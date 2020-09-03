package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Common.RandomStringGenerator;
import com.homeworkhelpcenter.demo.Dto.UserRegisterDto;
import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;
import com.homeworkhelpcenter.demo.Repo.IUserRepository;
import com.homeworkhelpcenter.demo.Service.IRoleService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Transactional
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService, IUserService {

    private final IUserRepository iUserRepository;

    private final IRoleService iRoleService;

    private final JavaMailSender javaMailSender;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = iUserRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = iUserRepository.findByEmail(email);
        return user.orElse( null);
    }


    @Override
    public User createUser(UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getPassword() == null || userRegisterDto.getPassword().equals("")) {
            RandomStringGenerator gen = new RandomStringGenerator(8, ThreadLocalRandom.current());
            RandomStringGenerator session = new RandomStringGenerator();
            String easy = RandomStringGenerator.digits + "ACEFGHJKLMNPQRUVWXYabcdefhijkprstuvwx";
            RandomStringGenerator tickets = new RandomStringGenerator(23, new SecureRandom(), easy);
            userRegisterDto.setPassword(tickets.nextString());
            String mailBody = "Email: " + userRegisterDto.getEmail() + "\n Password: " + userRegisterDto.getPassword();
            sendEmail(userRegisterDto.getEmail(), mailBody, "TestLogin");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userRegisterDto.getPassword()));
        iUserRepository.save(user);
        Role role = iRoleService.getDefaultRole();
        user.getRoles().add(role);
//        role.getUsers().add(user);
        return user;
    }

    @Override
    public Set<Role> getUserRoles(String email) {
        return iUserRepository.findRolesByEmail(email);
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
