INSERT INTO bb_project(id,name) VALUES(0,'Root project');
INSERT INTO bb_account(id,userName,title,password,accountNonExpired,accountNonLocked,credentialsNonExpired,enabled,createddate,lastupdateddate) VALUES(1,'admin','Administrator','$2a$04$FQyfe75OhZGlwnm7w/R76.9xtKmLu0MZYn22oRGVMShzFKu5N0PKS',true,true,true,true,'2016-01-01 00:00:00','2016-01-01 00:00:00');

INSERT INTO bb_role(roleCode) VALUES('administrator');
INSERT INTO bb_permission(permissionCode) VALUES('dashboard');
INSERT INTO bb_rolePermission(roleCode,permissionCode) VALUES('administrator','dashboard');
INSERT INTO bb_accountrole(userName,roleCode) VALUES('admin','administrator');
INSERT INTO bb_accountpermission(userName,permissionCode) VALUES('admin','dashboard');

/* Navigation Links */

INSERT INTO bb_navigationlink(messageKey,code,icon,path,project_id,parent_code,groupid,groupindex,itemindex,version) VALUES('sc.common.00011','sc.common.00011','fa fa-gears','',0,null,'','1',1,0);
INSERT INTO bb_navigationlink(messageKey,code,icon,path,project_id,parent_code,groupid,groupindex,itemindex,version) VALUES('sc.navigationlink.00001','sc.navigationlink.00001','fa fa-link','/navigation-link',0,'sc.common.00011','1','1.1',2,0);
INSERT INTO bb_navigationlink(messageKey,code,icon,path,project_id,parent_code,groupid,groupindex,itemindex,version) VALUES('sc.message.00001','sc.message.00001','fa fa-database','/message',0,'sc.common.00011','1','1.2',3,0);
INSERT INTO bb_navigationlink(messageKey,code,icon,path,project_id,parent_code,groupid,groupindex,itemindex,version) VALUES('sc.account.00001','sc.account.00001','fa fa-user','/account',0,'sc.common.00011','1','1.3',4,0);

/*resources */


INSERT INTO bb_resource(category, key,messageKey, value, resourceGroup) VALUES('language','en_US','','English','flag-icon-us');
INSERT INTO bb_resource(category, key,messageKey, value, resourceGroup) VALUES('language','vi_VN','','Vietnamese','flag-icon-vn');
/* Messages */


INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES('mod.sys','mod','en','US','','System');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES('mod.sys','mod','vi','VN','','Hệ thống');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'err.sys.00001','err','en','US','','Invalid username, email or password');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00001','sc','en','US','','Ejk');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00001','sc','vi','VN','','Ejk');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00002','sc','en','US','','Ejk');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00002','sc','vi','VN','','Ejk');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00003','sc','en','US','','Sign in');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00004','sc','en','US','','Sign out');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00005','sc','en','US','','Forgot password?');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00006','sc','en','US','','Register now');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00007','sc','en','US','','Don''t have a membership yet?');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00008','sc','en','US','','Remember me');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00009','sc','en','US','','Sign in using Facebook');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00010','sc','en','US','','Sign up using Facebook');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00011','sc','en','US','','- OR -');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00012','sc','en','US','','Full name');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00013','sc','en','US','','Email');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00014','sc','en','US','','Password');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00015','sc','en','US','','Retype password');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00016','sc','en','US','','I agree to the');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00017','sc','en','US','','terms');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00018','sc','en','US','','Sign up');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00019','sc','en','US','','I already have a membership');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.sys.00020','sc','en','US','','Member since');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00001','sc','en','US','','New');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00001','sc','vi','VN','','Xin chào!');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00002','sc','en','US','','Edit');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00003','sc','en','US','','Delete');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00004','sc','en','US','','Search');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00005','sc','en','US','','List all');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00006','sc','en','US','','Search result');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00007','sc','en','US','','No.');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00008','sc','en','US','','Search information');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00009','sc','en','US','','Save');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00010','sc','en','US','','Edit form');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.common.00011','sc','en','US','','Management');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.navigationlink.00001','sc','en','US','','Navigation link');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.navigationlink.00001','sc','vi','VN','','Xin chào!');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.navigationlink.00002','sc','en','US','','Path');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.message.00001','sc','en','US','','Message');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.message.00001','sc','vi','VN','','Xin chào!');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.message.00002','sc','en','US','','Search message');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.message.00003','sc','en','US','','Message code');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00001','sc','en','US','','Account management');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00001','sc','vi','VN','','Xin chào!');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00002','sc','en','US','','Search account');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00003','sc','en','US','','Modify account');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00004','sc','en','US','','Delete account');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00005','sc','en','US','','Update account');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00006','sc','en','US','','User name');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00007','sc','en','US','','Password');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00008','sc','en','US','','Account none expired');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00009','sc','en','US','','Account none locked');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00010','sc','en','US','','Credentials none expired');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00011','sc','en','US','','Enabled');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00012','sc','en','US','','Account search result');
INSERT INTO bb_message(project_id,key, basename, language, country, variant, message) VALUES(0,'sc.account.00013','sc','en','US','','Profile');