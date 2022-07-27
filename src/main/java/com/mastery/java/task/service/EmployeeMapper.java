package com.mastery.java.task.service;

import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.dto.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public EmployeeDto toDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setDepartmentId(employee.getDepartmentId());
        employeeDto.setJobTitle(employee.getJobTitle());
        employeeDto.setGender(EmployeeDto.Gender.valueOf(employee.getGender().toString()));
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        return employeeDto;
    }

    public Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDto.getEmployeeId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setDepartmentId(employeeDto.getDepartmentId());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setGender(Employee.Gender.valueOf(employeeDto.getGender().toString()));
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        return employee;
    }
}
