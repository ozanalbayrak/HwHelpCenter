package com.homeworkhelpcenter.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {

    @NonNull
    private String email;

    private String password;

}
