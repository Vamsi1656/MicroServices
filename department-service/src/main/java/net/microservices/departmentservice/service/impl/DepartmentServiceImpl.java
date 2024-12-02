package net.microservices.departmentservice.service.impl;

import net.microservices.departmentservice.mapper.Departmentmapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.entity.Department;
import net.microservices.departmentservice.repository.DepartmentRepository;
import net.microservices.departmentservice.service.DepartmentService;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
	
	private DepartmentRepository departmentrepo;
	
	
	@Override
	public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
		

//		Department department=new Department(
//				departmentDto.getId(),
//				departmentDto.getDepartmentName(),
//				departmentDto.getDepartmentDescription(),
//				departmentDto.getDepartmentCode()
//				);


         // Mapper method in case of above code
		Department department= Departmentmapper.maptoDepartment(departmentDto);
		
		Department savedDepartment=departmentrepo.save(department);


// covert savedepartment into departmentdto;
//		DepartmentDto savedDepartmentDto=new DepartmentDto(
//				savedDepartment.getId(),
//				savedDepartment.getDepartmentName(),
//				savedDepartment.getDepartmentDescription(),
//				savedDepartment.getDepartmentCode()
//				);

		// Mapper method in case of above code
		DepartmentDto savedDepartmentDto=Departmentmapper.maptoDepartmentDto(savedDepartment);
				
		
		return savedDepartmentDto;
	}


	@Override
	public DepartmentDto getDepartmentByCode(String departmentcode) {
		
		Department department = departmentrepo.findByDepartmentCode(departmentcode);
//
//		DepartmentDto departmentDto=new DepartmentDto(
//				department.getId(),
//				department.getDepartmentName(),
//				department.getDepartmentDescription(),
//				department.getDepartmentCode()
//				);

		DepartmentDto departmentDto=Departmentmapper.maptoDepartmentDto(department);
		
		return departmentDto;
	}

}
