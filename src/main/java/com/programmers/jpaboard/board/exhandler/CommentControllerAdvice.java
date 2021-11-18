package com.programmers.jpaboard.board.exhandler;

import com.programmers.jpaboard.board.exception.CommentNotFoundException;
import com.programmers.jpaboard.response.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.programmers.jpaboard.comment")
public class CommentControllerAdvice {

    @ExceptionHandler
    public ApiResponse<CommentErrorResult> handleCommentNotFound(CommentNotFoundException exception) {
        CommentErrorResult commentErrorResult = new CommentErrorResult(exception.getClass().getSimpleName(), exception.getMessage());
        return ApiResponse.fail(CommentErrorStatus.COMMENT_NOT_FOUND.getCode(), CommentErrorStatus.COMMENT_NOT_FOUND.getMessage(), commentErrorResult);
    }

    @ExceptionHandler
    public ApiResponse<CommentErrorResult> handleException(Exception exception) {
        CommentErrorResult commentErrorResult = new CommentErrorResult(exception.getClass().getSimpleName(), exception.getMessage());
        return ApiResponse.fail(CommentErrorStatus.INTERNAL_SERVER_ERROR.getCode(), CommentErrorStatus.INTERNAL_SERVER_ERROR.getMessage(), commentErrorResult);
    }
}
