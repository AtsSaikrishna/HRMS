package com.example.auth.repository;

import com.example.auth.model.LeaveRequest;
import com.example.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeOrderByCreatedAtDesc(User employee);
    List<LeaveRequest> findAllByOrderByCreatedAtDesc();
}