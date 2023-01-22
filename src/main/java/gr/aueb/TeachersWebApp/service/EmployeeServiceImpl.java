package gr.aueb.TeachersWebApp.service;

import gr.aueb.TeachersWebApp.dao.EmployeeDAO;
import gr.aueb.TeachersWebApp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * We are creating a Pageable object with the page number, page size, and sort direction and then passing it to the
 * findAll() method of the EmployeeRepository interface
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeRepository;

    // A constructor.
    public EmployeeServiceImpl(EmployeeDAO employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * > The function returns a list of all employees in the database
     *
     * @return A list of all employees
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    /**
     * If the employee is present, return the employee, else throw an exception
     *
     * @param id The id of the employee to be retrieved.
     * @return Employee
     */
    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> optional = employeeRepository.findById(id);
        Employee employee;
        if (optional.isPresent()) {
            employee = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return employee;
    }

    /**
     * The deleteEmployeeById function takes in an id, and deletes the employee with that id
     *
     * @param id The id of the employee to delete.
     */
    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    /**
     * We are creating a Pageable object with the page number, page size, and sort direction and then passing it to the
     * findAll() method of the EmployeeRepository interface
     *
     * @param pageNo The page number to be returned.
     * @param pageSize The number of records to be fetched in a page.
     * @param sortField The field on which the sorting is to be performed.
     * @param sortDirection This is the direction of the sort. It can be either ascending or descending.
     * @return A Page object.
     */
    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }

    /**
     * Find all employees whose first name, last name, or email contains the keyword.
     *
     * @param keyword The keyword to search for.
     * @return A list of employees
     */
    public List<Employee> getByKeyword(String keyword){
        return employeeRepository.findByKeyword(keyword);
    }
}
