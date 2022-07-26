package com.mastery.java.task.dao;

import com.mastery.java.task.dao.entity.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> getAll();

    Employee get(Long employeeId);

    Employee create(Employee newEmployeeDto);

    Employee update(Employee updateEmployee);

    void delete(Long employeeId);
}