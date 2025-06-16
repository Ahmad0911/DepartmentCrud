package org.example.departmentcrud.Services;

import org.example.departmentcrud.Entity.DepartmentEntity;
import org.example.departmentcrud.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // CREATE
    public DepartmentEntity createDepartment(DepartmentEntity departmentEntity, String createdBy) {
        departmentEntity.setDateCreated(new Date());
        departmentEntity.setCreatedBy(createdBy);
        departmentEntity.setVoided(false);
        return departmentRepository.save(departmentEntity);
    }

    // READ
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    // UPDATE
    public DepartmentEntity updateDepartment(Long id, DepartmentEntity departmentDetails, String updatedBy) {
        DepartmentEntity department = departmentRepository.findById(id).orElse(null);
        if (department != null) {
            if (departmentDetails.getName() != null) {
                department.setName(departmentDetails.getName());
            }
            if (departmentDetails.getDescription() != null) {
                department.setDescription(departmentDetails.getDescription());
            }
            department.setDateUpdated(new Date());
            department.setUpdatedBy(updatedBy);
            return departmentRepository.save(department);
        }
        return null;
    }

    // DELETE (soft delete by voiding)
    public DepartmentEntity voidDepartment(Long id, String voidedBy) {
        DepartmentEntity department = departmentRepository.findById(id).orElse(null);
        if (department != null && !department.getVoided()) {
            department.setVoided(true);
            department.setDateVoided(new Date());
            department.setVoidedBy(voidedBy);
            return departmentRepository.save(department);
        }
        return null;
    }
}
