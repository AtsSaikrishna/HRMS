package com.attendanceleave.service;

import com.attendanceleave.entity.LeaveRequest;
import com.attendanceleave.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;

    @Autowired
    public LeaveRequestService(LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
    }

    // Get all leave requests
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    // Get leave request by ID
    public Optional<LeaveRequest> getLeaveRequestById(Long id) {
        return leaveRequestRepository.findById(id);
    }

    // Save or update leave request
    public LeaveRequest saveLeaveRequest(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    // Delete leave request by ID
    public void deleteLeaveRequest(Long id) {
        leaveRequestRepository.deleteById(id);
    }

    // Update leave status (HR approval)
    public LeaveRequest updateLeaveStatus(Long id, String status) {
        Optional<LeaveRequest> leaveRequest = getLeaveRequestById(id);
        if (leaveRequest.isPresent()) {
            LeaveRequest request = leaveRequest.get();
            request.setStatus(status);
            return saveLeaveRequest(request);
        }
        return null;
    }
}
