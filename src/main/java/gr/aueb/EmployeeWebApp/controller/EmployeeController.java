package gr.aueb.EmployeeWebApp.controller;

import gr.aueb.EmployeeWebApp.model.Employee;
import gr.aueb.EmployeeWebApp.service.EmployeeService;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeIdAlreadyExistsException;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeNameMustNotBeNullException;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    // A constructor that takes in an EmployeeService object and sets it to the employeeService variable.
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // display list of employees
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    /**
     * The function showNewEmployeeForm() is a GET request handler that returns the view name new_employee
     *
     * @param model This is the model object that will be used to store the data that will be displayed on the view page.
     * @return The new_employee.html file
     */
    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(ModelMap model) {
        // create model attribute to bind form data
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    /**
     * The function takes in an employee object, saves it to the database, and then redirects the user to the root page
     *
     * @param employee The name of the model attribute.
     * @return A redirect to the root path.
     */
    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        // save employee to database
        try {
            employeeService.saveEmployee(employee);
        } catch (EmployeeIdAlreadyExistsException | EmployeeNotFoundException | EmployeeNameMustNotBeNullException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    /**
     * We're getting the employee from the service, and then setting the employee as a model attribute to pre-populate the
     * form
     *
     * @param id    The id of the employee to be updated.
     * @param model This is the model object that will be used to render the view.
     * @return A String
     */
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    /**
     * The function takes in an id, calls the deleteEmployeeById function in the employeeService, and then redirects to the
     * root page
     *
     * @param id The id of the employee to be deleted.
     * @return A string
     */
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }


    /**
     * The function takes in a keyword, and if the keyword is empty, it redirects to the home page. If the keyword is not
     * empty, it searches for the keyword and returns the results
     *
     * @param model   The model is an object that contains all the data that will be displayed on the view page.
     * @param keyword the name of the parameter in the URL.
     * @return A list of employees
     */
    @GetMapping("/search")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        List<Employee> listEmployees;
        if (Objects.requireNonNull(keyword).isEmpty()) {
            return "redirect:/";
        } else {
            listEmployees = employeeService.getByKeyword(keyword);
        }
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }


    /**
     * It returns a list of employees for a given page number, page size, sort field and sort direction
     *
     * @param pageNo    The page number to be displayed.
     * @param sortField The field name to sort by.
     * @param sortDir   The direction of the sorting. It can be either asc or desc.
     * @param model     The model object that will be used to render the view.
     * @return A list of employees.
     */
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}
