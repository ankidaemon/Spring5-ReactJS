drop table IF exists t_credential_roles;
drop table IF exists t_credential;

create table t_credential(
      username varchar_ignorecase(50) not null primary key,
      password varchar_ignorecase(50) not null,
      enabled boolean not null);

create table t_credential_roles (
      username varchar_ignorecase(50) not null,
      authority varchar_ignorecase(50) not null);
      
ALTER TABLE t_credential_roles ADD FOREIGN KEY (username) REFERENCES t_credential(username);
      
create unique index ix_auth_username on t_credential_roles (username,authority);
      
insert into t_credential values('admin','adminPassword',true);
insert into t_credential values('employee','employeePassword',true);

insert into t_credential_roles values('admin','admin');
insert into t_credential_roles values('admin','local');
insert into t_credential_roles values('employee','local');


