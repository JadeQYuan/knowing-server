create table article
(
    id      bigint auto_increment
        primary key,
    title   varchar(30)   not null,
    content varchar(4000) not null
);

create table article_tag
(
    article_id bigint not null,
    tag_id     bigint not null
);

create table note
(
    id      bigint auto_increment
        primary key,
    title   varchar(30)   not null,
    content varchar(5000) not null
);

create table tag
(
    id          bigint auto_increment
        primary key,
    name        varchar(15)  not null,
    description varchar(200) not null
);
