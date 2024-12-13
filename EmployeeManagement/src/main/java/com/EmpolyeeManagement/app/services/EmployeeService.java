package com.EmpolyeeManagement.app.services;

import com.EmpolyeeManagement.app.entity.Employee;
import com.EmpolyeeManagement.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save a new Employee
    public Employee saveEmployee(Employee employee) {
        try {
            return employeeRepository.save(employee);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Employee with this email already exists.");
        }
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get Employee by ID
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new IllegalArgumentException("Employee not found with ID: " + id);
        }
    }

    // Update Employee by ID
    public Employee updateEmployee(Long id, Employee employee) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPhoneNumber(employee.getPhoneNumber());
            existingEmployee.setGender(employee.getGender());
            existingEmployee.setDateOfBirth(employee.getDateOfBirth());
            existingEmployee.setAddress(employee.getAddress());
            existingEmployee.setJobTitle(employee.getJobTitle());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setRole(employee.getRole());
            existingEmployee.setDateOfJoining(employee.getDateOfJoining());
            existingEmployee.setStatus(employee.getStatus());
            existingEmployee.setPassword(employee.getPassword());
            return employeeRepository.save(existingEmployee);
        }).orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + id));
    }

    // Delete Employee by ID
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get Employee by Email
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with email: " + email));
    }

    // Get Employee by Employee ID
    public Employee getEmployeeByEmployeeId(Long employeeId) {
        return employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with Employee ID: " + employeeId));
    }
}
