package com.mastery.java.task.dao;

import com.mastery.java.task.dao.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}