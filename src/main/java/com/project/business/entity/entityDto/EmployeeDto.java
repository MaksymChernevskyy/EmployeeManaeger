package com.project.business.entity.entityDto;

import lombok.*;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class EmployeeDto {

    private Long id;

    @Size(max = 50, message = "Name may contain only 50 symbols")
    @NotNull(message = "Name cannot be null")
    private String name;
}
