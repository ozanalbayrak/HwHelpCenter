package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Repo.IRoleRepository;
import com.homeworkhelpcenter.demo.Service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class RoleService implements  IRoleService {

    private final IRoleRepository iRoleRepository;

    @Override
    public Role save(Role role) {
        iRoleRepository.save(role);
        return role;
    }

    @Override
    public Role getDefaultRole() {
        return iRoleRepository.getDefaultRole();
    }
}
