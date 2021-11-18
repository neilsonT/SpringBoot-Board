package com.programmers.jpaboard.board.converter;

import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.board.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.board.domian.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public Comment convertComment(CommentCreationDto commentCreationDto, Board board) {
        return Comment.builder()
                .content(commentCreationDto.getContent())
                .board(board)
                .build();
    }

    public CommentResponseDto convertCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .build();
    }
}
