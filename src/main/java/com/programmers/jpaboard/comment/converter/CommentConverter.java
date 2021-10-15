package com.programmers.jpaboard.comment.converter;

import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.comment.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.comment.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.comment.domain.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {

    public Comment convertComment(CommentCreationDto commentCreationDto, Board board) {
        return Comment.builder()
                .content(commentCreationDto.getContent())
                .board(board)
                .parentId(commentCreationDto.getParentId())
                .build();
    }

    public CommentResponseDto convertCommentResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .parentId(comment.getParentId())
                .build();
    }
}
