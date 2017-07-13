create table account (id bigint not null auto_increment, code varchar(255), createdDate datetime, lastUpdatedDate datetime, name varchar(255), version bigint, accountNonExpired bit not null, accountNonLocked bit not null, accountStatus integer, accountType integer, birthday datetime, contactNumber varchar(255), credentialsNonExpired bit not null, email varchar(255), password varchar(255) not null, title varchar(255), userName varchar(255) not null, createdBy_id bigint, lastUpdatedBy_id bigint, approver_id bigint, primary key (id));
create table accountActivity (discriminator varchar(31) not null, id bigint not null auto_increment, operationTimeRecord datetime, authenticationType integer, operationAccount_id bigint, primary key (id));
create table accountPermission (userName varchar(255) not null, permissionCode varchar(255) not null, primary key (userName, permissionCode));
create table accountProfile (id bigint not null auto_increment, code varchar(255), createdDate datetime, lastUpdatedDate datetime, name varchar(255), version bigint, dateFormat varchar(255), dateTimeFormat varchar(255), locale varchar(255), createdBy_id bigint, lastUpdatedBy_id bigint, parent_id bigint, primary key (id));
create table accountRole (userName varchar(255) not null, roleCode varchar(255) not null, primary key (userName, roleCode));
create table message (id bigint not null auto_increment, basename varchar(255), country varchar(255), language varchar(255), message varchar(255), messageKey varchar(255), variant varchar(255), primary key (id));
create table permission (id bigint not null auto_increment, code varchar(255), createdDate datetime, lastUpdatedDate datetime, name varchar(255), version bigint, createdBy_id bigint, lastUpdatedBy_id bigint, primary key (id));
create table resource (id bigint not null auto_increment, category varchar(255), messageKey varchar(255), resourceGroup varchar(255), resourceKey varchar(255), value varchar(255), primary key (id));
create table role (id bigint not null auto_increment, code varchar(255), createdDate datetime, lastUpdatedDate datetime, name varchar(255), version bigint, createdBy_id bigint, lastUpdatedBy_id bigint, primary key (id));
create table rolePermission (roleCode varchar(255) not null, permissionCode varchar(255) not null, primary key (roleCode, permissionCode));
alter table account add constraint UK_g1qbg12c961rxc4xtxobfat84 unique (userName);
alter table account add constraint UK_k9qlqijt38kmryafdhhq04lon unique (code);
alter table account add constraint UK_q0uja26qgu1atulenwup9rxyr unique (email);
alter table accountProfile add constraint UK_22mol2amuhdew6ipp8mv4ex3i unique (code);
alter table message add constraint UKoxpq1hiqswaoxfxjnvvip10yp unique (messageKey, language, country);
alter table permission add constraint UK_a7ujv987la0i7a0o91ueevchc unique (code);
alter table resource add constraint UK_tkbnx3c9b0miuemjjbceuvm57 unique (resourceKey);
alter table role add constraint UK_c36say97xydpmgigg38qv5l2p unique (code);
alter table account add constraint FKbduwhcaqk7dsmqb3lsgup073x foreign key (createdBy_id) references account (id);
alter table account add constraint FK5jwl78hlxdbpuar9ly73ry0s6 foreign key (lastUpdatedBy_id) references account (id);
alter table account add constraint FKelfmcfrn3evdd7jysvecn5200 foreign key (approver_id) references account (id);
alter table accountActivity add constraint FKohsl8obeslpftxg764baossu7 foreign key (operationAccount_id) references account (id);
alter table accountProfile add constraint FKrjh2442sqmj8ewonf6f3xklju foreign key (createdBy_id) references account (id);
alter table accountProfile add constraint FKxjg0mv4louarcdbbsw0n41kd foreign key (lastUpdatedBy_id) references account (id);
alter table accountProfile add constraint FKt8fl1c71kvd06nryasl629ktu foreign key (parent_id) references account (id);
alter table permission add constraint FKt78va6b8et01e93noe1txfbaj foreign key (createdBy_id) references account (id);
alter table permission add constraint FKjpik92mgn7i9lt7guqmat2wq5 foreign key (lastUpdatedBy_id) references account (id);
alter table role add constraint FKmok21gm6m5jyp5kqcenhl3dqi foreign key (createdBy_id) references account (id);
alter table role add constraint FKsew11tht69uvgfkw450hxp45a foreign key (lastUpdatedBy_id) references account (id);

INSERT INTO role(code) VALUES('administrator');
INSERT INTO permission(code) VALUES('account_search');
INSERT INTO permission(code) VALUES('account_register');
INSERT INTO permission(code) VALUES('account_delete');
INSERT INTO permission(code) VALUES('account_modify');

INSERT INTO rolePermission(roleCode,permissionCode) VALUES('administrator','dashboard');
INSERT INTO accountPermission(userName,permissionCode) VALUES('admin','dashboard');

INSERT INTO account(id,userName,title,password,accountNonExpired,accountNonLocked,credentialsNonExpired,accountStatus,createddate,lastupdateddate,accountType,version) VALUES(1,'admin','Administrator','$2a$08$J8NKSZF0g08tCUZfS01DcOBhVRyJvsPuSQGIDhF.Ct3/CDUNAhd/a',true,true,true,0,'2016-01-01 00:00:00','2016-01-01 00:00:00',0,0);
INSERT INTO accountRole(userName,roleCode) VALUES('admin','administrator');
INSERT INTO account(id,userName,title,password,accountNonExpired,accountNonLocked,credentialsNonExpired,accountStatus,createddate,lastupdateddate,accountType,version) VALUES(11,'administrator','Administrator','$2a$08$J8NKSZF0g08tCUZfS01DcOBhVRyJvsPuSQGIDhF.Ct3/CDUNAhd/a',true,true,true,0,'2016-01-01 00:00:00','2016-01-01 00:00:00',0,0);
INSERT INTO accountRole(userName,roleCode) VALUES('administrator','administrator');

INSERT INTO resource(category, resourceKey,messageKey, value, resourceGroup) VALUES('language','en_US','','English','flag-icon-us');
INSERT INTO resource(category, resourceKey,messageKey, value, resourceGroup) VALUES('language','vi_VN','','Vietnamese','flag-icon-vn');
