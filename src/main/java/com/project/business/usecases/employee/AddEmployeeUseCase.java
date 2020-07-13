package com.project.business.usecases.employee;

import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.EmployeeDto;
import com.project.business.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

@Component
public class AddEmployeeUseCase {
    private EmployeeRepository employeeRepository;
    private EmployeeDto employeeDto;

    public AddEmployeeUseCase withRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public AddEmployeeUseCase withEmployeeDto(EmployeeDto employeeDto) {
        this.employeeDto = employeeDto;
        return this;
    }

    public Employee run(){
        return createEmployee();
    }

    private Employee createEmployee(){
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        return employeeRepository.save(employee);
    }
}
