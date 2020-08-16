package com.homeworkhelpcenter.demo.Repo;

import com.homeworkhelpcenter.demo.Entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRoleRepository extends CrudRepository<Role, String> {
    @Query("select r from Role r where r.roleName = 'default_role'")
    Role getDefaultRole();
}
