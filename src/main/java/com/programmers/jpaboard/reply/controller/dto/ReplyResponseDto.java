package com.programmers.jpaboard.reply.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReplyResponseDto {

    private final Long id;
    private final String content;
    private final Long parentId;
}
