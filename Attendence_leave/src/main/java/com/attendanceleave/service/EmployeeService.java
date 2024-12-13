package com.attendanceleave.service;

import com.attendanceleave.entity.Employee;
import com.attendanceleave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    // Save or update employee
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Delete employee by ID
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Update leave balance
    public Employee updateLeaveBalance(Long id, Integer leaveBalance) {
        Optional<Employee> employee = getEmployeeById(id);
        if (employee.isPresent()) {
            Employee emp = employee.get();
           // emp.setLeaveBalance(leaveBalance);
            return saveEmployee(emp);
        }
        return null;
    }
}
