create table if not exists authorities
(
    id        serial primary key,
    authority varchar not null unique
);

create table if not exists persons
(
    id           serial primary key,
    nickname     varchar,
    login        varchar not null unique,
    password     varchar not null,
    authority_id int references authorities (id)
);
create table if not exists rooms
(
    id   serial primary key,
    name varchar
);

create table if not exists messages
(
    id          serial primary key,
    description varchar,
    created     timestamp without time zone not null default now(),
    person_id   int references persons (id),
    room_id     int references rooms (id)
);

create table if not exists rooms_persons
(
    room_id   int references rooms (id),
    person_id int references persons (id)
);


