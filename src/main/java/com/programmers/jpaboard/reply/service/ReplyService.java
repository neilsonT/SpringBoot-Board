package com.programmers.jpaboard.reply.service;

import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.board.exception.BoardNotFoundException;
import com.programmers.jpaboard.board.repository.BoardRepository;
import com.programmers.jpaboard.board.service.BoardService;
import com.programmers.jpaboard.member.domain.Member;
import com.programmers.jpaboard.reply.domain.Reply;
import com.programmers.jpaboard.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Reply saveReply(Reply reply, Long boardId, Member member) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new BoardNotFoundException(boardId));

        reply.setBoard(board);
        reply.setMember(member);

        return replyRepository.save(reply);
    }
}
