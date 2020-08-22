package com.homeworkhelpcenter.demo.Dto;


import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private User user;
    private String token;
}