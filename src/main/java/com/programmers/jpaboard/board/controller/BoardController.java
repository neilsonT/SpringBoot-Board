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
    private final BoardConverter boardConverter;

    public BoardController(BoardService boardService, BoardConverter boardConverter) {
        this.boardService = boardService;
        this.boardConverter = boardConverter;
    }

    @PostMapping
    public ApiResponse<BoardResponseDto> createBoard(@Valid @RequestBody BoardCreationDto boardCreationDto) {
        Board board = boardConverter.convertBoardByCreation(boardCreationDto);

        Board saved = boardService.saveBoard(board);

        BoardResponseDto responseDto = getResponseDto(saved);
        return ApiResponse.ok(BoardResponseStatus.BOARD_CREATION_SUCCESS.getMessage(), responseDto);
    }

    @GetMapping
    public ApiResponse<List<BoardResponseDto>> lookupAllBoard() {
        List<BoardResponseDto> result = boardService.findAll().stream()
                .map(boardConverter::convertBoardResponseDto)
                .collect(Collectors.toList());

        return ApiResponse.ok(BoardResponseStatus.BOARD_LOOKUP_ALL_SUCCESS.getMessage(), result);
    }

    @GetMapping("/{boardId}")
    public ApiResponse<BoardResponseDto> lookupBoard(@PathVariable Long boardId) {
        Board board = boardService.findOne(boardId);

        BoardResponseDto responseDto = getResponseDto(board);
        return ApiResponse.ok(BoardResponseStatus.BOARD_LOOKUP_SUCCESS.getMessage(), responseDto);
    }


    @PutMapping("/{boardId}")
    public ApiResponse<BoardResponseDto> updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, @PathVariable Long boardId) {

        Board updatedBoard = boardService.updateBoard(boardId, boardUpdateDto);
        BoardResponseDto responseDto = getResponseDto(updatedBoard);

        return ApiResponse.ok(BoardResponseStatus.BOARD_UPDATE_SUCCESS.getMessage(), responseDto);
    }

    private BoardResponseDto getResponseDto(Board updatedBoard) {
        return boardConverter.convertBoardResponseDto(updatedBoard);
    }
}
