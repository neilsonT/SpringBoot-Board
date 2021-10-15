package com.programmers.jpaboard.comment.repository;

import com.programmers.jpaboard.comment.domain.ChildComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildCommentRepository extends JpaRepository<ChildComment, Long> {
    List<ChildComment> findByParent(Long parentId);
}
