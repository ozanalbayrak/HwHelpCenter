package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Common.Http.ResponseDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;

public interface IAssignmentService {
    AssignmentBase create(AssignmentBase assignmentBase) throws Exception;
}
