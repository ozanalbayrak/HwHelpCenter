package com.homeworkhelpcenter.demo.Dto;

import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentCreationAndRegisterResponseDto {
    private TokenResponseDto tokenResponseDto;
    private AssignmentBase assignmentBase;
}
