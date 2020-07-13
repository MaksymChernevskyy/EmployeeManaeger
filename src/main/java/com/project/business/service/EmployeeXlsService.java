package com.project.business.service;

import com.project.business.entity.Employee;
import com.project.business.repository.EmployeeRepository;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeXlsService {

    private EmployeeRepository employeeRepository;
    private static String[] columns = {"Name", "ID"};

    public EmployeeXlsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void createXLS() throws IOException {
        int rownum = 0;
        Row row;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees");
        List<Employee> list = employeeRepository.findAll();
        HSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);
        CreateTitle(row, style);
        createRecords(rownum, sheet, list);
        FileOutputStream fileOut = new FileOutputStream("employeeList.xls");
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
    }

    private void createRecords(int rownum, HSSFSheet sheet, List<Employee> list) {
        Row row;
        Cell cell;
        for (Employee emp : list) {
            rownum++;
            row = sheet.createRow(rownum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(emp.getId());
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(emp.getName());
        }
    }

    private void CreateTitle(Row row, HSSFCellStyle style) {
        Cell cell;
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("EmployeeId");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("EmployeeName");
        cell.setCellStyle(style);
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

}
