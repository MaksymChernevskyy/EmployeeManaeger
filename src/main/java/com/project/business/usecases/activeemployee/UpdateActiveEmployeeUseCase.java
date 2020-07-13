package com.project.business.usecases.activeemployee;

import com.project.business.exceptions.BadRequestException;
import com.project.business.entity.ActiveEmployee;
import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.ActiveEmployeeDto;
import com.project.business.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateActiveEmployeeUseCase {
    private EmployeeRepository employeeRepository;
    private ActiveEmployeeDto activeEmployeeDto;
    private Long employeeId;

    public UpdateActiveEmployeeUseCase withEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public UpdateActiveEmployeeUseCase withEmployeeDto(ActiveEmployeeDto activeEmployeeDto) {
        this.activeEmployeeDto = activeEmployeeDto;
        return this;
    }

    public UpdateActiveEmployeeUseCase forEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
        return this;
    }

    public Employee run() {
        return updateEmployee();
    }

    private ActiveEmployee updateEmployee() {
        ActiveEmployee updatedEmployee = (ActiveEmployee) employeeRepository.findById(employeeId).orElseThrow(() -> new BadRequestException("No such employee."));
        updatedEmployee.setName(activeEmployeeDto.getName());
        updatedEmployee.setSalary(activeEmployeeDto.getSalary());
        updatedEmployee.setDateOfEmployment(activeEmployeeDto.getDateOfEmployment());
        employeeRepository.save(updatedEmployee);
        return updatedEmployee;
    }
}
