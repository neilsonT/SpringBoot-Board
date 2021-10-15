package com.programmers.jpaboard.comment.controller;

import com.programmers.jpaboard.comment.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.comment.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.comment.controller.dto.CommentUpdateDto;
import com.programmers.jpaboard.comment.controller.status.CommentResponseStatus;
import com.programmers.jpaboard.comment.service.ChildCommentService;
import com.programmers.jpaboard.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/childcomments")
public class ChildCommentController {

    private final ChildCommentService childCommentService;

    @PostMapping
    public ApiResponse<CommentResponseDto> createChildComment(@Valid @RequestBody CommentCreationDto commentCreationDto, Long commentId) {
        CommentResponseDto commentResponseDto = childCommentService.saveChildComment(commentCreationDto, commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_CREATION_SUCCESS.getMessage(), commentResponseDto);
    }

    @PutMapping("/{childCommentId}")
    public ApiResponse<CommentResponseDto> updateChildComment(@Valid @RequestBody CommentUpdateDto commentUpdateDto, @PathVariable Long childCommentId) {
        CommentResponseDto commentResponseDto = childCommentService.updateChildComment(commentUpdateDto, childCommentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_UPDATE_SUCCESS.getMessage(), commentResponseDto);
    }

    @GetMapping
    public ApiResponse<List<CommentResponseDto>> lookupAllChildCommentByCommentId(@RequestParam Long commentId){
        List<CommentResponseDto> commentResponseDtos = childCommentService.lookupAllChildCommentByCommentId(commentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_LOOKUP_ALL_SUCCESS.getMessage(), commentResponseDtos);
    }

    @DeleteMapping("/{childCommentId}")
    public ApiResponse<Long> deleteChildComment(@PathVariable Long childCommentId) {
        Long deleteId = childCommentService.deleteChildComment(childCommentId);

        return ApiResponse.ok(CommentResponseStatus.COMMENT_DELETE_SUCCESS.getMessage(), deleteId);
    }
}
