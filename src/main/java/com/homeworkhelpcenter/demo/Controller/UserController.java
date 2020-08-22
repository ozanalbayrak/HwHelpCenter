package com.homeworkhelpcenter.demo.Controller;

import com.homeworkhelpcenter.demo.Common.Constant.Url;
import com.homeworkhelpcenter.demo.Common.Http.ResponseDto;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Dto.UserLoginDto;
import com.homeworkhelpcenter.demo.Dto.UserRegisterDto;
import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;
import com.homeworkhelpcenter.demo.Security.Jwt.JwtTool;
import com.homeworkhelpcenter.demo.Service.ILoginService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@CrossOrigin(value = Url.CORS, maxAge = 3600)
@RestController
@AllArgsConstructor
public class UserController {

    private final IUserService iUserService;
    private final ILoginService iLoginService;

    @PostMapping("/login")
    public ResponseDto<TokenResponseDto> login(@RequestBody @Validated UserLoginDto user) throws AuthenticationException, UsernameNotFoundException {
        return new ResponseDto<TokenResponseDto>(iLoginService.login(user), HttpStatus.ACCEPTED);
    }

    @PostMapping("/register")
    public ResponseDto<TokenResponseDto> register(@RequestBody @Validated UserRegisterDto userRegisterDto) throws Exception {
        User user = iUserService.createUser(userRegisterDto);
        if (user == null) {
            throw new Exception("User can not be saved.");
        }
        UserLoginDto userLoginDto = new UserLoginDto(userRegisterDto.getEmail(), userRegisterDto.getPassword());
        return new ResponseDto<TokenResponseDto>(iLoginService.login(userLoginDto), HttpStatus.ACCEPTED);
    }

}
