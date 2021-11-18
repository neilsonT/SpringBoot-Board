package com.programmers.jpaboard.board.service;

import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.exception.BoardNotFoundException;
import com.programmers.jpaboard.board.repository.BoardRepository;
import com.programmers.jpaboard.board.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.board.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.board.controller.dto.CommentUpdateDto;
import com.programmers.jpaboard.board.converter.CommentConverter;
import com.programmers.jpaboard.board.domian.Comment;
import com.programmers.jpaboard.board.exception.CommentNotFoundException;
import com.programmers.jpaboard.board.repository.CommentRepository;
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

    @Transactional(readOnly = true)
    public List<CommentResponseDto> lookupChildCommentList(Long parentId) {
        return commentRepository.findByParentId(parentId).stream()
                .map(commentConverter::convertCommentResponseDto)
                .collect(Collectors.toList());
    }

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

    @Transactional
    public Long deleteComment(Long commentId) {
        commentRepository.findById(commentId)
                .ifPresentOrElse(commentRepository::delete,
                        () -> {
                            throw new CommentNotFoundException(commentId);
                        });
        return commentId;
    }
}
