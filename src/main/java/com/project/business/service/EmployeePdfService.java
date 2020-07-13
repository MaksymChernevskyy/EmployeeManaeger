package com.project.business.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.business.entity.Employee;
import com.project.business.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EmployeePdfService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeePdfService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public byte[] createPdf() throws DocumentException {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, stream);
        document.open();
        document.add(getEmployees());
        document.close();
        return stream.toByteArray();
    }

    private PdfPTable getEmployees() {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(25);
        table.setSpacingAfter(25);
        table.setWidthPercentage(100);
        String[] tableHeaders = new String[]{"Id", "Employee Name"};
        addTableHeader(table, tableHeaders);
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            table.addCell(employee.getId().toString());
            table.addCell(employee.getName());
        }
        return table;
    }

    private void addTableHeader(PdfPTable table, String[] headers) {
        Stream.of(headers)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
}
