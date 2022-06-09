-- TRUNCATE employee CASCADE;
-- TRUNCATE gender CASCADE;

INSERT INTO gender (name)
VALUES
    ('MALE'),
    ('FEMALE');

INSERT INTO employee (first_name, last_name, job_title, gender_id, date_of_birth)
VALUES
    ('Irina', 'Kravec', 'designer', (SELECT id FROM gender WHERE name = 'FEMALE'), '1990-06-05'),
    ('Polina', 'Filipova', 'engineer', (SELECT id FROM gender WHERE name = 'FEMALE'), '1991-04-23'),
    ('Kris', 'DeBurg', 'developer', (SELECT id FROM gender WHERE name = 'MALE'), '1980-05-20'),
    ('Lara', 'Fabian', 'developer', (SELECT id FROM gender WHERE name = 'FEMALE'), '1990-07-20'),
    ('James', 'Bond', 'engineer', (SELECT id FROM gender WHERE name = 'MALE'), '1987-04-12'),
    ('Jonny', 'Depp', 'developer', (SELECT id FROM gender WHERE name = 'MALE'), '1978-06-30'),
    ('Sveta', 'Shein', 'designer', (SELECT id FROM gender WHERE name = 'FEMALE'), '1995-05-25'),
    ('Alex', 'Bolduin', 'developer', (SELECT id FROM gender WHERE name = 'MALE'), '1980-12-27'),
    ('Bob', 'Marley', 'engineer', (SELECT id FROM gender WHERE name = 'MALE'), '1985-04-05'),
    ('Evgeniy', 'Korzun', 'developer', (SELECT id FROM gender WHERE name = 'MALE'), '1987-06-24');