package com.example.auth.controller;

import com.example.auth.dto.LeaveActionDto;
import com.example.auth.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/hr/leave")
@PreAuthorize("hasRole('HR')")
public class HRLeaveController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/requests")
    public ResponseEntity<?> getAllLeaveRequests() {
        return ResponseEntity.ok(leaveService.getAllLeaveRequests());
    }

    @PostMapping("/process/{leaveId}")
    public ResponseEntity<?> processLeaveRequest(
            @PathVariable Long leaveId,
            @Valid @RequestBody LeaveActionDto actionDto) {
        return ResponseEntity.ok(leaveService.processLeaveRequest(leaveId, actionDto));
    }
}