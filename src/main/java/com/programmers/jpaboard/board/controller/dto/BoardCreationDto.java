package com.programmers.jpaboard.board.controller.dto;

import com.programmers.jpaboard.board.domian.vo.Content;
import com.programmers.jpaboard.board.domian.vo.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreationDto {
    @NotBlank
    @Length(min = Title.MIN_LENGTH, max = Title.MAX_LENGTH)
    private String title;

    @NotBlank
    @Length(max = Content.MAX_LENGTH)
    private String content;
}
