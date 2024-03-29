package com.mastery.java.task.dao.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.mastery.java.task.service.validator.UnderValidAge;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @Schema(description = "Auto-generated identifier for employee")
    private Long id;

    @Column(name = "first_name")
    @Schema(description = "First name", example = "Ivan", required = true)
    @Size(min = 2, max = 20, message = "Name could be between 2 and 20 characters")
    private String firstName;

    @Column(name = "last_name")
    @Schema(description = "Second name", example = "Ivanov", required = true)
    @NotBlank(message = "Second name cannot be empty")
    @Size(min = 2, max = 20)
    private String lastName;

    @Column(name = "department_id")
    @Schema(description = "ID of department", example = "3", required = true)
    @NotNull(message = "Department cannot be null")
    private int departmentId;

    @Column(name = "job_title")
    @Schema(description = "Type of profession", example = "Engineer", required = true)
    @NotBlank(message = "Professional cannot be empty")
    @Size(min = 2, max = 20)
    private String jobTitle;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "gender")
    @Schema(description = "Type of gender", example = "Female", required = true)
    private Employee.Gender gender;

    @Column(name = "date_of_birth")
    @Schema(description = "Date of birth", example = "yyyy-mm-dd", required = true)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @UnderValidAge
    private LocalDate dateOfBirth;

    public enum Gender {
        MALE,
        FEMALE
    }

    public Employee(Long id, String firstName, String lastName,
                    int departmentId, String jobTitle,
                    Employee.Gender gender, LocalDate dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
        this.jobTitle = jobTitle;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public Employee.Gender getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setId(Long employeeId) {
        this.id = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setGender(Employee.Gender gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Employee employee))
            return false;
        return Objects.equals(this.id, employee.id) &&
                Objects.equals(this.firstName, employee.firstName) &&
                Objects.equals(this.lastName, employee.lastName) &&
                Objects.equals(this.departmentId, employee.departmentId) &&
                Objects.equals(this.jobTitle, employee.jobTitle) &&
                this.gender == employee.gender &&
                Objects.equals(this.dateOfBirth, employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.firstName, this.lastName,
                this.departmentId, this.jobTitle, this.gender, this.dateOfBirth);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentId=" + departmentId +
                ", jobTitle='" + jobTitle + '\'' +
                ", gender=" + gender +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}
