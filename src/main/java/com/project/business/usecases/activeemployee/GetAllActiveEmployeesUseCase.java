package com.project.business.usecases.activeemployee;

import com.project.business.entity.ActiveEmployee;
import com.project.business.entity.entityDto.ActiveEmployeeDto;
import com.project.business.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllActiveEmployeesUseCase {
    private EmployeeRepository employeeRepository;

    public GetAllActiveEmployeesUseCase withRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public List<ActiveEmployeeDto> run() {
        return getAllActiveEmployees();
    }

    private List<ActiveEmployeeDto> getAllActiveEmployees() {
        List<ActiveEmployeeDto> list = new ArrayList<>();
        employeeRepository.findAll().stream().filter(emp -> emp instanceof ActiveEmployee)
                .map(ActiveEmployee.class::cast).forEach(employee -> {
            ActiveEmployeeDto activeEmployeeDto = new ActiveEmployeeDto();
            activeEmployeeDto.setId(employee.getId());
            activeEmployeeDto.setName(employee.getName());
            activeEmployeeDto.setSalary(employee.getSalary());
            activeEmployeeDto.setDateOfEmployment(employee.getDateOfEmployment());
            list.add(activeEmployeeDto);
        });
        return list;
    }

}
