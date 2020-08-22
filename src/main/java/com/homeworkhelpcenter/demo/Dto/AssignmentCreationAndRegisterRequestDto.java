package com.homeworkhelpcenter.demo.Dto;

import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentCreationAndRegisterRequestDto {

    @NonNull
    private UserRegisterDto user;

    @NonNull
    private AssignmentBase assignment;

}
