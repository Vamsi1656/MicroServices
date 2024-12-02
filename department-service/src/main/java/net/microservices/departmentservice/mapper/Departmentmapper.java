package net.microservices.departmentservice.mapper;

import net.microservices.departmentservice.dto.DepartmentDto;
import net.microservices.departmentservice.entity.Department;

public class Departmentmapper {

    public static DepartmentDto maptoDepartmentDto(Department department){
        DepartmentDto departmentDto=new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()

        );
        return departmentDto;
    }

    public static Department maptoDepartment(DepartmentDto departmentDto){
        Department department=new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        return department;
    }
}
