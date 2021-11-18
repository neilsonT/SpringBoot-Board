package com.programmers.jpaboard.security.service;

import com.programmers.jpaboard.member.domain.Member;
import com.programmers.jpaboard.member.repository.MemberRepository;
import com.programmers.jpaboard.security.domain.GroupRole;
import com.programmers.jpaboard.security.domain.MemberContext;
import com.programmers.jpaboard.security.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUserId(username)
                .orElseThrow(() -> new RuntimeException("User Not Found "));

        List<SimpleGrantedAuthority> authorities = member.getGroup().getGroupRoles().stream()
                .map(GroupRole::getRole)
                .map(Role::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new MemberContext(member, authorities);
    }
}
