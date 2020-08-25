package com.project.business.entity;

import org.hibernate.envers.RevisionListener;

public class UserRevisionListener implements RevisionListener {
    private final static String USERNAME = "Username";

    @Override
    public void newRevision(Object o) {
        UserRevision userRevision = (UserRevision) o;
        userRevision.setUsername(USERNAME);
    }
}
