package com.programmers.jpaboard.comment.controller.status;

import lombok.Getter;

@Getter
public enum CommentResponseStatus {
    COMMENT_CREATION_SUCCESS("Comment Creation Success"),
    COMMENT_UPDATE_SUCCESS("Comment Update Success"),
    COMMENT_LOOKUP_SUCCESS("Comment Lookup Success"),
    COMMENT_LOOKUP_ALL_SUCCESS("Comment Lookup All Success"),
    COMMENT_DELETE_SUCCESS("Comment Delete Success");

    private String message;

    CommentResponseStatus(String message) {
        this.message = message;
    }
}
