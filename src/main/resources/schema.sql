-- DROP TABLE IF EXISTS employee;
-- DROP TABLE IF EXISTS type_gender;
-- DROP DATABASE IF EXISTS employeedb;

CREATE DATABASE emploeeydb;

CREATE TABLE IF NOT EXISTS gender (
        id BIGSERIAL PRIMARY KEY,
        name VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS employee (
        employee_id BIGSERIAL PRIMARY KEY,
        first_name VARCHAR(20) NOT NULL,
        last_name VARCHAR(20) NOT NULL,
        department_id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY
            ( INCREMENT 1 START 201 MINVALUE 1 MAXVALUE 1201 CACHE 1 ),
        job_title VARCHAR(20) NOT NULL,
        gender_id BIGINT REFERENCES gender,
        date_of_birth VARCHAR(20) NOT NULL,
        deleted BOOLEAN DEFAULT false NOT NULL
);