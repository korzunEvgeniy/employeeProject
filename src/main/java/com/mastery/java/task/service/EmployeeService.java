package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException{
            try {
                employeeDao.getEmployeeById(employeeId);
            } catch (Exception e) {
                throw new EmployeeNotFoundException("Employee with id " + employeeId + " is not exist");
            }
        return employeeDao.getEmployeeById(employeeId);
    }

    public Employee createNewEmployee(Employee newEmployee) {
        return employeeDao.createNewEmployee(newEmployee);
    }

    public Employee updateEmployee(Long employeeId, Employee updatedEmployee) throws EmployeeNotFoundException{
        try {
            employeeDao.updateEmployee(employeeId, updatedEmployee);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " is not exist");
        }
        return employeeDao.updateEmployee(employeeId, updatedEmployee);
    }

    public void deleteEmployeeById(Long employeeId) throws EmployeeNotFoundException{
        try {
            employeeDao.deleteEmployeeById(employeeId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " is not exist");
        }
    }
}