package com.mastery.java.task.service;

import com.mastery.java.task.dao.entity.Employee;
import java.util.List;

public interface EmployeeService {

    Employee get(Long key);

    List<Employee> getAll();

    Employee create(Employee employee);

    Employee update(Employee employee);

    void delete(Long key);
}
