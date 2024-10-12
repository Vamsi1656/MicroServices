package net.microservices.employeeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.microservices.employeeservice.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
