package com.mastery.java.task.service;

import com.mastery.java.task.dao.EmployeeDaoImpl;
import com.mastery.java.task.dto.Employee;
import com.mastery.java.task.dto.Gender;
import com.mastery.java.task.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeServiceImpl;

    List<Employee> listOfEmployees = new ArrayList<>();
    Long employeeId = 1L;
    Long employeeId2 = 2L;
    Long employeeId3 = 3L;
    Employee e1 = new Employee(employeeId, "Evgeniy", "Korzun",
            220, "developer", Gender.MALE, "1987-06-24");
    Employee e2 = new Employee(employeeId2, "Alex", "Safronov",
            221, "developer", Gender.MALE, "1977-12-12");
    Employee e3 = new Employee(employeeId3, "A", "B",
            222, "dev", Gender.FEMALE, "1-2-3");

    @Mock
    private EmployeeDaoImpl employeeDaoImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.openMocks(this);
        employeeServiceImpl = new EmployeeServiceImpl(employeeDaoImpl);
    }

    @Test
    public void getAllEmployees() {
        listOfEmployees.add(e1);
        listOfEmployees.add(e2);
        listOfEmployees.add(e3);

        when(employeeDaoImpl.getAllEmployees()).thenReturn(listOfEmployees);

        List<Employee> actualList = employeeServiceImpl.getAllEmployees();
        assertEquals(listOfEmployees, actualList);
        assertEquals(3, actualList.size());
        assertNotEquals(1, listOfEmployees.size());

        verify(employeeDaoImpl).getAllEmployees();
    }

    @Test
    public void getEmployeeById() throws EmployeeNotFoundException {
        when(employeeDaoImpl.getEmployeeById(employeeId)).thenReturn(e1);

        assertEquals(employeeServiceImpl.getEmployeeById(employeeId), e1);
        assertNotEquals(employeeServiceImpl.getEmployeeById(employeeId2), e1);

        verify(employeeDaoImpl).getEmployeeById(employeeId);
    }

    @Test
    public void getEmployeeByIdExpectedException() throws EmployeeNotFoundException {
        Long employeeId4 = 4L;
        when(employeeDaoImpl.getEmployeeById(employeeId4)).thenThrow(EmployeeNotFoundException.class);

        Throwable exception;
        exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.getEmployeeById(employeeId4));
        assertEquals("Employee with id " + employeeId4 + " is not exist", exception.getMessage());
    }

    @Test
    public void createNewEmployee() {
        when(employeeDaoImpl.createNewEmployee(e2)).thenReturn(e2);

        assertEquals(employeeServiceImpl.createNewEmployee(e2), e2);
        verify(employeeDaoImpl).createNewEmployee(e2);
    }

    @Test
    void updateEmployee() throws EmployeeNotFoundException {
        when(employeeDaoImpl.updateEmployee(employeeId2, e3)).thenReturn(e3);

        assertEquals(employeeServiceImpl.updateEmployee(employeeId2, e3), e3);
        verify(employeeDaoImpl).updateEmployee(employeeId2, e3);

    }

    @Test
    public void updateEmployeeExpectedException() throws EmployeeNotFoundException{
        Long employeeId5 = 5L;

        when(employeeDaoImpl.updateEmployee(employeeId5, e3)).thenThrow(EmployeeNotFoundException.class);

        Throwable exception;
        exception = assertThrows(EmployeeNotFoundException.class,
                () -> employeeServiceImpl.updateEmployee(employeeId5, e3));

        assertEquals("Employee with id " + employeeId5 + " is not exist", exception.getMessage());
    }

    @Test
    public void deleteEmployeeById() throws EmployeeNotFoundException {
        doNothing().
                when(employeeDaoImpl).deleteEmployeeById(employeeId3);
        employeeServiceImpl.deleteEmployeeById(employeeId3);
        verify(employeeDaoImpl).deleteEmployeeById(employeeId3);
    }

    @Test
    public void deleteEmployeeExpectedException() throws EmployeeNotFoundException {
        long employeeId6 = 6L;

        doNothing().doThrow(EmployeeNotFoundException.class).when(employeeDaoImpl).deleteEmployeeById(employeeId6);
        employeeServiceImpl.deleteEmployeeById(employeeId6);
        verify(employeeDaoImpl).deleteEmployeeById(employeeId6);
   }
}