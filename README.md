# EmployeessWebApp

## EmployeeController
EmployeeController is a Java class for handling employee related actions and interactions with the front-end. It communicates with the EmployeeService to handle all employee operations like adding a new employee, updating an employee, deleting an employee, searching for an employee, and displaying a list of employees.

## Functions
**viewHomePage()** - returns a list of employees on the home page.

**showNewEmployeeForm()** - returns the form for creating a new employee.
![](animation/create.gif)

**saveEmployee()** - saves a new employee to the database.
![](animation/create.gif)

**showFormForUpdate()** - returns the form for updating an employee.
![](animation/update.gif)

**deleteEmployee()** - deletes an employee from the database.
![](animation/delete.gif)

**search()** - searches for employees based on a keyword.
![](animation/read.gif)

**findPaginated()** - returns a list of employees for a given page number, page size, sort field, and sort direction.

## Usage
This class can be used in a web application for managing employee data. The front-end can make HTTP requests to the endpoints defined in the EmployeeController class to perform operations on the employee data.


