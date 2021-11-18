INSERT INTO group(id, name)
VALUES (1, 'group1'),
       (2, 'group2')
;

INSERT INTO role(id, name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN')
;

INSERT INTO group_role(id, group_id, role_id)
VALUES (1, 1, 1),
       (2, 2, 1),
       (3, 2, 2)
;

INSERT INTO member(id, user_id, password, name, age, group_id)
VALUES (1, 'user', 'user123', 'chulsu', 11, 1),
       (2, 'grepp', 'grepp123', 'david', 12, 1),
       (3, 'admin', 'admin123', 'mark', 13, 2)
;

INSERT INTO board(id, title, content, deleted, num_of_comment, member_id)
VALUES (1, 'title1', 'content1', false, 0, 1),
       (2, 'title2', 'content2', false, 0, 1),
       (3, 'title3', 'content3', false, 0, 1),
       (4, 'title4', 'content4', false, 0, 2),
       (5, 'title5', 'content5', false, 0, 2),
       (6, 'title6', 'content6', false, 0, 3),
;

INSERT INTO role(id, role_name)
VALUES (1, 'ROLE_USER'),
       (2, 'ROLE_ADMIN')
;