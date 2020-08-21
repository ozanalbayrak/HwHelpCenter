package com.homeworkhelpcenter.demo.Repo;

import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import com.homeworkhelpcenter.demo.Entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface IAssignmentRepository extends CrudRepository<AssignmentBase, String> {

    @Query("select count (a) from AssignmentBase a where a.createdAt =: todaysDate")
    int getTodaysAssignmentCount(Date todaysDate);

    @Query("select a from AssignmentBase a where a.owner = :user")
    List<AssignmentBase> findAllByOwner(User user);
}
