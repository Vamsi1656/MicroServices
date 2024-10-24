package net.microservices.employeeservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.AllArgsConstructor;
import net.microservices.employeeservice.dto.APIResponseDto;
import net.microservices.employeeservice.dto.DepartmentDto;
import net.microservices.employeeservice.dto.EmployeeDto;
import net.microservices.employeeservice.entity.Employee;
import net.microservices.employeeservice.repository.EmployeeRepository;
import net.microservices.employeeservice.service.APIClient;
import net.microservices.employeeservice.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepo;
	
//	private RestTemplate restTemplate;
	
//	private WebClient webClient
	
	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
		Employee employee=new Employee(
				employeeDto.getEmployeeid(),
				employeeDto.getFirstName(),
				employeeDto.getLastName(),
				employeeDto.getEmail(),
				employeeDto.getDepartmentCode()
				);
		
		Employee savedemployee=employeeRepo.save(employee);
		
		EmployeeDto savedemployeeDto=new EmployeeDto(
				savedemployee.getEmployeeid(),
				savedemployee.getFirstName(),
				savedemployee.getLastName(),
				savedemployee.getEmail(),
				savedemployee.getDepartmentCode()
				
				);
				
		return savedemployeeDto;
	}

	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {
		
	  Employee employee=employeeRepo.findById(employeeId).get();
		
//	  ResponseEntity<DepartmentDto> responseEntity= restTemplate.getForEntity("http://localhost:8081/api/departments/" + employee.getDepartmentCode(),
//			  DepartmentDto.class);
//	  
//	  DepartmentDto departmentDto=responseEntity.getBody();
	  
//	 DepartmentDto departmentDto= webClient.get()
//	  .uri("http://localhost:8081/api/departments/" + employee.getDepartmentCode())
//	   .retrieve()
//	   .bodyToMono(DepartmentDto.class)
//	   .block();
	  
	 DepartmentDto departmentDto= apiClient.getDepartment(employee.getDepartmentCode());
		
		EmployeeDto employeeDto=new EmployeeDto(
				employee.getEmployeeid(),
				employee.getFirstName(),
				employee.getLastName(),
				employee.getEmail(),
				employee.getDepartmentCode()
				
				);
		APIResponseDto apiResponseDto=new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		return apiResponseDto;
	}

}
