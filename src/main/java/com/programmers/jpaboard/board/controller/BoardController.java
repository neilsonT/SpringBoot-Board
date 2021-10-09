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
public class BoardController {

    private final BoardService boardService;
    private final BoardConverter boardConverter;

    public BoardController(BoardService boardService, BoardConverter boardConverter) {
        this.boardService = boardService;
        this.boardConverter = boardConverter;
    }

    @PostMapping("/boards")
    public ApiResponse<BoardResponseDto> createBoard(@Valid @RequestBody BoardCreationDto boardCreationDto) {
        Board board = boardConverter.convertBoardByCreation(boardCreationDto);

        // Todo: 로그인한 Member를 관리할 수 있게 되면 삭제
        Member member = Member.builder()
                .age(10)
                .name("name")
                .hobbies(List.of("Table Tennis"))
                .build();

        Board saved = boardService.saveBoard(board, member);

        BoardResponseDto responseDto = getResponseDto(saved);
        return ApiResponse.ok(BoardResponseStatus.BOARD_CREATION_SUCCESS.getMessage(), responseDto);
    }

    @GetMapping("/boards")
    public ApiResponse<List<BoardResponseDto>> lookupAllBoard() {
        List<Board> boards = boardService.findAll();
        List<BoardResponseDto> result = boards.stream()
                .map(boardConverter::convertBoardResponseDto)
                .collect(Collectors.toList());

        return ApiResponse.ok(BoardResponseStatus.BOARD_LOOKUP_ALL_SUCCESS.getMessage(), result);
    }

    @GetMapping("/boards/{boardId}")
    public ApiResponse<BoardResponseDto> lookupBoard(@PathVariable Long boardId) {
        Board board = boardService.findOne(boardId);

        BoardResponseDto responseDto = getResponseDto(board);
        return ApiResponse.ok(BoardResponseStatus.BOARD_LOOKUP_SUCCESS.getMessage(), responseDto);
    }


    @PostMapping("/boards/{boardId}")
    public ApiResponse<BoardResponseDto> updateBoard(@Validated @RequestBody BoardUpdateDto boardUpdateDto, @PathVariable Long boardId) {

        Board updatedBoard = boardService.updateBoard(boardId, boardUpdateDto);
        BoardResponseDto responseDto = getResponseDto(updatedBoard);

        return ApiResponse.ok(BoardResponseStatus.BOARD_UPDATE_SUCCESS.getMessage(), responseDto);
    }

    private BoardResponseDto getResponseDto(Board updatedBoard) {
        return boardConverter.convertBoardResponseDto(updatedBoard);
    }
}
