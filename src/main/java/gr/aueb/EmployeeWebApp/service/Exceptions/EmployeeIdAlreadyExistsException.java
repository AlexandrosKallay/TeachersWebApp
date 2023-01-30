package gr.aueb.EmployeeWebApp.service.Exceptions;

import gr.aueb.EmployeeWebApp.model.Employee;

/**
 * This class is a custom exception class that extends the Exception class and is used to throw an exception when an
 * employee with the same id already exists
 */
public class EmployeeIdAlreadyExistsException extends Exception {

    /**
     *
     */
    // Creating a custom exception class.
    private static final long serialVersionUID = 1L;

    // A constructor that takes an employee as a parameter and calls the super class constructor with a message.
    public EmployeeIdAlreadyExistsException(Employee employee) {
        super("Employee with id = " + employee.getId() + " already exist");
    }

}

