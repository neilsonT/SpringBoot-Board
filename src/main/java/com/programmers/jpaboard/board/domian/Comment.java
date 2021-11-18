package com.programmers.jpaboard.board.domian;

import com.programmers.jpaboard.DateEntity;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE id = ?")
public class Comment extends DateEntity {

    public static final int MAX_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = MAX_LENGTH)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    private Board board;

    @Column(name = "num_of_child_comment")
    private int numOfChildComment;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

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

    public int getNumOfChildComment() {
        return numOfChildComment;
    }

    public void plusChildNum(){
        this.numOfChildComment++;
    }

    public void minusChildNum(){
        this.numOfChildComment--;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
