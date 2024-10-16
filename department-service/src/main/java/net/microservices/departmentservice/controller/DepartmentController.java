package net.microservices.departmentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentservice;
	
	@PostMapping
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
		
		 DepartmentDto saveDepartment= departmentservice.saveDepartment(departmentDto);
		 
		 return new ResponseEntity<>(saveDepartment,HttpStatus.CREATED);
	}
	
	@GetMapping("{departmentCode}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentCode){
		
		DepartmentDto departmentDto= departmentservice.getDepartmentByCode(departmentCode);
		
		return new ResponseEntity<>(departmentDto,HttpStatus.OK);
	}

}
