package net.microservices.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class APIResponseDto {
	
	private EmployeeDto employee;
	
	private DepartmentDto department;

}
