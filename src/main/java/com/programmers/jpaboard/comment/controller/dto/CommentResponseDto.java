package com.programmers.jpaboard.comment.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CommentResponseDto {

    private final Long id;
    private final String content;
    private final Long parentId;
}
