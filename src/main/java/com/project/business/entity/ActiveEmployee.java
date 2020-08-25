package com.project.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "activeEmployee")
@Audited
public class ActiveEmployee extends Employee {

    @Column(name = "salary")
    private Double salary;

    @Column(name = "dateOfEmployment")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEmployment;
}
