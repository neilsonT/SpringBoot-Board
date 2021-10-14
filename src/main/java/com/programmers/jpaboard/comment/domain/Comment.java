package com.programmers.jpaboard.comment.domain;

import com.programmers.jpaboard.DateEntity;
import com.programmers.jpaboard.board.domian.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Comment extends DateEntity {

    private final int MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = MAX_LENGTH)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @Column(name = "parent_id")
    private Long parentId;

    @Builder
    public Comment(String content, Board board, Long parentId) {
        this.content = content;
        this.board = board;
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Board getBoard() {
        return board;
    }

    public Long getParentId() {
        return parentId;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
