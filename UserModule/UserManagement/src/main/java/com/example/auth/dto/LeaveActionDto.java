package com.example.auth.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LeaveActionDto {
    @NotNull
    private Boolean approved;
    private String comment;
}