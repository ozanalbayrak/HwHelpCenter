package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Common.Http.ResponseDto;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;

public interface IAssignmentService {
    TokenResponseDto create(AssignmentBase assignmentBase) throws Exception;
}
