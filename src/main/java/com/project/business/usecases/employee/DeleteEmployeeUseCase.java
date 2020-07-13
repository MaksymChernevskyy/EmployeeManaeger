package com.project.business.usecases.employee;

import com.project.business.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteEmployeeUseCase {
    private EmployeeRepository employeeRepository;
    private Long employeeId;

    public DeleteEmployeeUseCase withRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public DeleteEmployeeUseCase forEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public void run() {
        employeeRepository.deleteById(employeeId);
    }
}
