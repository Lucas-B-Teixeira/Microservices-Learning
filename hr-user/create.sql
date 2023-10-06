create table role (id bigserial not null, role_name varchar(255), primary key (id));
create table tb_user (id bigserial not null, email varchar(255) unique, name varchar(255), password varchar(255), primary key (id));
create table user_role (role_id bigint not null, user_id bigint not null, primary key (role_id, user_id));
alter table if exists user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role;
alter table if exists user_role add constraint FK430om9qnxgilp5cvcbeyovi37 foreign key (user_id) references tb_user;
