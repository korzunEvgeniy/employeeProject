package com.mastery.java.task.exception;

import java.util.function.Supplier;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}