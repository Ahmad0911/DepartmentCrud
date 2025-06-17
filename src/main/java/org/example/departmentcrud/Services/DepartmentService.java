package org.example.departmentcrud.Services;

import org.example.departmentcrud.Entity.DepartmentEntity;
import java.util.List;

public interface DepartmentService {
    DepartmentEntity createDepartment(DepartmentEntity department, String currentUsername);
    DepartmentEntity updateDepartment(Long id, DepartmentEntity departmentDetails, String currentUsername);
    List<DepartmentEntity> getAllDepartments();
    DepartmentEntity getDepartmentById(Long id);
    void deleteDepartment(Long id, String currentUsername);
    DepartmentEntity voidDepartment(Long id, String currentUsername);
}