package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Dto.UserLoginDto;
import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;
import com.homeworkhelpcenter.demo.Security.Jwt.JwtTool;
import com.homeworkhelpcenter.demo.Service.ILoginService;
import com.homeworkhelpcenter.demo.Service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class LoginService implements ILoginService {

    private final IUserService iUserService;
    private final AuthenticationManager authenticationManager;
    private final JwtTool jwtTokenUtil;

    @Override
    public TokenResponseDto login(UserLoginDto user) {
        final User userResp = iUserService.loadUserByEmail(user.getEmail());
        if (userResp == null) {
            throw new UsernameNotFoundException("User not found.");
        }
        Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        final String token = jwtTokenUtil.generateToken(userResp);
        final TokenResponseDto tokenResponseDto = new TokenResponseDto(userResp, token);
        return tokenResponseDto;
    }
}
