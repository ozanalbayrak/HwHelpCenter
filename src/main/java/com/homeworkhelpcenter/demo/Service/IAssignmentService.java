package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Common.Http.ResponseDto;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;

import java.util.List;

public interface IAssignmentService {
    TokenResponseDto create(AssignmentBase assignmentBase) throws Exception;

    List<AssignmentBase> getAssignmentsOfUser(String email);
}
