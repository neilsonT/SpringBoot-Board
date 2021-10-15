package com.programmers.jpaboard.comment.exhandler;
import lombok.Getter;

@Getter
public class CommentErrorResult {
    private final String error;
    private final String details;

    public CommentErrorResult(String error, String details) {
        this.error = error;
        this.details = details;
    }
}
