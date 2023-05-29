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

