create table category
(
    cat_id   serial not null,
    cat_desc varchar(255),
    primary key (cat_id)
);

create table device
(
    id          serial not null,
    qty         int4,
    device_desc varchar(255),
    price       float4,
    category_id int4,
    producer_id int4,
    primary key (id)
);
create table producer
(
    prod_id   serial not null,
    firm_desc varchar(255),
    primary key (prod_id)
);

alter table device
    add constraint DeviceFK1 foreign key (category_id) references category;
alter table device
    add constraint DeviceFK2 foreign key (producer_id) references producer;
