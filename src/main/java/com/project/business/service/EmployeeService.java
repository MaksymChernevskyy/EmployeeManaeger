package com.project.business.service;

import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.ActiveEmployeeDto;
import com.project.business.entity.entityDto.EmployeeDto;
import com.project.business.repository.EmployeeRepository;
import com.project.business.usecases.activeemployee.AddActiveEmployeeUseCase;
import com.project.business.usecases.activeemployee.GetAllActiveEmployeesUseCase;
import com.project.business.usecases.activeemployee.UpdateActiveEmployeeUseCase;
import com.project.business.usecases.employee.AddEmployeeUseCase;
import com.project.business.usecases.employee.DeleteEmployeeUseCase;
import com.project.business.usecases.employee.GetAllEmployeesUseCase;
import com.project.business.usecases.employee.UpdateEmployeeUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private AddEmployeeUseCase addEmployeeUseCase;
    private DeleteEmployeeUseCase deleteEmployeeUseCase;
    private UpdateEmployeeUseCase updateEmployeeUseCase;
    private GetAllEmployeesUseCase getAllEmployeesUseCase;
    private AddActiveEmployeeUseCase addActiveEmployeeUseCase;
    private UpdateActiveEmployeeUseCase updateActiveEmployeeUseCase;
    private GetAllActiveEmployeesUseCase getAllActiveEmployeesUseCase;

@Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           AddEmployeeUseCase addEmployeeUseCase,
                           DeleteEmployeeUseCase deleteEmployeeUseCase,
                           UpdateEmployeeUseCase updateEmployeeUseCase,
                           GetAllEmployeesUseCase getAllEmployeesUseCase,
                           AddActiveEmployeeUseCase addActiveEmployeeUseCase,
                           UpdateActiveEmployeeUseCase updateActiveEmployeeUseCase,
                           GetAllActiveEmployeesUseCase getAllActiveEmployeesUseCase) {
        this.employeeRepository = employeeRepository;
        this.addEmployeeUseCase = addEmployeeUseCase;
        this.deleteEmployeeUseCase = deleteEmployeeUseCase;
        this.updateEmployeeUseCase = updateEmployeeUseCase;
        this.getAllEmployeesUseCase = getAllEmployeesUseCase;
        this.addActiveEmployeeUseCase = addActiveEmployeeUseCase;
        this.updateActiveEmployeeUseCase = updateActiveEmployeeUseCase;
        this.getAllActiveEmployeesUseCase = getAllActiveEmployeesUseCase;
    }

    public Employee saveEmployee(EmployeeDto employeeDto) {
        return addEmployeeUseCase
                .withRepository(employeeRepository)
                .withEmployeeDto(employeeDto)
                .run();
    }

    public void deleteEmployee(Long employeeId) {
        deleteEmployeeUseCase
                .withRepository(employeeRepository)
                .forEmployeeId(employeeId)
                .run();
    }

    public List<EmployeeDto> getGetAllEmployees() {
        return getAllEmployeesUseCase
                .withRepository(employeeRepository)
                .run();
    }

    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        return updateEmployeeUseCase
                .withEmployeeRepository(employeeRepository)
                .withEmployeeDto(employeeDto)
                .forEmployeeId(id)
                .run();
    }

    public Employee addActiveEmployee(ActiveEmployeeDto activeEmployeeDto){
        return addActiveEmployeeUseCase
                .withRepository(employeeRepository)
                .forAciveEmployeeDto(activeEmployeeDto)
                .run();
    }

    public Employee updateActiveEmployee(Long id, ActiveEmployeeDto activeEmployeeDto) {
        return updateActiveEmployeeUseCase
                .withEmployeeRepository(employeeRepository)
                .withEmployeeDto(activeEmployeeDto)
                .forEmployeeId(id)
                .run();
    }

    public List<ActiveEmployeeDto> getGetAllActiveEmployees() {
        return getAllActiveEmployeesUseCase
                .withRepository(employeeRepository)
                .run();
    }
}
