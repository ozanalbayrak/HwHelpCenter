package com.homeworkhelpcenter.demo.Service.Impl;

import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Repo.IRoleRepository;
import com.homeworkhelpcenter.demo.Service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleService implements  IRoleService {

    private final IRoleRepository iRoleRepository;

    public RoleService(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

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
