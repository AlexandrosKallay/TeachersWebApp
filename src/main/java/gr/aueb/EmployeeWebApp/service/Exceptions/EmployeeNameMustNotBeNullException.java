package gr.aueb.EmployeeWebApp.service.Exceptions;

public class EmployeeNameMustNotBeNullException extends Throwable {
    private static final long serialVersionUID = 1L;

    public EmployeeNameMustNotBeNullException() {
        super("Employee First Name and Last Name must not be empty");
    }
}
