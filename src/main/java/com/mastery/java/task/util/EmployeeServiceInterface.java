package com.mastery.java.task.util;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import java.util.List;

public interface EmployeeServiceInterface {

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException;

    public Employee createNewEmployee(Employee newEmployee);

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) throws EmployeeNotFoundException;

    public void deleteEmployeeById(Long employeeId) throws EmployeeNotFoundException;
}
