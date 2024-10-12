package net.microservices.departmentservice.service;

import net.microservices.departmentservice.dto.DepartmentDto;

public interface DepartmentService {
	
	DepartmentDto saveDepartment(DepartmentDto departmentDto);
	
	
	DepartmentDto getDepartmentByCode(String departmentcode);

}
