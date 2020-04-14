create table user_role
(
    userid serial not null,
    roles  varchar(255)
);
create table usr
(
    userid   serial not null,
    active boolean not null,
    login    varchar(255),
    password varchar(255),
    primary key (userid)
);
alter table user_role
    add constraint UserRoleFK foreign key (userid) references usr;