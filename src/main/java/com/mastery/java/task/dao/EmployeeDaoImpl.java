package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query("SELECT * FROM employee",
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return jdbcTemplate.query("SELECT * FROM employee WHERE employee_id=?",
                new BeanPropertyRowMapper<>(Employee.class), employeeId)
                .stream().findAny().orElseThrow();
    }

    @Override
    public Employee createNewEmployee(Employee newEmployee) {
        jdbcTemplate.update("INSERT INTO employee (first_name, last_name, job_title, gender, date_of_birth) " +
                        "VALUES(?, ?, ?, ?, ?)",
                newEmployee.getFirstName(), newEmployee.getLastName(),
                newEmployee.getJobTitle(), newEmployee.getGender().name(), newEmployee.getDateOfBirth());
        return newEmployee;
    }

    @Override
    public Employee updateEmployee(Employee updateEmployee) {
        int numOfRowsAffected = jdbcTemplate.update("UPDATE employee SET first_name=?, last_name=?, " +
                "job_title=?, gender=?, date_of_birth=? " +
                "                WHERE employee_id=?",
                updateEmployee.getFirstName(),
                updateEmployee.getLastName(),
                updateEmployee.getJobTitle(),
                updateEmployee.getGender().name(),
                updateEmployee.getDateOfBirth(), updateEmployee.getEmployeeId());
        if(numOfRowsAffected != 0) {
            return updateEmployee;
        } else {
            throw new EmployeeNotFoundException(updateEmployee.getEmployeeId());
        }
    }

    @Override
    public void deleteEmployeeById(Long employeeId) {
        int numOfRowsAffected = jdbcTemplate.update("DELETE FROM employee " +
                "WHERE employee_id=?", employeeId);
        if(numOfRowsAffected == 0) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }
}