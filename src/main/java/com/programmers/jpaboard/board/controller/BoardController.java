package com.programmers.jpaboard.board.controller;

import com.programmers.jpaboard.board.controller.dto.*;
import com.programmers.jpaboard.board.controller.status.CommentResponseStatus;
import com.programmers.jpaboard.board.converter.BoardConverter;
import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.service.BoardService;
import com.programmers.jpaboard.board.service.CommentService;
import com.programmers.jpaboard.response.ApiResponse;
import com.programmers.jpaboard.board.controller.status.BoardResponseStatus;
import com.programmers.jpaboard.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final CommentService commentService;

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

    /* ========================= 댓글 ===================== */

    @PostMapping("/{boardId}/comments")
    public ApiResponse<CommentResponseDto> createComment(@Valid @RequestBody CommentCreationDto commentCreationDto, @PathVariable Long boardId) {
        CommentResponseDto commentResponseDto = commentService.saveComment(commentCreationDto, boardId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_CREATION_SUCCESS.getMessage(), commentResponseDto);
    }

    @PutMapping("/{boardId}/comments/{commentId}")
    public ApiResponse<CommentResponseDto> updateComment(@Valid @RequestBody CommentUpdateDto commentUpdateDto, @PathVariable Long boardId, @PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentUpdateDto, commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_UPDATE_SUCCESS.getMessage(), commentResponseDto);
    }

    @GetMapping("/{boardId}/comments/{commentId}")
    public ApiResponse<CommentResponseDto> lookupComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.lookupComment(commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_LOOKUP_SUCCESS.getMessage(), commentResponseDto);
    }

    @GetMapping("/{boardId}/comments")
    public ApiResponse<List<CommentResponseDto>> lookupAllComment(@PathVariable Long boardId){
        List<CommentResponseDto> commentResponseDtos = commentService.lookupAllComment();

        return ApiResponse.ok(CommentResponseStatus.COMMENT_LOOKUP_ALL_SUCCESS.getMessage(), commentResponseDtos);
    }

    @DeleteMapping("/{boardId}/comments/{commentId}")
    public ApiResponse<Long> deleteComment(@PathVariable Long boardId, @PathVariable Long commentId) {
        Long deleteId = commentService.deleteComment(commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_DELETE_SUCCESS.getMessage(), deleteId);
    }
}
