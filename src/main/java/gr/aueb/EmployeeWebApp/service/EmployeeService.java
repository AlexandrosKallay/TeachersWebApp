package gr.aueb.EmployeeWebApp.service;

import gr.aueb.EmployeeWebApp.model.Employee;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeIdAlreadyExistsException;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeNameMustNotBeNullException;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;


// An interface that defines the methods that the EmployeeServiceImpl class will implement.
public interface EmployeeService {

    /**
     * This function returns a list of all employees.
     *
     * @return A list of all employees.
     */
    List<Employee> getAllEmployees();

    /**
     * Save the employee.
     *
     * @param employee The employee object to be saved.
     */
    void saveEmployee(Employee employee) throws EmployeeIdAlreadyExistsException, EmployeeNotFoundException, EmployeeNameMustNotBeNullException;

    /**
     * This function returns an Employee object with the given id.
     *
     * @param id The id of the employee you want to get.
     * @return Employee
     */
    Employee getEmployeeById(long id);

    /**
     * Deletes an employee by id.
     *
     * @param id The id of the employee to be deleted.
     */
    void deleteEmployeeById(long id);

    /**
     * It returns a Page of Employee objects for a given page number, page size, sort field, and sort direction
     *
     * @param pageNo        The page number to be retrieved.
     * @param pageSize      The number of records per page.
     * @param sortField     The field on which the data should be sorted
     * @param sortDirection "asc" or "desc"
     * @return A Page object.
     */
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);

    /**
     * Get all employees whose name contains the given keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of employees.
     */
    List<Employee> getByKeyword(String keyword);
}

