package com.programmers.jpaboard.reply.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ReplyCreationDto {

    private String content;
    private Long parentId;
}
