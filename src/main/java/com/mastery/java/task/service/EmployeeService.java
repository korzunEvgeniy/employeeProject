package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDao;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.util.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        try {
            employeeDao.getEmployeeById(employeeId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " is not exist");
        }
        return employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public Employee createNewEmployee(Employee newEmployee) {
        return employeeDao.createNewEmployee(newEmployee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee updatedEmployee)
            throws EmployeeNotFoundException {
        try {
            employeeDao.updateEmployee(employeeId, updatedEmployee);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " is not exist");
        }
        return employeeDao.updateEmployee(employeeId, updatedEmployee);
    }

    @Override
    public void deleteEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        try {
            employeeDao.deleteEmployeeById(employeeId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException("Employee with id " + employeeId + " is not exist");
        }
    }
}