package com.project.business.usecases.employee;

import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.EmployeeDto;
import com.project.business.repository.EmployeeRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllEmployeesUseCase {
    private EmployeeRepository employeeRepository;

    public GetAllEmployeesUseCase withRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        return this;
    }

    public List<EmployeeDto> run() {
        return getAllEmployees();
    }

    private List<EmployeeDto> getAllEmployees() {
        List<EmployeeDto> list = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setId(employee.getId());
            employeeDto.setName(employee.getName());
            list.add(employeeDto);
        }
        return list;
    }
}
