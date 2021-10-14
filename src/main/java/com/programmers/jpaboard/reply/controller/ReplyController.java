package com.programmers.jpaboard.reply.controller;

import com.programmers.jpaboard.member.domain.Member;
import com.programmers.jpaboard.reply.controller.dto.ReplyCreationDto;
import com.programmers.jpaboard.reply.controller.dto.ReplyResponseDto;
import com.programmers.jpaboard.reply.controller.status.ReplyResponseStatus;
import com.programmers.jpaboard.reply.converter.ReplyConverter;
import com.programmers.jpaboard.reply.domain.Reply;
import com.programmers.jpaboard.reply.service.ReplyService;
import com.programmers.jpaboard.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/replies")
public class ReplyController {

    private final ReplyConverter replyConverter;
    private final ReplyService replyService;

    @PostMapping
    public ApiResponse<ReplyResponseDto> createReply(@Valid @RequestBody ReplyCreationDto replyCreationDto, Long boardId) {
        Member member = Member.builder()
                .age(11)
                .name("name")
                .hobbies(List.of("clean code"))
                .build();

        Reply reply = getReply(replyCreationDto);
        Reply savedReply = replyService.saveReply(reply, boardId, member);
        ReplyResponseDto replyResponseDto = getReplyResponseDto(savedReply);

        return ApiResponse.ok(ReplyResponseStatus.REPLY_CREATION_SUCCESS.getMessage(), replyResponseDto);
    }

    private ReplyResponseDto getReplyResponseDto(Reply savedReply) {
        return replyConverter.convertReplyResponseDto(savedReply);
    }

    private Reply getReply(ReplyCreationDto replyCreationDto) {
        return replyConverter.convertReply(replyCreationDto);
    }
}
