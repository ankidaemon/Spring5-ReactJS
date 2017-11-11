
drop table IF exists t_credential_roles;
drop table IF exists t_credential;

create table IF NOT EXISTS User (USER_ID integer not null auto_increment, phone varchar(255), USER_NAME varchar(20), primary key (USER_ID));

create table t_credential(username varchar(50) not null primary key, password varchar(50) not null,enabled boolean not null);

create table t_credential_roles (username varchar(50) not null, authority varchar(50) not null);      

ALTER TABLE t_credential_roles ADD FOREIGN KEY (username) REFERENCES t_credential(username);
      
create unique index ix_auth_username on t_credential_roles (username,authority);
      
insert into t_credential values('admin','adminPassword',true);
insert into t_credential values('employee','employeePassword',true);

insert into t_credential_roles values('admin','ROLE_ADMIN');
insert into t_credential_roles values('employee','ROLE_LOCAL');

commit;