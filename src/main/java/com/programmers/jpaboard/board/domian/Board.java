package com.programmers.jpaboard.board.domian;

import com.programmers.jpaboard.DateEntity;
import com.programmers.jpaboard.board.domian.vo.Content;
import com.programmers.jpaboard.board.domian.vo.Title;
import com.programmers.jpaboard.comment.domain.Comment;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE board SET deleted = true WHERE id = ?")
public class Board extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Content content;

    @OneToMany(mappedBy = "board")
    private List<Comment> replies = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

    @Builder
    public Board(String title, String content) {
        this.title = new Title(title);
        this.content = new Content(content);
    }

    public void update(String title, String content) {
        this.title = new Title(title);
        this.content = new Content(content);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title.getTitle();
    }

    public String getContent() {
        return content.getContent();
    }

    public List<Comment> getReplies() {
        return replies;
    }
}
