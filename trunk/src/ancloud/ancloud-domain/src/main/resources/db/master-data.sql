INSERT INTO role(code) VALUES('administrator');
INSERT INTO permission(code) VALUES('account_search');
INSERT INTO permission(code) VALUES('account_register');
INSERT INTO permission(code) VALUES('account_delete');
INSERT INTO permission(code) VALUES('account_modify');
INSERT INTO permission(code) VALUES('dashboard');

INSERT INTO rolePermission(roleCode,permissionCode) VALUES('administrator','dashboard');
INSERT INTO accountPermission(userName,permissionCode) VALUES('admin','dashboard');

INSERT INTO account(id,userName,password,accountNonExpired,accountNonLocked,credentialsNonExpired,accountStatus,createddate,lastupdateddate,accountType,version) VALUES(1,'admin','$2a$08$J8NKSZF0g08tCUZfS01DcOBhVRyJvsPuSQGIDhF.Ct3/CDUNAhd/a',true,true,true,0,'2016-01-01 00:00:00','2016-01-01 00:00:00',0,0);
INSERT INTO accountRole(userName,roleCode) VALUES('admin','administrator');
INSERT INTO account(id,userName,password,accountNonExpired,accountNonLocked,credentialsNonExpired,accountStatus,createddate,lastupdateddate,accountType,version) VALUES(11,'administrator','$2a$08$J8NKSZF0g08tCUZfS01DcOBhVRyJvsPuSQGIDhF.Ct3/CDUNAhd/a',true,true,true,0,'2016-01-01 00:00:00','2016-01-01 00:00:00',0,0);
INSERT INTO accountRole(userName,roleCode) VALUES('administrator','administrator');

INSERT INTO resource(category, resourceKey,messageKey, value, resourceGroup) VALUES('language','en_US','','English','flag-icon-us');
INSERT INTO resource(category, resourceKey,messageKey, value, resourceGroup) VALUES('language','vi_VN','','Vietnamese','flag-icon-vn');
