package com.project.business.usecases.activeemployee;

import com.project.business.entity.ActiveEmployee;
import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.ActiveEmployeeDto;
import com.project.business.repository.EmployeeRepository;
import com.project.business.usecases.employee.AddEmployeeUseCase;
import org.springframework.stereotype.Component;

@Component
public class AddActiveEmployeeUseCase extends AddEmployeeUseCase {

    private EmployeeRepository employeeRepository;
    private ActiveEmployeeDto activeEmployeeDto;

    public AddActiveEmployeeUseCase withRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public AddActiveEmployeeUseCase forAciveEmployeeDto(ActiveEmployeeDto activeEmployeeDto) {
        this.activeEmployeeDto = activeEmployeeDto;
        return this;
    }

    public Employee run(){
        return createEmployee();
    }

    private ActiveEmployee createEmployee(){
        ActiveEmployee activeEmployee = new ActiveEmployee();
        activeEmployee.setName(activeEmployeeDto.getName());
        activeEmployee.setSalary(activeEmployeeDto.getSalary());
        activeEmployee.setDateOfEmployment(activeEmployeeDto.getDateOfEmployment());
        return employeeRepository.save(activeEmployee);
    }
}
