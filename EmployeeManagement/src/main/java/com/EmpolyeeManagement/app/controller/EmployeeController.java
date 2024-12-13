package com.EmpolyeeManagement.app.controller;

import com.EmpolyeeManagement.app.entity.Employee;
import com.EmpolyeeManagement.app.response.ResponseMessage;
import com.EmpolyeeManagement.app.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Register a new employee
    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> addEmployee(@RequestBody Employee employee) {
        try {
            Employee createdEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(
                    new ResponseMessage("Employee registered successfully.", HttpStatus.CREATED.value(), createdEmployee),
                    HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseMessage(e.getMessage(), HttpStatus.BAD_REQUEST.value(), null),
                    HttpStatus.BAD_REQUEST);
        }
    }

    // Get Employee by ID
    @GetMapping("/getEmployeeBy/{id}")
    public ResponseEntity<ResponseMessage> getEmployeeById(@PathVariable Long id) {
        try {
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(
                    new ResponseMessage("Employee retrieved successfully.", HttpStatus.OK.value(), employee),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), null),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseMessage("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update Employee by ID
    @PutMapping("/updateEmployeeBy/{id}")
    public ResponseEntity<ResponseMessage> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee updatedEmployee = employeeService.updateEmployee(id, employee);
            return new ResponseEntity<>(
                    new ResponseMessage("Employee updated successfully.", HttpStatus.OK.value(), updatedEmployee),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new ResponseMessage(e.getMessage(), HttpStatus.NOT_FOUND.value(), null),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseMessage("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Delete Employee by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);
        if (isDeleted) {
            return new ResponseEntity<>(
                    new ResponseMessage("Employee deleted successfully.", HttpStatus.NO_CONTENT.value(), null),
                    HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(
                    new ResponseMessage("Employee not found.", HttpStatus.NOT_FOUND.value(), null),
                    HttpStatus.NOT_FOUND);
        }
    }
}
