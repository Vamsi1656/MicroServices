package net.microservices.employeeservice.mapper;

import net.microservices.employeeservice.dto.EmployeeDto;
import net.microservices.employeeservice.entity.Employee;

public class EmployeeMapper {


    public static EmployeeDto maptoEmployeeDto(Employee employee){
        EmployeeDto employeeDto=new EmployeeDto(
                employee.getEmployeeid(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );
        return employeeDto;
    }

    public static Employee maptoEmployee(EmployeeDto employeeDto){
        Employee employee=new Employee(
                employeeDto.getEmployeeid(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        return employee;
    }
}
