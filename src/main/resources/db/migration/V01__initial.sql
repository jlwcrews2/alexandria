create table if not exists book(
    book_id bigint primary key,
    title varchar(100),
    status varchar(20),
    location_id bigint,
    publisher varchar(100)
);

create sequence book_seq;

create table if not exists author(
    author_id bigint primary key,
    author_firstname varchar(50),
    author_lastname varchar(50)
);

create sequence author_seq;

create table if not exists location(
    location_id bigint primary key,
    location varchar(250)
);

create sequence location_seq;

create table if not exists book_author(
    book_id bigint,
    author_id bigint
);
