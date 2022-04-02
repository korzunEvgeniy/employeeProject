package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM employee",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    public Employee getEmployeeById(Long employeeId) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE employee_id=?",
                new BeanPropertyRowMapper<>(Employee.class), employeeId)
                .stream().findAny().orElse(null);
    }

    public Employee createNewEmployee(Employee newEmployee) {
        jdbcTemplate.update("INSERT INTO employee (first_name, last_name, job_title, gender, date_of_birth) VALUES(?, ?, ?, ?, ?)",
                newEmployee.getFirstName(), newEmployee.getLastName(),
                newEmployee.getJobTitle(), newEmployee.getGender().name(), newEmployee.getDateOfBirth());
        return newEmployee;
    }

    public Employee updateEmployee(Long employeeId, Employee updateEmployee) {
        jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, " +
                "job_title=?, gender=?, date_of_birth=? " +
                "                WHERE employee_id=?",
                updateEmployee.getFirstName(),
                updateEmployee.getLastName(),
                updateEmployee.getJobTitle(),
                updateEmployee.getGender().name(),
                updateEmployee.getDateOfBirth(), employeeId);
        return updateEmployee;
    }

    public void deleteEmployeeById(Long employeeId) {
        jdbcTemplate.update("DELETE FROM employee WHERE employee_id=?", employeeId);
    }
}
