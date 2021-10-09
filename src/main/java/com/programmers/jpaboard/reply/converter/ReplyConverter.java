package com.programmers.jpaboard.reply.converter;

import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.member.domain.Member;
import com.programmers.jpaboard.reply.controller.dto.ReplyCreationDto;
import com.programmers.jpaboard.reply.controller.dto.ReplyResponseDto;
import com.programmers.jpaboard.reply.domain.Reply;
import org.springframework.stereotype.Component;

@Component
public class ReplyConverter {

    public Reply convertReply(ReplyCreationDto replyCreationDto) {
        return Reply.builder()
                .content(replyCreationDto.getContent())
                .parentId(replyCreationDto.getParentId())
                .build();
    }

    public ReplyResponseDto convertReplyResponseDto(Reply reply) {
        return ReplyResponseDto.builder()
                .id(reply.getId())
                .content(reply.getContent())
                .parentId(reply.getParentId())
                .build();
    }
}
