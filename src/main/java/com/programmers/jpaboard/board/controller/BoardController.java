package com.programmers.jpaboard.board.controller;

import com.programmers.jpaboard.board.controller.dto.BoardCreationDto;
import com.programmers.jpaboard.board.controller.dto.BoardResponseDto;
import com.programmers.jpaboard.board.controller.dto.BoardUpdateDto;
import com.programmers.jpaboard.board.converter.BoardConverter;
import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.service.BoardService;
import com.programmers.jpaboard.response.ApiResponse;
import com.programmers.jpaboard.board.controller.status.BoardResponseStatus;
import com.programmers.jpaboard.member.domain.Member;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public ApiResponse<BoardResponseDto> createBoard(@Valid @RequestBody BoardCreationDto boardCreationDto) {
        BoardResponseDto responseDto = boardService.saveBoard(boardCreationDto);

        return ApiResponse.ok(BoardResponseStatus.BOARD_CREATION_SUCCESS.getMessage(), responseDto);
    }

    @GetMapping
    public ApiResponse<List<BoardResponseDto>> lookupAllBoard() {
        List<BoardResponseDto> result = boardService.findAll();

        return ApiResponse.ok(BoardResponseStatus.BOARD_LOOKUP_ALL_SUCCESS.getMessage(), result);
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponseDto> lookupBoard(@PathVariable Long boardId) {
        BoardResponseDto responseDto = boardService.findOne(boardId);

        return ApiResponse.ok(BoardResponseStatus.BOARD_LOOKUP_SUCCESS.getMessage(), responseDto);
    }


    @PutMapping("/{boardId}")
    public ApiResponse<BoardResponseDto> updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, @PathVariable Long boardId) {
        BoardResponseDto responseDto = boardService.updateBoard(boardId, boardUpdateDto);

        return ApiResponse.ok(BoardResponseStatus.BOARD_UPDATE_SUCCESS.getMessage(), responseDto);
    }

    @DeleteMapping("/{boardId}")
    public ApiResponse<Long> deleteBoard(@PathVariable Long boardId) {
        Long deletedId = boardService.deleteBoard(boardId);

        return ApiResponse.ok(BoardResponseStatus.BOARD_DELETE_SUCCESS.getMessage(), deletedId);
    }
}
