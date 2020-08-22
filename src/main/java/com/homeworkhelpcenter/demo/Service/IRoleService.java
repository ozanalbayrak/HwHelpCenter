package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;

import java.util.List;

public interface IRoleService {

    Role save(Role role);

    Role getDefaultRole();

}
