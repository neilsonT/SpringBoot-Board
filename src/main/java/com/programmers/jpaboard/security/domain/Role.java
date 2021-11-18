package com.programmers.jpaboard.security.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
