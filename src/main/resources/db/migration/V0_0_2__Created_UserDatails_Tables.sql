create table address
(
    addrid       serial not null,
    address_desc varchar(255),
    cityid       int4,
    primary key (addrid)
);
create table city
(
    cityid    serial not null,
    city_desc varchar(255),
    countryid int4,
    primary key (cityid)
);
create table country
(
    country_id   serial not null,
    country_desc varchar(255),
    primary key (country_id)
);

create table user_details
(
    id         serial not null,
    first_name varchar(255),
    last_name  varchar(255),
    addrid     int4,
    userid     int4,
    primary key (id)
);

alter table address
    add constraint AddressFK foreign key (cityid) references city;
alter table city
    add constraint CityFK foreign key (countryid) references country;
alter table user_details
    add constraint UserDetailsFK1 foreign key (addrid) references address;
alter table user_details
    add constraint UserDetailsFK2 foreign key (userid) references usr;