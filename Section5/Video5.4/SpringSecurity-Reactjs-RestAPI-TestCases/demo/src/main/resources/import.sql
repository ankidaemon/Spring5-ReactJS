drop table User;
drop table Admin_User;

create table IF NOT EXISTS User (ID integer not null auto_increment, USER_NAME varchar(255), PHONE varchar(20), primary key (ID));

create table IF NOT EXISTS Admin_User (ID integer not null auto_increment, USER_NAME varchar(50), PASSWORD varchar(50), ROLES varchar(50), primary key (ID));
      
insert into User values(1,'test1','9876543210');
insert into Admin_User values(1,'admin','adminPassword','ROLE_ADMIN');
insert into Admin_User values(2,'chief','chiefPassword','ROLE_CHIEF');

commit;