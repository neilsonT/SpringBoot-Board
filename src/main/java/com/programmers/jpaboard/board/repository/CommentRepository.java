package com.programmers.jpaboard.board.repository;

import com.programmers.jpaboard.board.domian.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByParentId(Long parentId);
}
