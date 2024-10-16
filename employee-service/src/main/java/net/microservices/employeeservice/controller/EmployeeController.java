package net.microservices.employeeservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.microservices.employeeservice.dto.APIResponseDto;
import net.microservices.employeeservice.dto.EmployeeDto;
import net.microservices.employeeservice.service.EmployeeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto saveemployee =employeeservice.saveEmployee(employeeDto);
		
		return new ResponseEntity<>(saveemployee,HttpStatus.CREATED);		
	}
	
	@GetMapping("{employeeid}")
	public ResponseEntity<APIResponseDto> getByEmployee(@PathVariable Long employeeid){
		APIResponseDto apiResponseDto= employeeservice.getEmployeeById(employeeid);
		return new ResponseEntity<>(apiResponseDto,HttpStatus.OK);
		
	}
	

}
