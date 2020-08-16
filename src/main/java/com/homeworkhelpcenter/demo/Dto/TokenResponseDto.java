package com.homeworkhelpcenter.demo.Dto;


import com.homeworkhelpcenter.demo.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String email;
    private String token;
    Set<Role> roles = new HashSet<>();
}