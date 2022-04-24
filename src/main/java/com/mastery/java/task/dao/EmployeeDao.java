package com.mastery.java.task.dao;

import com.mastery.java.task.dto.Employee;
import java.util.List;

public interface EmployeeDao {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId);

    Employee createNewEmployee(Employee newEmployee);

    Employee updateEmployee(Employee updateEmployee);

    void deleteEmployeeById(Long employeeId);
}
