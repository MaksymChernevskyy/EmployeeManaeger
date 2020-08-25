package com.project.business.repository;

import com.project.business.entity.Employee;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnversReposiory extends RevisionRepository<Employee, Long, Integer> {
}
