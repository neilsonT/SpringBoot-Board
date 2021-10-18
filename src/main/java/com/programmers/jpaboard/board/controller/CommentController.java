package com.programmers.jpaboard.board.controller;

import com.programmers.jpaboard.board.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.board.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.board.controller.dto.CommentUpdateDto;
import com.programmers.jpaboard.board.controller.status.CommentResponseStatus;
import com.programmers.jpaboard.board.service.CommentService;
import com.programmers.jpaboard.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ApiResponse<CommentResponseDto> createComment(@Valid @RequestBody CommentCreationDto commentCreationDto, Long boardId) {
        CommentResponseDto commentResponseDto = commentService.saveComment(commentCreationDto, boardId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_CREATION_SUCCESS.getMessage(), commentResponseDto);
    }

    @PutMapping("/{commentId}")
    public ApiResponse<CommentResponseDto> updateComment(@Valid @RequestBody CommentUpdateDto commentUpdateDto, @PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.updateComment(commentUpdateDto, commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_UPDATE_SUCCESS.getMessage(), commentResponseDto);
    }

    @GetMapping("/{commentId}")
    public ApiResponse<CommentResponseDto> lookupComment(@PathVariable Long commentId) {
        CommentResponseDto commentResponseDto = commentService.lookupComment(commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_LOOKUP_SUCCESS.getMessage(), commentResponseDto);
    }

    @GetMapping
    public ApiResponse<List<CommentResponseDto>> lookupAllComment(){
        List<CommentResponseDto> commentResponseDtos = commentService.lookupAllComment();

        return ApiResponse.ok(CommentResponseStatus.COMMENT_LOOKUP_ALL_SUCCESS.getMessage(), commentResponseDtos);
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<Long> deleteComment(@PathVariable Long commentId) {
        Long deleteId = commentService.deleteComment(commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_DELETE_SUCCESS.getMessage(), deleteId);
    }
}
