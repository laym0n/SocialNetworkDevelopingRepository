--liquibase formatted sql

--changeset nvoxland:25
insert into users(username, password, first_name, second_name)
values ('admin', '$2a$12$Xl4e.IKstMFinY4Xjgc/i.txqdDOUb22TxujK8fgGjqQ0H5qL.zJy', 'admin', 'admin');

--changeset nvoxland:26
insert into users_roles(user_id, role_id)
values ((select id from users where username = 'admin'), (select id from roles where name = 'ADMIN'));

