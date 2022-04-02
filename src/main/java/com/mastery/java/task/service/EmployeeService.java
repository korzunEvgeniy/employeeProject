package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee getEmployeeById(Long employeeId) {
//        if(employeeId == null) {
//            new EmployeeNotFoundException("This ID is not exist");
//        } else
//        employeeDao.getEmployeeById(employeeId).stream().findAny().orElse(() ->
//                new EmployeeNotFoundException("This ID is not exist"));
        return employeeDao.getEmployeeById(employeeId);
    }

    public Employee createNewEmployee(Employee newEmployee) {
        return employeeDao.createNewEmployee(newEmployee);
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) {
        return employeeDao.updateEmployee(employeeId, updatedEmployee);
    }

    public void deleteEmployeeById(Long employeeId) {
        employeeDao.deleteEmployeeById(employeeId);
    }
}