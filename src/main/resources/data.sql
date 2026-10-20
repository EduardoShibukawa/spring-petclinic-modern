INSERT INTO specialties (id, name) VALUES (1, 'radiology');
INSERT INTO specialties (id, name) VALUES (2, 'surgery');
INSERT INTO specialties (id, name) VALUES (3, 'dentistry');

INSERT INTO vets (id, first_name, last_name) VALUES (1, 'James', 'Carter');
INSERT INTO vets (id, first_name, last_name) VALUES (2, 'Helen', 'Leary');
INSERT INTO vets (id, first_name, last_name) VALUES (3, 'Linda', 'Douglas');

INSERT INTO vet_specialties (vet_id, specialty_id) VALUES (2, 1);
INSERT INTO vet_specialties (vet_id, specialty_id) VALUES (3, 2);
INSERT INTO vet_specialties (vet_id, specialty_id) VALUES (3, 3);

INSERT INTO types (id, name) VALUES (1, 'cat');
INSERT INTO types (id, name) VALUES (2, 'dog');
INSERT INTO types (id, name) VALUES (3, 'lizard');
