package com.programmers.jpaboard.board.exhandler;

import lombok.Getter;

@Getter
public class BoardErrorResult {
    private final String error;
    private final String details;

    public BoardErrorResult(String error, String details) {
        this.error = error;
        this.details = details;
    }
}
