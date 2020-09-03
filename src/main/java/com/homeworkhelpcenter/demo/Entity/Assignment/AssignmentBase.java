package com.homeworkhelpcenter.demo.Entity.Assignment;

import com.homeworkhelpcenter.demo.Common.Enum.AssignmentStatus;
import com.homeworkhelpcenter.demo.Common.Enum.PaymentStatus;
import com.homeworkhelpcenter.demo.Entity.BaseEntity;
import com.homeworkhelpcenter.demo.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
public class AssignmentBase extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    private String orderId;
    private String subject;
    private String deadlineDate;
    private String deadlineTime;
    private String details;
    private BigDecimal offer;
    private AssignmentStatus assignmentStatus = AssignmentStatus.AWAITING;
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

}
