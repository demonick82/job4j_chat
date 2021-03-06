insert into rooms (name)
values ('room 1');
insert into rooms (name)
values ('room 2');

insert into authorities (authority)
values ('ROLE_USER');
insert into authorities (authority)
values ('ROLE_ADMIN');

insert into persons (nickname, login, password, authority_id)
values ('user1', 'user@mail.ru', '$2a$10$NDdqOISl8ahk0EklWm4PN.6uLzviTFH452lDvUH/bpfXaXyAr7OqG', 2);
insert into persons (nickname, login, password, authority_id)
values ('user2', 'user2@mail.ru', '$2a$10$NDdqOISl8ahk0EklWm4PN.6uLzviTFH452lDvUH/bpfXaXyAr7OqG', 2);

insert into messages (description, person_id, room_id)
values ('message 1', 1, 1);
insert into messages (description, person_id, room_id)
values ('message 2', 1, 1);
insert into messages (description, person_id, room_id)
values ('message 3', 2, 2);

insert into rooms_persons (room_id, person_id)
values (1, 1);
insert into rooms_persons (room_id, person_id)
values (2, 2);
insert into rooms_persons (room_id, person_id)
values (2, 1);
