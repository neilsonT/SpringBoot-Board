package com.programmers.jpaboard.member.domain;

import com.programmers.jpaboard.DateEntity;
import com.programmers.jpaboard.member.domain.vo.Age;
import com.programmers.jpaboard.member.domain.vo.Hobby;
import com.programmers.jpaboard.member.domain.vo.Name;
import com.programmers.jpaboard.security.domain.Group;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Member extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String userId;

    private String password;

    @Embedded
    private Name name;

    @Embedded
    private Age age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<Hobby> hobbies = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    @Builder
    public Member(String userId, String name, int age, List<String> hobbies, Group group) {
        this.userId = userId;
        this.name = new Name(name);
        this.age = new Age(age);
        this.hobbies = hobbies.stream()
                .map(Hobby::new)
                .collect(Collectors.toList());
        this.group = group;
    }

    public void changeHobbies(List<String> hobbies) {
        this.hobbies = hobbies.stream()
                .map(Hobby::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name.getName();
    }

    public int getAge() {
        return age.getAge();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getHobbies() {
        return hobbies.stream()
                .map(Hobby::getHobby)
                .collect(Collectors.toList());
    }

    public Group getGroup() {
        return group;
    }
}
