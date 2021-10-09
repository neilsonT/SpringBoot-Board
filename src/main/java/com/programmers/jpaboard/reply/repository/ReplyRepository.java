package com.programmers.jpaboard.reply.repository;

import com.programmers.jpaboard.reply.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
