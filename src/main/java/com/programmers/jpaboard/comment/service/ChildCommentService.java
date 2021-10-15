package com.programmers.jpaboard.comment.service;

import com.programmers.jpaboard.board.repository.BoardRepository;
import com.programmers.jpaboard.comment.controller.dto.CommentCreationDto;
import com.programmers.jpaboard.comment.controller.dto.CommentResponseDto;
import com.programmers.jpaboard.comment.controller.dto.CommentUpdateDto;
import com.programmers.jpaboard.comment.converter.CommentConverter;
import com.programmers.jpaboard.comment.domain.ChildComment;
import com.programmers.jpaboard.comment.domain.Comment;
import com.programmers.jpaboard.comment.exception.CommentNotFoundException;
import com.programmers.jpaboard.comment.repository.ChildCommentRepository;
import com.programmers.jpaboard.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChildCommentService {

    private final ChildCommentRepository childCommentRepository;
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Transactional
    public CommentResponseDto saveChildComment(CommentCreationDto commentCreationDto, Long commentId) {
        Comment parent = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException(commentId));

        ChildComment childComment = commentConverter.convertChildComment(commentCreationDto, parent);
        ChildComment save = childCommentRepository.save(childComment);

        return commentConverter.convertCommentResponseDto(save);
    }

    @Transactional
    public CommentResponseDto updateChildComment(CommentUpdateDto commentUpdateDto, Long childCommentId) {
        ChildComment childComment = childCommentRepository.findById(childCommentId)
                .orElseThrow(() -> new CommentNotFoundException(childCommentId));

        childComment.changeContent(commentUpdateDto.getContent());
        return commentConverter.convertCommentResponseDto(childComment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> lookupAllChildCommentByCommentId(Long commentId) {
        return childCommentRepository.findByParentId(commentId).stream()
                .map(commentConverter::convertCommentResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long deleteChildComment(Long childCommentId) {
        childCommentRepository.findById(childCommentId)
                .ifPresentOrElse(childCommentRepository::delete,
                        () -> {
                            throw new CommentNotFoundException(childCommentId);
                        });
        return childCommentId;
    }
}
