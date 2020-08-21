package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Dto.UserRegisterDto;
import com.homeworkhelpcenter.demo.Entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    UserDetails loadUserByUsername(String email);

    User loadUserByEmail(String email);

    User createUser(UserRegisterDto userRegisterDto);
}
