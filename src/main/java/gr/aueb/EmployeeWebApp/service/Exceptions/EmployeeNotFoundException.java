package gr.aueb.EmployeeWebApp.service.Exceptions;

import gr.aueb.EmployeeWebApp.model.Employee;

public class EmployeeNotFoundException extends Throwable {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(Employee teacher) {
        super("Employee with id = " + teacher.getId() + " does no exist");
    }
}
