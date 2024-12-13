package com.attendanceleave.service;

import com.attendanceleave.entity.Attendance;
import com.attendanceleave.entity.Employee;
import com.attendanceleave.repository.AttendanceRepository;
import com.attendanceleave.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    // Get all attendance records
    public List<Attendance> getAllAttendance() {
        try {
            return attendanceRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while fetching attendance records.");
        }
    }

    // Get attendance record by ID
    public Optional<Attendance> getAttendanceById(Long id) {
        try {
            return attendanceRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while fetching the attendance record.");
        }
    }

    // Save or update attendance record
    public Attendance saveAttendance(Attendance attendance) {
        try {
            if (attendance.getEmployee() == null || attendance.getAttendanceDate() == null || attendance.getStatus() == null) {
                throw new IllegalArgumentException("All fields (employee, attendanceDate, status) are required.");
            }

            // Ensure employee exists before saving attendance
            Optional<Employee> employee = employeeRepository.findById(attendance.getEmployee().getId());
            if (employee.isEmpty()) {
                throw new IllegalArgumentException("Employee not found for the given ID.");
            }

            // Set the employee for the attendance record
            attendance.setEmployee(employee.get());
            
            // Save attendance record
            return attendanceRepository.save(attendance);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;  // Rethrow to handle it in controller
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while saving the attendance record.");
        }
    }

    // Delete attendance record
    public void deleteAttendance(Long id) {
        try {
            Optional<Attendance> attendance = attendanceRepository.findById(id);
            if (attendance.isPresent()) {
                attendanceRepository.deleteById(id);
            } else {
                throw new IllegalArgumentException("Attendance record not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred while deleting the attendance record.");
        }
    }
}
