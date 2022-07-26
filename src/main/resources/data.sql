-- TRUNCATE employees CASCADE;
-- TRUNCATE gender CASCADE;

INSERT INTO gender (name)
VALUES
    ('MALE'),
    ('FEMALE');

INSERT INTO employees (first_name, last_name, job_title, department_id, gender_id, date_of_birth)
VALUES
    ('Irina', 'Kravec', 'designer', 1, (SELECT id FROM gender WHERE name = 'FEMALE'), '1990-06-05'),
    ('Polina', 'Filipova', 'engineer', 1, (SELECT id FROM gender WHERE name = 'FEMALE'), '1991-04-23'),
    ('Kris', 'DeBurg', 'developer', 1, (SELECT id FROM gender WHERE name = 'MALE'), '1980-05-20'),
    ('Lara', 'Fabian', 'developer', 1, (SELECT id FROM gender WHERE name = 'FEMALE'), '1990-07-20'),
    ('James', 'Bond', 'engineer', 2, (SELECT id FROM gender WHERE name = 'MALE'), '1987-04-12'),
    ('Jonny', 'Depp', 'developer', 2, (SELECT id FROM gender WHERE name = 'MALE'), '1978-06-30'),
    ('Sveta', 'Shein', 'designer', 2, (SELECT id FROM gender WHERE name = 'FEMALE'), '1995-05-25'),
    ('Alex', 'Bolduin', 'developer', 2, (SELECT id FROM gender WHERE name = 'MALE'), '1980-12-27'),
    ('Bob', 'Marley', 'engineer', 2, (SELECT id FROM gender WHERE name = 'MALE'), '1985-04-05'),
    ('Evgeniy', 'Korzun', 'developer', 2, (SELECT id FROM gender WHERE name = 'MALE'), '1987-06-24');