package com.programmers.jpaboard.member.controller;

import com.programmers.jpaboard.member.controller.status.MemberResponseStatus;
import com.programmers.jpaboard.response.ApiResponse;
import com.programmers.jpaboard.member.controller.dto.MemberCreationDto;
import com.programmers.jpaboard.member.controller.dto.MemberResponseDto;
import com.programmers.jpaboard.member.converter.MemberConverter;
import com.programmers.jpaboard.member.domain.Member;
import com.programmers.jpaboard.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberConverter memberConverter;

    @PostMapping
    public ApiResponse<MemberResponseDto> createMember(@Validated @RequestBody MemberCreationDto memberCreationDto) {
        Member member = memberConverter.convertMember(memberCreationDto);
        Member saved = memberService.saveMember(member);
        MemberResponseDto memberResponseDto = memberConverter.convertMemberResponseDto(saved);

        return ApiResponse.ok(MemberResponseStatus.MEMBER_CREATION_SUCCESS.getMessage(), memberResponseDto);
    }
}
