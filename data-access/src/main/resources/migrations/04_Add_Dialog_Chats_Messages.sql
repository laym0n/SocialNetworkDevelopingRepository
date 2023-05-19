--liquibase formatted sql

--changeset nvoxland:17
CREATE TABLE dialog_chat
(
    id INTEGER PRIMARY KEY REFERENCES chat(id),
    first_user_id INTEGER NOT NULL REFERENCES users(id),
    second_user_id INTEGER NOT NULL REFERENCES users(id),
    UNIQUE (first_user_id, second_user_id)
);
--rollback drop table event_type;

--changeset nvoxland:19
CREATE TABLE message
(
   id SERIAL,
   chat_id INTEGER NOT NULL REFERENCES chat(id),
   chat_member_id INTEGER NOT NULL REFERENCES chat_members(id),
   event_id_when_created INTEGER NOT NULL,
   text TEXT,
   images BYTEA,
   PRIMARY KEY (id, chat_id)
);
--rollback drop table event;

