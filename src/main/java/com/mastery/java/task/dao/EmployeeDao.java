package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

//    public Employee getEmployeeById(Long employeeId) {
//        return jdbcTemplate.query("SELECT * FROM employee WHERE employeeId=?",
//                new Object[]{employeeId}, new BeanPropertyRowMapper<>(Employee.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public Employee createNewEmployee(Employee newEmployee) {
//        jdbcTemplate.update("INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?)",
//                newEmployee.getFirstName(), newEmployee.getLastName(), newEmployee.getDepartmentId(),
//                newEmployee.getJobTitle(), newEmployee.getGender(), newEmployee.getDateOfBirth());
//        return newEmployee;
//    }
//
//    public Employee updateEmployee(Long employeeId, Employee updateEmployee) {
//        jdbcTemplate.update("UPDATE employee SET firstName=?, lastName=?, departmentId=?, " +
//                        "jobTitle=?, gender=?, dateOfBirth=?",
//                updateEmployee.getFirstName(),
//                updateEmployee.getLastName(), updateEmployee.getDepartmentId(), updateEmployee.getJobTitle(),
//                updateEmployee.getGender(), updateEmployee.getDateOfBirth(), employeeId);
//        return updateEmployee;
//    }
//
//    public void deleteEmployeeById(Long employeeId) {
//        jdbcTemplate.update("DELETE FROM employee WHERE employeeId=?", employeeId);
//    }
}
