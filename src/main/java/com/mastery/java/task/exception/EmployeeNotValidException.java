package com.mastery.java.task.exception;

public class EmployeeNotValidException extends RuntimeException {
    public EmployeeNotValidException(String name) {
        super("Employee with name " + name + " is under 18 years old!");
    }
}
