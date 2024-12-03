package com.example.auth.service;

import com.example.auth.dto.LeaveActionDto;
import com.example.auth.dto.LeaveRequestDto;
import com.example.auth.exception.ApiException;
import com.example.auth.model.LeaveRequest;
import com.example.auth.model.LeaveStatus;
import com.example.auth.model.User;
import com.example.auth.repository.LeaveRequestRepository;
import com.example.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public LeaveRequest createLeaveRequest(String username, LeaveRequestDto leaveRequestDto) {
        User employee = userRepository.findByUsername(username)
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setReason(leaveRequestDto.getReason());
        leaveRequest.setStartDate(leaveRequestDto.getStartDate());
        leaveRequest.setEndDate(leaveRequestDto.getEndDate());
        
        return leaveRequestRepository.save(leaveRequest);
    }

    @Transactional
    public LeaveRequest processLeaveRequest(Long leaveId, LeaveActionDto actionDto) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId)
            .orElseThrow(() -> new ApiException("Leave request not found", HttpStatus.NOT_FOUND));

        leaveRequest.setStatus(actionDto.getApproved() ? LeaveStatus.APPROVED : LeaveStatus.REJECTED);
        leaveRequest.setHrComment(actionDto.getComment());
        leaveRequest.setUpdatedAt(LocalDateTime.now());

        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getEmployeeLeaveRequests(String username) {
        User employee = userRepository.findByUsername(username)
            .orElseThrow(() -> new ApiException("User not found", HttpStatus.NOT_FOUND));
        return leaveRequestRepository.findByEmployeeOrderByCreatedAtDesc(employee);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAllByOrderByCreatedAtDesc();
    }
}