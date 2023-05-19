--liquibase formatted sql

--changeset nvoxland:1
CREATE TABLE users
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(255) UNIQUE NOT NULL,
    password    VARCHAR(255)        NOT NULL,
    first_name  VARCHAR(255)        NOT NULL,
    second_name VARCHAR(255)        NOT NULL,
    avatar BYTEA,
    is_blocked BOOLEAN              NOT NULL,
    birthday TIMESTAMP WITH TIME ZONE,
    last_get_updates_time TIMESTAMP WITH TIME ZONE NOT NULL
);
--rollback drop table users;

--changeset nvoxland:2
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
--rollback drop table users;

--changeset nvoxland:4
CREATE TABLE users_roles
(
    user_id INTEGER NOT NULL REFERENCES users (id),
    role_id INTEGER NOT NULL REFERENCES roles (id),
    PRIMARY KEY (user_id, role_id)
);
--rollback drop users_roles;

--changeset nvoxland:5
CREATE TABLE bans
(
    id              SERIAL PRIMARY KEY,
    user_id         INTEGER REFERENCES users (id),
    user_blocker_id INTEGER                  NOT NULL,
    description     VARCHAR(255)             NOT NULL,
    when_banned     TIMESTAMP WITH TIME ZONE NOT NULL
);
--rollback drop table bans;

--changeset nvoxland:6
insert into roles(name) values ('SIMPLE_USER'), ('ADMIN');