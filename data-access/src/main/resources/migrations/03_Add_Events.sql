--liquibase formatted sql

--changeset nvoxland:17
CREATE TABLE event_type
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
--rollback drop table event_type;

--changeset nvoxland:18
INSERT INTO event_type(name)
values
('CHAT_CREATED'),
('CHAT_DELETED'),
('CHAT_INFO_CHANGED'),
('MESSAGE_EDITED'),
('NEW_MESSAGE_WAS_SENT'),
('MESSAGE_DELETED'),
('MESSAGE_BLOCKED'),
('MESSAGE_CHECKED'),
('CHAT_MEMBER_BLOCKED'),
('CHAT_MEMBER_CHANGED_INFO');

--changeset nvoxland:19
CREATE TABLE event
(
   id SERIAL,
   chat_id INTEGER NOT NULL REFERENCES chat(id),
   event_type_id INTEGER NOT NULL REFERENCES event_type(id),
   chat_member_id INTEGER REFERENCES chat_members(id),
   order_id_message INTEGER,
   PRIMARY KEY (id, chat_id)
);
--rollback drop table event;

