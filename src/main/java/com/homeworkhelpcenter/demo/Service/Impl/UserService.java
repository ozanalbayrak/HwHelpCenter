package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Dto.UserRegisterDto;
import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;
import com.homeworkhelpcenter.demo.Repo.IRoleRepository;
import com.homeworkhelpcenter.demo.Repo.UserRepository;
import com.homeworkhelpcenter.demo.Security.Jwt.JwtTool;
import com.homeworkhelpcenter.demo.Service.IRoleService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService, IUserService {

    private final UserRepository userRepository;

    private final IRoleService iRoleService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }

    @Override
    public User loadUserByEmail(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse( null);
    }


    @Override
    public User createUserAndLogin(UserRegisterDto userRegisterDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setEmail(userRegisterDto.getEmail());
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(userRegisterDto.getPassword()));
        Role role = iRoleService.getDefaultRole();
        if (role == null) {
            role = new Role("default_role");
            role = iRoleService.save(role);
        }
        Set<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }
}
