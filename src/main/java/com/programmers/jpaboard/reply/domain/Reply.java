package com.programmers.jpaboard.reply.domain;

import com.programmers.jpaboard.DateEntity;
import com.programmers.jpaboard.board.domian.Board;
import com.programmers.jpaboard.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Reply extends DateEntity {

    private final int MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = MAX_LENGTH)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @Column(name = "parent_id")
    private Long parentId;

    @Builder
    public Reply(String content, Member member, Board board, Long parentId){
        this.content = content;
        this.member = member;
        this.board = board;
        this.parentId = parentId;
    }

    public void setMember(Member member) {
        if (Objects.nonNull(this.member)) {
            this.member.getReplies().remove(this);
        }
        member.getReplies().add(this);
        this.member = member;
    }

    public void setBoard(Board board) {
        if (Objects.nonNull(this.board)) {
            this.board.getReplies().remove(this);
        }
        this.board = board;
        board.getReplies().add(this);
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Member getMember() {
        return member;
    }

    public Board getBoard() {
        return board;
    }

    public Long getParentId() {
        return parentId;
    }
}
