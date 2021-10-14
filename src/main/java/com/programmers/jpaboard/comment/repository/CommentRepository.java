package com.programmers.jpaboard.comment.repository;

import com.programmers.jpaboard.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
