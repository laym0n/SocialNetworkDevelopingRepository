--liquibase formatted sql

--changeset nvoxland:20
CREATE TABLE friends
(
    first_user_id INTEGER NOT NULL REFERENCES users(id),
    second_user_id INTEGER NOT NULL REFERENCES users(id),
    when_became_friends TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (first_user_id, second_user_id),
    UNIQUE (second_user_id, first_user_id)
);
--rollback drop table friends;

--changeset nvoxland:21
CREATE TABLE black_list_of_users
(
    id_user_owner INTEGER NOT NULL REFERENCES users(id),
    id_user_blocked INTEGER NOT NULL REFERENCES users(id),
    when_blocked TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (id_user_owner, id_user_blocked)
);
--rollback drop table black_list_of_users;

--changeset nvoxland:22
CREATE TABLE friend_requests
(
    id_user_sender_request INTEGER NOT NULL REFERENCES users(id),
    id_user_to_whom INTEGER NOT NULL REFERENCES users(id),
    when_request_sent TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY (id_user_sender_request, id_user_to_whom),
    UNIQUE (id_user_to_whom, id_user_sender_request)
);
--rollback drop table friend_requests;

