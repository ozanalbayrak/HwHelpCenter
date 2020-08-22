package com.homeworkhelpcenter.demo.Controller;

import com.homeworkhelpcenter.demo.Common.Constant.Url;
import com.homeworkhelpcenter.demo.Common.Http.ResponseDto;
import com.homeworkhelpcenter.demo.Dto.AssignmentCreationAndRegisterRequestDto;
import com.homeworkhelpcenter.demo.Dto.AssignmentCreationAndRegisterResponseDto;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import com.homeworkhelpcenter.demo.Service.IAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = Url.CORS, maxAge = 3600)
@RestController
@AllArgsConstructor
public class AssignmentController {

    private final IAssignmentService iAssignmentService;
    private final UserController userController;

    @PostMapping(path = "/createAssignment")
    public ResponseDto<AssignmentBase> create(@RequestBody AssignmentBase assignmentBase) throws Exception {
        return new ResponseDto<AssignmentBase>(iAssignmentService.createAssignment(assignmentBase), HttpStatus.ACCEPTED);
    }

    @PostMapping(path = "/createAssignmentAndRegister")
    public ResponseDto<AssignmentCreationAndRegisterResponseDto> createAssignmentAndRegister(@RequestBody AssignmentCreationAndRegisterRequestDto request) throws Exception {
        TokenResponseDto tokenResponseDto= userController.register(request.getUser()).getBody();
        if (tokenResponseDto == null) {
            throw new Exception("Register failed.");
        }
        request.getAssignment().setOwner(tokenResponseDto.getUser());
        AssignmentBase assignmentBase = iAssignmentService.createAssignment(request.getAssignment());
        return new ResponseDto<AssignmentCreationAndRegisterResponseDto>(new AssignmentCreationAndRegisterResponseDto(tokenResponseDto, assignmentBase), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getAssignment" + Url.PATH_VARIABLE_ID)
    public ResponseDto<AssignmentBase> getAssignment(@PathVariable String id)
    {
        return null;
    }

    @GetMapping(path = "/getAssignments/{email}")
    public ResponseDto<List<AssignmentBase>> getAssignmentsOfUser(@PathVariable String email)
    {
        return new ResponseDto<List<AssignmentBase>>(iAssignmentService.getAssignmentsOfUser(email), HttpStatus.ACCEPTED);
    }

}
