INSERT INTO bb_account(id,userName,password,accountNonExpired,accountNonLocked,credentialsNonExpired,enabled) VALUES(1,'admin','$2a$04$FQyfe75OhZGlwnm7w/R76.9xtKmLu0MZYn22oRGVMShzFKu5N0PKS',true,true,true,true);

INSERT INTO bb_role(roleCode) VALUES('administrator');
INSERT INTO bb_permission(permissionCode) VALUES('dashboard');
INSERT INTO bb_rolePermission(roleCode,permissionCode) VALUES('administrator','dashboard');
INSERT INTO bb_accountrole(userName,roleCode) VALUES('admin','administrator');
INSERT INTO bb_accountpermission(userName,permissionCode) VALUES('admin','dashboard');

# Messages
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00001','sc','en','US','','Ejukate');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00002','sc','en','US','','Eju');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00003','sc','en','US','','Sign in');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00004','sc','en','US','','Sign out');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00005','sc','en','US','','Forgot password');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00006','sc','en','US','','Register now');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00007','sc','en','US','','Don''t have a membership yet?');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00008','sc','en','US','','Remember me');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00009','sc','en','US','','Sign in using Facebook');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00010','sc','en','US','','Sign up using Facebook');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00011','sc','en','US','','- OR -');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00012','sc','en','US','','Full name');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00013','sc','en','US','','Email');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00014','sc','en','US','','Password');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00015','sc','en','US','','Retype password');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00016','sc','en','US','','I agree to the');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00017','sc','en','US','','terms');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00018','sc','en','US','','Sign up');
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('sc.sys.00019','sc','en','US','','I already have a membership');

# Errors
INSERT INTO bb_message(key, basename, language, country, variant, message) VALUES('err.sys.00001','err','en','US','','Invalid username, email or password');
