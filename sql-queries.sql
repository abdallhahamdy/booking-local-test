-- FOR ARABIC LANGUAGE
ALTER DATABASE Booking CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE city CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE roles CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


-- INSERT ROLES
insert into roles (arabic_nameو role) values ("نزيل" "ROLE_GUEST");
insert into roles (arabic_nameو role) values ("مؤجر", "ROLE_LESSOR");


-- insert cities
insert into city (arabic_name, city) values ("طرابلس","Tripoli");
insert into city (arabic_name, city) values ("بنغازي","Benghazi");
insert into city (arabic_name, city) values ("مصراتة","Misrata");


--
