package com.project.business.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Entity;

@Entity
@RevisionEntity(UserRevisionListener.class)
public class UserRevision extends DefaultRevisionEntity {

    @Getter
    @Setter
    private String username;
}
