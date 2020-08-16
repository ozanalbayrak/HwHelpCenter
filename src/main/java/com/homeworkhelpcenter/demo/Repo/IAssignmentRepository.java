package com.homeworkhelpcenter.demo.Repo;

import com.homeworkhelpcenter.demo.Entity.Assignment.AssignmentBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface IAssignmentRepository extends CrudRepository<AssignmentBase, String> {

    @Query("select count (a) from AssignmentBase a where a.createdAt =: todaysDate")
    int getTodaysAssignmentCount(Date todaysDate);
}
