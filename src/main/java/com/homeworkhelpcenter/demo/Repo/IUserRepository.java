package com.homeworkhelpcenter.demo.Repo;

import com.homeworkhelpcenter.demo.Entity.Role;
import com.homeworkhelpcenter.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface IUserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);

    Set<Role> findRolesByEmail(String email);
}
