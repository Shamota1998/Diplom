create table orders
(
    order_id serial not null,
    date     date not null default CURRENT_DATE,
    user_id  int4,
    primary key (order_id)
);

create table order_device
(
    order_id int4 not null,
    dev_id   int4 not null,

    primary key (order_id, dev_id)
);

alter table order_device
    add constraint ODeviceFK foreign key (dev_id) references device;
alter table order_device
    add constraint ODevice1FK foreign key (order_id) references orders;
alter table orders
    add constraint Usr2FK foreign key (user_id) references usr;



