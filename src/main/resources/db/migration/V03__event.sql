create table if not exists book_event(
    event_id bigint primary key,
    date timestamp not null,
    type varchar(10) not null,
    book_id bigint not null,
    patron_id bigint
);

create sequence book_event_seq;