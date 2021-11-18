package com.programmers.jpaboard.member.repository;

import com.programmers.jpaboard.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

}
