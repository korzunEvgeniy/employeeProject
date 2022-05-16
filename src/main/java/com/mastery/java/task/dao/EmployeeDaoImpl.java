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
    public static final String GET_ALL = "SELECT e.employee_id, e.first_name, e.last_name, e.department_id, " +
            "e.job_title, g.name AS gender, e.date_of_birth " +
            "FROM employee e JOIN gender g ON e.gender_id = g.id " +
            "WHERE e.deleted = false";
    public static final String GET_BY_ID = "SELECT e.employee_id, e.first_name, e.last_name, e.department_id, " +
            "e.job_title, g.name AS gender, e.date_of_birth " +
            "FROM employee e JOIN gender g ON e.gender_id = g.id " +
            "WHERE e.employee_id = ? AND e.deleted=false";
    public static final String INSERT = "INSERT INTO employee (first_name, last_name, job_title, gender_id, date_of_birth) " +
            "VALUES (?, ?, ?, (SELECT id FROM gender WHERE name=?), ?)";
    public static final String UPDATE = "UPDATE employee SET first_name=?, last_name=?, job_title=?, " +
            "gender_id= (SELECT id FROM gender WHERE name=?), date_of_birth=? " +
            "WHERE employee_id = ? AND deleted = false";
    public static final String DELETE = "UPDATE employee SET deleted=true WHERE employee_id=? AND deleted=false";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return jdbcTemplate.query(GET_ALL,
                new BeanPropertyRowMapper<>(Employee.class));
    }

    @Override
    public Employee getEmployeeById(Long employeeId) {
        return jdbcTemplate.query(GET_BY_ID,
                new BeanPropertyRowMapper<>(Employee.class), employeeId)
                .stream().findAny().orElseThrow();
    }

    @Override
    public Employee createNewEmployee(Employee newEmployee) {
        jdbcTemplate.update(INSERT,
                newEmployee.getFirstName(), newEmployee.getLastName(),
                newEmployee.getJobTitle(), newEmployee.getGender().name(), newEmployee.getDateOfBirth());
        return newEmployee;
    }

    @Override
    public Employee updateEmployee(Employee updateEmployee) {
        int numOfRowsAffected = jdbcTemplate.update(UPDATE,
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
        int numOfRowsAffected = jdbcTemplate.update(DELETE, employeeId);
        if(numOfRowsAffected == 0) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }
}