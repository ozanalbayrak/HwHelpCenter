package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import com.homeworkhelpcenter.demo.Entity.User;
import com.homeworkhelpcenter.demo.Repo.IAssignmentRepository;
import com.homeworkhelpcenter.demo.Service.IAssignmentService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    private final IAssignmentRepository iAssignmentRepository;
    private final IUserService iUserService;

    @Override
    public List<AssignmentBase> getAssignmentsOfUser(String email) {
        User user = iUserService.loadUserByEmail(email);
        return iAssignmentRepository.findAllByOwner(user);
    }

    @Override
    public AssignmentBase createAssignment(AssignmentBase assignment) {
        User owner = iUserService.loadUserByEmail(assignment.getOwner().getEmail());
        assignment.setOrderId(orderIdGenerator());
        assignment.setOwner(owner);
        iAssignmentRepository.save(assignment);
        return assignment;
    }

    private String orderIdGenerator()
    {
        return "123";
    }

    private int getTodaysAssignmentCount()
    {
        Date todaysDate = DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
        return iAssignmentRepository.getTodaysAssignmentCount(todaysDate);
    }
}
