package net.microservices.departmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.microservices.departmentservice.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	
	Department findByDepartmentCode(String departmentCode);

}
