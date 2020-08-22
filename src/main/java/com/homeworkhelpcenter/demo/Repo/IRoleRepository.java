package com.homeworkhelpcenter.demo.Repo;

import com.homeworkhelpcenter.demo.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

public interface IRoleRepository extends JpaRepository<Role, String> {
    @Query("select r from Role r where r.roleName = 'default_role'")
    Role getDefaultRole();

    Role findByRoleName(String roleName);
}
