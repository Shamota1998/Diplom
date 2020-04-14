create table cart
(
    cart_id serial not null,
    user_id int4,
    primary key (cart_id)
);

create table cart_device
(
    cart_id int4 not null,
    dev_id  int4 not null,
    primary key (cart_id, dev_id)
) ;


alter table cart
    add constraint UsrFK foreign key (user_id) references usr;
alter table cart_device
    add constraint DeviceFK foreign key (dev_id) references device;
alter table cart_device
    add constraint CartFK foreign key (cart_id) references cart;


