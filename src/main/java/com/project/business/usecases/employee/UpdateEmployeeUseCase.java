package com.project.business.usecases.employee;

import com.project.business.exceptions.BadRequestException;
import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.EmployeeDto;
import com.project.business.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateEmployeeUseCase {
    private EmployeeRepository employeeRepository;
    private EmployeeDto employeeDto;
    private Long employeeId;

    public UpdateEmployeeUseCase withEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public UpdateEmployeeUseCase withEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
        return this;
    }

    public UpdateEmployeeUseCase forEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Employee run() {
        return updateEmployee();
    }

    private Employee updateEmployee() {
        Employee updatedEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new BadRequestException("No such employee."));
        updatedEmployee.setName(employeeDto.getName());
        employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }
}
