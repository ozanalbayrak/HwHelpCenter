package com.homeworkhelpcenter.demo.Service;

import com.homeworkhelpcenter.demo.Entity.Role;

public interface IRoleService {

    Role save(Role role);

    Role getDefaultRole();

}
