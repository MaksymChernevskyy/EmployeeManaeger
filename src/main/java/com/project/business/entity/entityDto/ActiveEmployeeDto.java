package com.project.business.entity.entityDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveEmployeeDto extends EmployeeDto {
    @NotNull(message = "Salary cannot be null")
    private Double salary;

    @NotNull(message = "Date pf employment cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfEmployment;
}
