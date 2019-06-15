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

