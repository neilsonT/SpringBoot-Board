package com.programmers.jpaboard.board.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreationDto {

    private String content;
    private Long parentId;
}
