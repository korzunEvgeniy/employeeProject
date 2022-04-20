package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDaoImpl;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDaoImpl employeeDaoImpl;

    @Autowired
    public EmployeeServiceImpl(EmployeeDaoImpl employeeDaoImpl) {
        this.employeeDaoImpl = employeeDaoImpl;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDaoImpl.getAllEmployees();
    }

    @Override
    public Employee getEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        try {
            return employeeDaoImpl.getEmployeeById(employeeId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public Employee createNewEmployee(Employee newEmployee) {
        return employeeDaoImpl.createNewEmployee(newEmployee);
    }

    @Override
    public Employee updateEmployee(Long employeeId, Employee updatedEmployee)
            throws EmployeeNotFoundException {
        try {
            return employeeDaoImpl.updateEmployee(employeeId, updatedEmployee);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }

    @Override
    public void deleteEmployeeById(Long employeeId) throws EmployeeNotFoundException {
        try {
            employeeDaoImpl.deleteEmployeeById(employeeId);
        } catch (Exception e) {
            throw new EmployeeNotFoundException(employeeId);
        }
    }
}