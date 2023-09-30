INSERT INTO tb_user (name, email, password) VALUES ('Nina Brown', 'nina@gmail.com', '$2a$10$n4Vby0cpgV1Fn9QpLR6v7uzHuTlUOM6mLOSPVF9NaCdJTNlEAjQOS');
INSERT INTO tb_user (name, email, password) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$n4Vby0cpgV1Fn9QpLR6v7uzHuTlUOM6mLOSPVF9NaCdJTNlEAjQOS');

INSERT INTO role (role_name) VALUES ('ROLE_OPERATOR');
INSERT INTO role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);