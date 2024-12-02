package net.microservices.employeeservice.service.impl;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import net.microservices.employeeservice.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger Logger= LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepo;
	
//	private RestTemplate restTemplate;
	
	private WebClient webClient;
	
//	private APIClient apiClient;

	@Override
	public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//		Employee employee=new Employee(
//				employeeDto.getEmployeeid(),
//				employeeDto.getFirstName(),
//				employeeDto.getLastName(),
//				employeeDto.getEmail(),
//				employeeDto.getDepartmentCode()
//				);
		// Method from the mapper class
		Employee employee= EmployeeMapper.maptoEmployee(employeeDto);
		
		Employee savedemployee=employeeRepo.save(employee);


		
//		EmployeeDto savedemployeeDto=new EmployeeDto(
//				savedemployee.getEmployeeid(),
//				savedemployee.getFirstName(),
//				savedemployee.getLastName(),
//				savedemployee.getEmail(),
//				savedemployee.getDepartmentCode()
//
//				);

		// Method from the mapper class
		EmployeeDto savedemployeeDto=EmployeeMapper.maptoEmployeeDto(savedemployee);
				
		return savedemployeeDto;
	}

//	@CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Retry(name="${spring.application.name}",fallbackMethod = "getDefaultDepartment")
	@Override
	public APIResponseDto getEmployeeById(Long employeeId) {

		Logger.info("Inside EmployeeById() method");
		
	  Employee employee=employeeRepo.findById(employeeId).get();
		
//	  ResponseEntity<DepartmentDto> responseEntity= restTemplate.getForEntity("http://localhost:8081/api/departments/" + employee.getDepartmentCode(),
//			  DepartmentDto.class);
//	  
//	  DepartmentDto departmentDto=responseEntity.getBody();
	  
	 DepartmentDto departmentDto= webClient.get()
	  .uri("http://localhost:8081/api/departments/" + employee.getDepartmentCode())
	   .retrieve()
	   .bodyToMono(DepartmentDto.class)
	   .block();
	  
//	 DepartmentDto departmentDto= apiClient.getDepartment(employee.getDepartmentCode());
		
//		EmployeeDto employeeDto=new EmployeeDto(
//				employee.getEmployeeid(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail(),
//				employee.getDepartmentCode()
//
//				);

		// Method from the mapper class
		EmployeeDto employeeDto=EmployeeMapper.maptoEmployeeDto(employee);


		APIResponseDto apiResponseDto=new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		
		return apiResponseDto;
	}

	public APIResponseDto getDefaultDepartment(Long employeeId,Exception exception){

		Logger.info("Inside getDefaultDepartment() method");
		Employee employee=employeeRepo.findById(employeeId).get();

	DepartmentDto departmentDto=new DepartmentDto();
	departmentDto.setDepartmentName("R&D Department");
	departmentDto.setDepartmentCode("RD001");
	departmentDto.setDepartmentDescription("Research and Development Department");

//		EmployeeDto employeeDto=new EmployeeDto(
//				employee.getEmployeeid(),
//				employee.getFirstName(),
//				employee.getLastName(),
//				employee.getEmail(),
//				employee.getDepartmentCode()
//
//		);
		
		// Method from the mapper class
		EmployeeDto employeeDto=EmployeeMapper.maptoEmployeeDto(employee);

		APIResponseDto apiResponseDto=new APIResponseDto();
		apiResponseDto.setEmployee(employeeDto);
		apiResponseDto.setDepartment(departmentDto);
		return apiResponseDto;

	}
}
