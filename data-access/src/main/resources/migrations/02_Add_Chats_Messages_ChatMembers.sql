--liquibase formatted sql

--changeset nvoxland:7
CREATE TABLE chat_type
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
--rollback drop table chat_type;

--changeset nvoxland:8
INSERT INTO chat_type(NAME) VALUES ('DIALOG_CHAT'), ('PRIVATE_CHAT'), ('GROUP_CHAT');

--changeset nvoxland:9
CREATE TABLE chat
(
   id SERIAL PRIMARY KEY,
   chat_type_id INTEGER NOT NULL REFERENCES chat_type(id),
   is_blocked BOOLEAN NOT NULL
);
--rollback drop table chat;

--changeset nvoxland:10
CREATE TABLE group_chat
(
    id INTEGER PRIMARY KEY references chat(id),
    name VARCHAR(255) NOT NULL,
    avatar BYTEA
);
--rollback drop table group_chat;

--changeset nvoxland:11
CREATE TABLE private_chat
(
    id INTEGER PRIMARY KEY references chat(id),
    id_user INTEGER REFERENCES users(id),
    name VARCHAR(255) NOT NULL,
    UNIQUE (id_user, name)
);
--rollback drop table private_chat;

--changeset nvoxland:12
CREATE TABLE chat_members (
    id SERIAL NOT NULL PRIMARY KEY,
    chat_id INTEGER NOT NULL REFERENCES chat(id),
    user_id INTEGER NOT NULL REFERENCES users(id),
    is_blocked BOOLEAN NOT NULL,
    order_id_of_message_where_start_reading INTEGER NOT NULL,
    last_order_id_of_checked_message INTEGER NOT NULL,
    UNIQUE (chat_id, user_id)
);
--rollback drop table chat_members;

--changeset nvoxland:13
CREATE TABLE group_chat_members (
    id INTEGER NOT NULL PRIMARY KEY REFERENCES chat_members(id),
    chat_member_name VARCHAR(255)
);
--rollback drop table group_chat_members;

--changeset nvoxland:14
CREATE TABLE group_chat_roles (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);
--rollback drop group_chat_roles;

--changeset nvoxland:15
CREATE TABLE group_chat_members_roles (
    id_member INTEGER REFERENCES group_chat_roles(id),
    id_group_member_role INTEGER REFERENCES group_chat_roles(id),
    PRIMARY KEY (id_member, id_group_member_role)
);
--rollback drop table group_chat_members_roles;
