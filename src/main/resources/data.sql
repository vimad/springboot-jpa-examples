-- noinspection SqlDialectInspectionForFile

-- noinspection SqlNoDataSourceInspectionForFile

-- Fetch Nested db
INSERT INTO reservation (reservation_id, category) VALUES (1L, 'APPOINTMENT');
INSERT INTO reservation (reservation_id, category) VALUES (2L, 'APPOINTMENT');
INSERT INTO reservation (reservation_id, category) VALUES (3L, 'OPERATING_ROOM');
INSERT INTO reservation (reservation_id, category) VALUES (4L, 'OPERATING_ROOM');

INSERT INTO patient (patient_id, first_name, age, reservation_id) VALUES (1L, 'vinod', 25, 1L);
INSERT INTO patient (patient_id, first_name, age, reservation_id) VALUES (2L, 'tishan', 22, 2L);
INSERT INTO patient (patient_id, first_name, age, reservation_id) VALUES (3L, 'shehan', 20, 3L);
INSERT INTO patient (patient_id, first_name, age, reservation_id) VALUES (4L, 'omal', 35, 4L);

INSERT INTO hierarchy (hierarchy_id, hierarchy_type, parent_resource_hierarchy_id) VALUES (1L, 'HOSPITAL', null);
INSERT INTO hierarchy (hierarchy_id, hierarchy_type, parent_resource_hierarchy_id) VALUES (2L, 'BUILDING', 1L);
INSERT INTO hierarchy (hierarchy_id, hierarchy_type, parent_resource_hierarchy_id) VALUES (3L, 'ROOM', 2L);
INSERT INTO hierarchy (hierarchy_id, hierarchy_type, parent_resource_hierarchy_id) VALUES (4L, 'ROOM', 3L);

INSERT INTO resource (resource_id, resource_type, hierarchy_hierarchy_id, reservation_id) VALUES (1L, 'DOCTOR', 1L, 1L);
INSERT INTO resource (resource_id, resource_type, hierarchy_hierarchy_id, reservation_id) VALUES (2L, 'DOCTOR', 1L, 2L);
INSERT INTO resource (resource_id, resource_type, hierarchy_hierarchy_id, reservation_id) VALUES (3L, 'OPERATING_ROOM', 2L, 3L);
INSERT INTO resource (resource_id, resource_type, hierarchy_hierarchy_id, reservation_id) VALUES (4L, 'OPERATING_ROOM', 3L, 4L);

-- ************************************************
-- Common Entity db
insert into course(id, name, created_date, last_updated_date,is_deleted) values(10001,'Maths', sysdate(), sysdate(),false);
insert into course(id, name, created_date, last_updated_date,is_deleted) values(10002,'Physics', sysdate(), sysdate(),false);
insert into course(id, name, created_date, last_updated_date,is_deleted) values(10003,'Chemistry', sysdate(), sysdate(),false);


insert into passport(id,number) values(40001,'E123456');
insert into passport(id,number) values(40002,'N123457');
insert into passport(id,number) values(40003,'L123890');

insert into student(id,name,passport_id) values(20001,'Vinod',40001);
insert into student(id,name,passport_id) values(20002,'Tishan',40002);
insert into student(id,name,passport_id) values(20003,'Shehan',40003);

insert into review(id,rating,description,course_id) values(50001,'FIVE', 'Great Course',10001);
insert into review(id,rating,description,course_id) values(50002,'FOUR', 'Wonderful Course',10001);
insert into review(id,rating,description,course_id) values(50003,'FIVE', 'Awesome Course',10003);

insert into student_course(student_id,course_id) values(20001,10001);
insert into student_course(student_id,course_id) values(20002,10001);
insert into student_course(student_id,course_id) values(20003,10001);
insert into student_course(student_id,course_id) values(20001,10003);
