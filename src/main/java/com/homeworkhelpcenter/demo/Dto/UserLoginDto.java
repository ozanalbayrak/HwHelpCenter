package com.homeworkhelpcenter.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

    @NonNull
    private String email;

    @NonNull
    private String password;

}
