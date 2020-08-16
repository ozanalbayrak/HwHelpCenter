package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Common.RandomStringGenerator;
import com.homeworkhelpcenter.demo.Dto.UserRegisterDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import com.homeworkhelpcenter.demo.Repo.IAssignmentRepository;
import com.homeworkhelpcenter.demo.Service.IAssignmentService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    private final IAssignmentRepository iAssignmentRepository;
    private final IUserService iUserService;

    @Override
    public AssignmentBase create(AssignmentBase assignmentBase) throws Exception {
        if(iUserService.loadUserByEmail(assignmentBase.getOwner().getEmail()) == null) {
            RandomStringGenerator gen = new RandomStringGenerator(8, ThreadLocalRandom.current());
            RandomStringGenerator session = new RandomStringGenerator();
            String easy = RandomStringGenerator.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
            RandomStringGenerator tickets = new RandomStringGenerator(23, new SecureRandom(), easy);
            UserRegisterDto loginUser = new UserRegisterDto(assignmentBase.getOwner().getEmail(), tickets.nextString());
            assignmentBase.setOwner(iUserService.createUserAndLogin(loginUser));
        }
        else {
            assignmentBase.setOrderId(orderIdGenerator());
            iAssignmentRepository.save(assignmentBase);
        }

        return assignmentBase;
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
