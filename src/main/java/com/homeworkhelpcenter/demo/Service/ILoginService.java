package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Dto.UserLoginDto;
import com.homeworkhelpcenter.demo.Entity.User;

public interface ILoginService {

    TokenResponseDto login(UserLoginDto user);
}
