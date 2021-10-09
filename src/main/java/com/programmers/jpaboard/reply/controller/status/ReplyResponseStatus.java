package com.programmers.jpaboard.reply.controller.status;

import lombok.Getter;

@Getter
public enum ReplyResponseStatus {
    REPLY_CREATION_SUCCESS("Reply Creation Success"),
    REPLY_UPDATE_SUCCESS("Reply Update Success");

    private String message;

    ReplyResponseStatus(String message) {
        this.message = message;
    }
}
