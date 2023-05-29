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
   name VARCHAR(255),
   description VARCHAR(255),
   rules VARCHAR(255),
   avatar BYTEA,
   is_blocked BOOLEAN NOT NULL,
   is_active BOOLEAN DEFAULT TRUE NOT NULL,
   is_frozen_due_to_black_list BOOlEAN DEFAULT FALSE NOT NULL
);
--rollback drop table chat;

--changeset nvoxland:10
CREATE TABLE chat_members (
    id SERIAL NOT NULL PRIMARY KEY,
    chat_id INTEGER NOT NULL REFERENCES chat(id),
    user_id INTEGER NOT NULL REFERENCES users(id),
    is_blocked BOOLEAN NOT NULL,
    order_id_of_message_where_start_reading INTEGER NOT NULL,
    last_order_id_of_checked_message INTEGER NOT NULL,
    group_member_name VARCHAR(255),
    UNIQUE (chat_id, user_id)
);
--rollback drop table chat_members;

--changeset nvoxland:11
CREATE TABLE group_chat_members (
    id INTEGER NOT NULL PRIMARY KEY REFERENCES chat_members(id),
    chat_member_name VARCHAR(255)
);
--rollback drop table group_chat_members;

--changeset nvoxland:12
CREATE TABLE group_chat_roles (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);
--rollback drop group_chat_roles;

--changeset nvoxland:13
CREATE TABLE group_chat_members_roles (
    id_member INTEGER REFERENCES group_chat_roles(id),
    id_group_member_role INTEGER REFERENCES group_chat_roles(id),
    PRIMARY KEY (id_member, id_group_member_role)
);
--rollback drop table group_chat_members_roles;


--changeset nvoxland:14
CREATE TABLE message (
    order_id SERIAL,
    chat_id INTEGER NOT NULL REFERENCES chat(id),
    chat_member_id INTEGER REFERENCES chat_members(id),
    text TEXT,
    images BYTEA,
    PRIMARY KEY (chat_id, order_id)
);
--rollback drop table group_chat_members_roles;
