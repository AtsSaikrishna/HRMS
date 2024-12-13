package com.attendanceleave.controller;

import com.attendanceleave.entity.LeaveRequest;
import com.attendanceleave.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/leaverequest")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    // Get all leave requests
    @GetMapping("/getAllleave")
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();
    }

    // Get leave request by ID
    @GetMapping("/getLeaveRequestBy/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return leaveRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create or update leave request
    @PostMapping("/create/update/leaveRequest")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveRequestService.saveLeaveRequest(leaveRequest));
    }

    // Delete leave request
    @DeleteMapping("/deleteLeaveRequestby/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // Update leave status (HR approval)
    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveStatus(@PathVariable Long id, @RequestParam String status) {
        Optional<LeaveRequest> leaveRequest = leaveRequestService.getLeaveRequestById(id);
        if (leaveRequest.isPresent()) {
            LeaveRequest request = leaveRequest.get();
            request.setStatus(status);
            return ResponseEntity.ok(leaveRequestService.saveLeaveRequest(request));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
