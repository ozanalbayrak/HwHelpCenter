package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Dto.AssignmentCreationAndRegisterRequestDto;
import com.homeworkhelpcenter.demo.Dto.AssignmentCreationAndRegisterResponseDto;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;

import java.util.List;

public interface IAssignmentService {

    List<AssignmentBase> getAssignmentsOfUser(String email);

    AssignmentBase createAssignment(AssignmentBase assignment);
}
