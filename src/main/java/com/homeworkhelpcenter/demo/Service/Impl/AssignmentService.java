package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Common.RandomStringGenerator;
import com.homeworkhelpcenter.demo.Controller.UserController;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Dto.UserRegisterDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import com.homeworkhelpcenter.demo.Entity.User;
import com.homeworkhelpcenter.demo.Repo.IAssignmentRepository;
import com.homeworkhelpcenter.demo.Service.IAssignmentService;
import com.homeworkhelpcenter.demo.Service.IMailService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
@Service
@AllArgsConstructor
public class AssignmentService implements IAssignmentService {

    private final IAssignmentRepository iAssignmentRepository;
    private final IUserService iUserService;
    private final UserController userController;
    private final JavaMailSender javaMailSender;

    @Override
    public TokenResponseDto create(AssignmentBase assignmentBase) throws Exception {
        if(iUserService.loadUserByEmail(assignmentBase.getOwner().getEmail()) == null) {
            RandomStringGenerator gen = new RandomStringGenerator(8, ThreadLocalRandom.current());
            RandomStringGenerator session = new RandomStringGenerator();
            String easy = RandomStringGenerator.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
            RandomStringGenerator tickets = new RandomStringGenerator(23, new SecureRandom(), easy);
            UserRegisterDto loginUser = new UserRegisterDto(assignmentBase.getOwner().getEmail(), tickets.nextString());
            assignmentBase.setOwner(iUserService.createUser(loginUser));
            iAssignmentRepository.save(assignmentBase);
            User user = new User();
            user.setEmail(loginUser.getEmail());
            user.setEncryptedPassword(loginUser.getPassword());
            String mailBody = loginUser.getEmail() + "\n Password: " + loginUser.getPassword();
            sendEmail(loginUser.getEmail(), mailBody, "TestLogin");
            return userController.login(user).getBody();
        }
        else {
            assignmentBase.setOrderId(orderIdGenerator());
            iAssignmentRepository.save(assignmentBase);
            return null;
        }
    }

    @Override
    public List<AssignmentBase> getAssignmentsOfUser(String email) {
        User user = iUserService.loadUserByEmail(email);
        return iAssignmentRepository.findAllByOwner(user);
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

    private void sendEmail(String to, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mt.ozanalbayrak@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

}
