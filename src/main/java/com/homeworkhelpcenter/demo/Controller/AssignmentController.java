package com.homeworkhelpcenter.demo.Controller;

import com.homeworkhelpcenter.demo.Common.Constant.Url;
import com.homeworkhelpcenter.demo.Common.Http.ResponseDto;
import com.homeworkhelpcenter.demo.Dto.TokenResponseDto;
import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import com.homeworkhelpcenter.demo.Service.IAssignmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = Url.CORS, maxAge = 3600)
@RestController
@AllArgsConstructor
public class AssignmentController {

    private final IAssignmentService iAssignmentService;

    @PostMapping(path = "/createAssignment")
    public ResponseDto<TokenResponseDto> create(@RequestBody AssignmentBase assignmentBase) throws Exception {
        return new ResponseDto<TokenResponseDto>(iAssignmentService.create(assignmentBase), HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/getAssignment" + Url.PATH_VARIABLE_ID)
    public ResponseDto<AssignmentBase> getAssignment(@PathVariable String id)
    {
        return null;
    }



}
