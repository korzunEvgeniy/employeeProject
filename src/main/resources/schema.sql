-- DROP TABLE IF EXISTS employees;
-- DROP DATABASE IF EXISTS employeedb;

CREATE DATABASE emploeeydb;

CREATE TABLE IF NOT EXISTS employees (
        employee_id BIGSERIAL PRIMARY KEY,
        first_name VARCHAR(20) NOT NULL,
        last_name VARCHAR(20) NOT NULL,
        department_id BIGINT NOT NULL,
        job_title VARCHAR(20) NOT NULL,
        gender BIGINT NOT NULL,
        date_of_birth VARCHAR(20) NOT NULL,
        deleted BOOLEAN DEFAULT false NOT NULL
);