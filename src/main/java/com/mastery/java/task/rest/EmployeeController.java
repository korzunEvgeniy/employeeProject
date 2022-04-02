package com.mastery.java.task.rest;

import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import com.mastery.java.task.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("getAll")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("getOne{employeeId}")
    public Employee getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("createEmployee")
    public Employee createNewEmployee(@RequestBody Employee newEmployee) {
        return employeeService.createNewEmployee(newEmployee);
    }

    @PutMapping("update{employeeId}")
    public Employee updateEmployee(@PathVariable Long employeeId,
    @RequestBody Employee updateEmployee) {
        return employeeService.updateEmployee(employeeId, updateEmployee);
    }

    @DeleteMapping("delete{employeeId}")
    public void deleteEmployeeById(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }




    //private Employee employee;

//    @Autowired
//    public EmployeeController(Employee employee) {
//        this.employee = employee;
//    }

//    public EmployeeController() {}
//
//    Employee e1 = new Employee(1, "A", "B", 1,
//            "dev", Gender.MALE, "1.1.2010");
//    Employee e2 = new Employee(2, "C", "D", 2,
//            "dev", Gender.FEMALE, "2.2.2010");
//    Employee e3 = new Employee(3, "E", "F", 3,
//            "dev", Gender.MALE, "3.3.2010");
//
//    List<Employee> listOfEmployees = new ArrayList<>();
//
//
//    @GetMapping
//    public List<Employee> getAllEmployees() {
//        listOfEmployees.add(e1);
//        listOfEmployees.add(e2);
//        listOfEmployees.add(e3);
//        return listOfEmployees;
//    }

//    @GetMapping("/{employeeId}")
//    public Employee getEmployeeById(@PathVariable Long employeeId) {
//        long index = employeeId;
//        Employee e = listOfEmployees.get((int) index - 1);
//        return e;
//    }

//        @PostMapping
//    public Employee createNewEmployee() {
//        Employee newEmployee = new Employee();
//        newEmployee.setFirstName("Evgeniy");
//        newEmployee.setLastName("Korzun");
//        newEmployee.setDepartmentId(5);
//        newEmployee.setJobTitle("engineer");
//        newEmployee.setGender(Gender.MALE);
//        newEmployee.setDateOfBirth("24.06.1987");
//        return newEmployee;
//    }

//        @PutMapping("/{employeeId}")
//    public Employee updateEmployee(@PathVariable Long employeeId) {
//        long index = employeeId;
//        Employee updateEmployee = listOfEmployees.get((int) index - 1);
//        updateEmployee.setFirstName("Evgeniy");
//        updateEmployee.setLastName("Korzun");
//        updateEmployee.setDepartmentId(5);
//        updateEmployee.setJobTitle("engineer");
//        updateEmployee.setGender(Gender.MALE);
//        updateEmployee.setDateOfBirth("24.06.1987");
//        return updateEmployee;
//    }

//    @DeleteMapping("/{employeeId}")
//    public List<Employee> deleteEmployeeById(@PathVariable Long employeeId) {
//        long index = employeeId;
//        listOfEmployees.remove((int) index - 1);
//        return listOfEmployees;
//    }

}
