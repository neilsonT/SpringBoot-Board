package com.programmers.jpaboard.reply.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ReplyCreationDto {

    private String content;
    private Long parentId;
}
