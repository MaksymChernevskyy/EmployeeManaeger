package com.project.business.controller;

import com.itextpdf.text.DocumentException;
import com.project.business.entity.Employee;
import com.project.business.entity.entityDto.ActiveEmployeeDto;
import com.project.business.entity.entityDto.EmployeeDto;
import com.project.business.service.EmployeePdfService;
import com.project.business.service.EmployeeService;
import com.project.business.service.EmployeeXlsService;
import com.project.business.service.GetEnversService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revisions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(EmployeeController.BASE_URL)
public class EmployeeController {
    public static final String BASE_URL = "employee";
    private EmployeeService employeeService;
    private EmployeePdfService employeePdfService;
    private EmployeeXlsService employeeXlsService;
    private GetEnversService getEnversService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeePdfService employeePdfService, EmployeeXlsService employeeXlsService, GetEnversService getEnversService) {
        this.employeeService = employeeService;
        this.employeePdfService = employeePdfService;
        this.employeeXlsService = employeeXlsService;
        this.getEnversService = getEnversService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        Employee addedEmployee = employeeService.saveEmployee(employeeDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create(String.format("/employee/%d", addedEmployee.getId())));
        return getResponseForSuccess(addedEmployee, responseHeaders);
    }

    @PostMapping("/postActiveEmployee")
    public ResponseEntity<?> createActiveEmployee(@Valid @RequestBody ActiveEmployeeDto employeeDto) {
        Employee addedEmployee = employeeService.addActiveEmployee(employeeDto);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(URI.create(String.format("/activeemployee/%d", addedEmployee.getId())));
        return getResponseForSuccess(addedEmployee, responseHeaders);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> update(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody(required = false) EmployeeDto employeeDto) {
        employeeService.updateEmployee(employeeId, employeeDto);
        return getResponseForSuccess(employeeDto);
    }

    @PutMapping("/updateActiveEmployee/{employeeId}")
    public ResponseEntity<?> update(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody(required = false) ActiveEmployeeDto activeEmployeeDto) {
        employeeService.updateActiveEmployee(employeeId, activeEmployeeDto);
        return getResponseForSuccess(activeEmployeeDto);
    }

    @GetMapping({"/"})
    public ResponseEntity<?> getAllEmployees() {
        List<EmployeeDto> list = employeeService.getGetAllEmployees();
        return getResponseForSuccess(list);
    }

    @GetMapping("/allActiveEmployees")
    public ResponseEntity<?> getAllActiveEmployees() {
        List<ActiveEmployeeDto> list = employeeService.getGetAllActiveEmployees();
        return responseForSuccess(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pdf")
    public ResponseEntity<?> getPdf() throws DocumentException {
        byte[] employeeListAsPdf = employeePdfService.createPdf();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_PDF);
        return getResponseForSuccess(employeeListAsPdf, responseHeaders);
    }

    @GetMapping({"/{id}/audit"})
    Revisions<Integer, Employee> getRevisionsById(@PathVariable Long id) {
        return getEnversService.getRevisionsById(id);
    }

    @GetMapping("/xls")
    public ResponseEntity<?> getXls() throws IOException {
        employeeXlsService.createXLS();
        return getResponseForSuccess("Excel is created");
    }

    private ResponseEntity<?> getResponseForSuccess(byte[] employeeListAsPdf, HttpHeaders responseHeaders) {
        return new ResponseEntity<>(employeeListAsPdf, responseHeaders, HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseForSuccess(String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseForSuccess(List<EmployeeDto> list) {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private ResponseEntity<List<ActiveEmployeeDto>> responseForSuccess(List<ActiveEmployeeDto> list) {
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private ResponseEntity<?> getResponseForSuccess(Employee addedEmployee, HttpHeaders responseHeaders) {
        return new ResponseEntity<>(addedEmployee, responseHeaders, HttpStatus.CREATED);
    }

    private ResponseEntity<?> getResponseForSuccess(@RequestBody @Valid EmployeeDto employeeDto) {
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }
}
