package com.EmpolyeeManagement.app.repository;

import com.EmpolyeeManagement.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<Employee> findByEmployeeId(Long employeeId);  // Assuming Employee has an employeeId field
}
