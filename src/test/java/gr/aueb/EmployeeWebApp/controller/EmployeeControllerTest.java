package gr.aueb.EmployeeWebApp.controller;


import gr.aueb.EmployeeWebApp.model.Employee;
import gr.aueb.EmployeeWebApp.service.EmployeeService;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeIdAlreadyExistsException;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeNameMustNotBeNullException;
import gr.aueb.EmployeeWebApp.service.Exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class EmployeeControllerTest {


//    @Test
//    public void showNewEmployeeFormTest() {
//        EmployeeController controller = new EmployeeController();
//        ModelMap model = new ModelMap();
//        String result = controller.showNewEmployeeForm(model);
//        assertEquals("new_employee", result);
//        assertTrue(model.containsAttribute("employee"));
//    }


    @Test
    public void testSaveEmployee() throws EmployeeIdAlreadyExistsException, EmployeeNotFoundException, EmployeeNameMustNotBeNullException {
        // arrange
        Employee employee = new Employee();
        employee.setFirstName("John Doe");
        employee.setEmail("john.doe@example.com");

        EmployeeService employeeService = mock(EmployeeService.class);

        EmployeeController employeeController = new EmployeeController(employeeService);

        // act
        String result = employeeController.saveEmployee(employee);

        // assert
        assertEquals("redirect:/", result);
        verify(employeeService).saveEmployee(employee);
    }

}