drop table if exists group cascade;

drop table if exists member cascade;

drop table if exists board cascade;

drop table if exists comment cascade;

drop table if exists role cascade;

drop table if exists group_role cascade;

create table role (
    id bigint not null,
    name varchar(10),
    PRIMARY KEY (id)
)

create table group (
    id bigint not null,
    name varchar(20) not null,
    PRIMARY KEY (id)
)

create table group_role (
    id bigint not null,
    group_id bigint not null,
    role_id bigint not null,
    PRIMARY KEY (id),
    CONSTRAINT unq_group_id_role_id UNIQUE (group_id, role_id),
    CONSTRAINT fk_group_id_for_group_role FOREIGN KEY(group_id) REFERENCES group (id) ON DELETE RESTRIC ON UPDATE RESTRICT,
    CONSTRAINT fk_role_id_for_group_role FOREIGN KEY(role_id) REFERENCES role (id) ON DELETE RESTRIC ON UPDATE RESTRICT,
)

create table member (
    id bigint not null,
    user_id varchar(20) not null,
    password varchar(20) not null,
    name varchar(10) not null,
    age bigint,
    group_id bigint references group(id),
    PRIMARY KEY (id)
)

create table board (
    id bigint not null,
    title varchar(100) not null,
    content clob,
    deleted boolean,
    num_of_comment bigint,
    member_id references member(id),
    PRIMARY KEY (id)
)

create table comment (
    id bigint not null,
    content varchar (255),
    board_id references board(id),
    num_of_child_comment bigint,
    parent_id bigint,
    deleted boolean,
    PRIMARY KEY (id)
)
