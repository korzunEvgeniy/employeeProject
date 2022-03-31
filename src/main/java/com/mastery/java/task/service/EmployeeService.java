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

//    public Employee getEmployeeById(Long id) {
//        return employeeDao.getEmployeeById(id);
//    }
//
//    public Employee createNewEmployee(Employee newEmployee) {
//        return employeeDao.createNewEmployee(newEmployee);
//    }
//
//    public Employee updateEmployee(Long id, Employee updatedEmployee) {
//        return employeeDao.updateEmployee(id, updatedEmployee);
//    }
//
//    public void deleteEmployeeById(Long id) {
//        employeeDao.deleteEmployeeById(id);
//    }
}
