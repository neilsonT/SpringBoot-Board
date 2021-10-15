package com.programmers.jpaboard.comment.domain;

import com.programmers.jpaboard.DateEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "child_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE comment SET deleted = true WHERE id = ?")
public class ChildComment extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = Comment.MAX_LENGTH)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Comment parent;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean deleted;

    @Builder
    public ChildComment(String content, Comment parent) {
        this.content = content;
        this.parent = parent;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Comment getParent() {
        return parent;
    }

    public void changeContent(String content) {
        this.content = content;
    }
}
