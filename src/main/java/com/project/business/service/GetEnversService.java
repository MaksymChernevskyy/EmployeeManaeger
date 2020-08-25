package com.project.business.service;

import com.project.business.entity.Employee;
import com.project.business.repository.EnversReposiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Component;

@Component
public class GetEnversService {
    private EnversReposiory enversReposiory;

    @Autowired
    public GetEnversService(EnversReposiory employeeRepository) {
        this.enversReposiory = employeeRepository;
    }

    public Revisions<Integer, Employee> getRevisionsById(Long id) {
        return enversReposiory.findRevisions(id);
    }
}
