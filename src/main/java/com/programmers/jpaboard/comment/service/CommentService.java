package com.programmers.jpaboard.comment.service;

import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.exception.BoardNotFoundException;
import com.programmers.jpaboard.board.repository.BoardRepository;
import com.programmers.jpaboard.comment.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.comment.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.comment.controller.dto.CommentUpdateDto;
import com.programmers.jpaboard.comment.converter.CommentConverter;
import com.programmers.jpaboard.comment.domain.Comment;
import com.programmers.jpaboard.comment.exception.CommentNotFoundException;
import com.programmers.jpaboard.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final CommentConverter commentConverter;

    @Transactional
    public CommentResponseDto saveComment(CommentCreationDto commentCreationDto, Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(boardId));

        Comment comment = commentConverter.convertComment(commentCreationDto, board);
        Comment savedComment = commentRepository.save(comment);

        return commentConverter.convertCommentResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(CommentUpdateDto commentUpdateDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        comment.changeContent(commentUpdateDto.getContent());
        return commentConverter.convertCommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public CommentResponseDto lookupComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        return commentConverter.convertCommentResponseDto(comment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> lookupAllComment() {
        return commentRepository.findAll()
                .stream()
                .map(commentConverter::convertCommentResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        return commentId;
    }
}
