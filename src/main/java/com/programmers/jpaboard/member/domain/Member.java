package com.programmers.jpaboard.member.domain;

import com.programmers.jpaboard.DateEntity;
import com.programmers.jpaboard.member.domain.vo.Age;
import com.programmers.jpaboard.member.domain.vo.Hobby;
import com.programmers.jpaboard.member.domain.vo.Name;
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

    @Embedded
    private Name name;

    @Embedded
    private Age age;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<Hobby> hobbies = new ArrayList<>();

    @Builder
    public Member(String name, int age, List<String> hobbies) {
        this.name = new Name(name);
        this.age = new Age(age);
        this.hobbies = hobbies.stream()
                .map(Hobby::new)
                .collect(Collectors.toList());
    }

    public void changeHobbies(List<String> hobbies) {
        this.hobbies = hobbies.stream()
                .map(Hobby::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name.getName();
    }

    public int getAge() {
        return age.getAge();
    }

    public List<String> getHobby() {
        return hobbies.stream()
                .map(Hobby::getHobby)
                .collect(Collectors.toList());
    }
}
