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


alter table article rename to blog_article;
alter table article_tag rename to blog_article_tag;
alter table note rename to blog_note;
alter table tag rename to blog_tag;


create table sys_user
(
    id          bigint auto_increment primary key,
    name        varchar(20)  not null
);


create table sys_user_oauth
(
    id          bigint auto_increment primary key,
    user_id        bigint  not null,
    platform        varchar(20)  not null,
    open_id        varchar(64)  not null
);

create table blog_tag_category
(
    id          bigint auto_increment primary key,
    name        varchar(32)  not null,
    description        varchar(255)  not null
);
