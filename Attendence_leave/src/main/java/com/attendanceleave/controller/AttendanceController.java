package com.attendanceleave.controller;

import com.attendanceleave.entity.Attendance;
import com.attendanceleave.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    // Get all attendance records
    @GetMapping("/getAttendence")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        try {
            List<Attendance> attendanceList = attendanceService.getAllAttendance();
            return ResponseEntity.ok(attendanceList);
        } catch (Exception e) {
            e.printStackTrace(); // Log exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get attendance by ID
    @GetMapping("/getAttendenceBy/{id}")
    public ResponseEntity<?> getAttendanceById(@PathVariable Long id) {
        try {
            Optional<Attendance> attendance = attendanceService.getAttendanceById(id);
            if (attendance.isPresent()) {
                return ResponseEntity.ok(attendance.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance record not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Create or update attendance record
    @PostMapping("/create/update/Attendence")
    public ResponseEntity<?> createOrUpdateAttendance(@RequestBody Attendance attendance) {
        try {
            // Log incoming request data for debugging
            System.out.println("Received Attendance Data: " + attendance);

            // Validate input
            if (attendance.getEmployee() == null || attendance.getAttendanceDate() == null || attendance.getStatus() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("All fields (employee, attendanceDate, status) are required.");
            }

            // Save attendance record
            Attendance savedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAttendance);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Log exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Delete attendance record
    @DeleteMapping("/deleteAttendenceby/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        try {
            // Log the ID being deleted for debugging
            System.out.println("Attempting to delete attendance with ID: " + id);

            attendanceService.deleteAttendance(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Attendance record not found.");
        } catch (Exception e) {
            e.printStackTrace(); // Log exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    // Utility for testing (if needed)
    public AttendanceService getAttendanceService() {
        return attendanceService;
    }
}
