package com.mastery.java.task.util;

import com.mastery.java.task.dto.Employee;
import java.util.List;

public interface EmployeeDaoInterface {

    public List<Employee> getAllEmployees();

    public Employee getEmployeeById(Long employeeId);

    public Employee createNewEmployee(Employee newEmployee);

    public Employee updateEmployee(Long employeeId, Employee updateEmployee);

    public void deleteEmployeeById(Long employeeId);
}
