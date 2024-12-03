package com.example.auth.controller;

import com.example.auth.dto.LeaveRequestDto;
import com.example.auth.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employee/leave")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeLeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping("/request")
    public ResponseEntity<?> createLeaveRequest(
            @Valid @RequestBody LeaveRequestDto leaveRequestDto,
            Authentication authentication) {
        return ResponseEntity.ok(leaveService.createLeaveRequest( 
            authentication.getName(), 
            leaveRequestDto
        ));
    }

    @GetMapping("/my-requests")
    public ResponseEntity<?> getMyLeaveRequests(Authentication authentication) {
        return ResponseEntity.ok(leaveService.getEmployeeLeaveRequests(
            authentication.getName()
        ));
    }
}