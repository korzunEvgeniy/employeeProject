package com.mastery.java.task.service;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException;

    Employee createNewEmployee(Employee newEmployee);

    Employee updateEmployee(Employee updatedEmployee) throws EmployeeNotFoundException;

    void deleteEmployeeById(Long employeeId) throws EmployeeNotFoundException;
}
