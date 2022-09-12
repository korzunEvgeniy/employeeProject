package com.mastery.java.task.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.java.task.dao.entity.Employee;
import com.mastery.java.task.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Autowired
    EmployeeController employeeController;

    private Employee employeeValid;
    private Employee employeeNotValidAge;
    private Employee employeeWithNotValidName;

    @BeforeEach
    public void init() {
        employeeValid = new Employee(1L, "Evgeniy", "Krid",
                1, "developer", Employee.Gender.MALE, LocalDate.of(1989, 5, 14));
        employeeNotValidAge = new Employee(2L, "Alex", "Safronov",
                1, "developer", Employee.Gender.MALE, LocalDate.of(2011, 12, 12));
        employeeWithNotValidName = new Employee(3L, "A", "S",
                1, "engineer", Employee.Gender.FEMALE, LocalDate.of(2002, 5, 3));
    }

    @Test
    public void createValidEmployee() throws Exception {
        Mockito.when(employeeService.create(Mockito.any())).thenReturn(employeeValid);
        mockMvc.perform(post("/employees")
                        .content(objectMapper.writeValueAsString(employeeValid))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(employeeValid)));
    }

    @Test
    public void updateValidEmployee() throws Exception {
        Mockito.when(employeeService.update(Mockito.any())).thenReturn(employeeValid);
        mockMvc.perform(put("/employees")
                        .content(objectMapper.writeValueAsString(employeeValid))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employeeValid)));
    }

    @Test
    public void createNotValidAgeEmployee() throws Exception {
        Mockito.when(employeeService.create(Mockito.any())).thenReturn(employeeNotValidAge);
        mockMvc.perform(post("/employees")
                        .content(objectMapper.writeValueAsString(employeeNotValidAge))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void createEmployeeWithNotValidName() throws Exception {
        Mockito.when(employeeService.create(Mockito.any())).thenReturn(employeeWithNotValidName);
        mockMvc.perform(post("/employees")
                        .content(objectMapper.writeValueAsString(employeeWithNotValidName))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
