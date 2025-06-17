package org.example.departmentcrud.Services;

import org.example.departmentcrud.Entity.DepartmentEntity;
import org.example.departmentcrud.Repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentEntity createDepartment(DepartmentEntity department, String currentUsername) {
        department.setDateCreated(new Date());
        department.setCreatedBy(currentUsername);
        department.setVoided(false);
        return departmentRepository.save(department);
    }

    @Override
    public DepartmentEntity updateDepartment(Long id, DepartmentEntity departmentDetails, String currentUsername) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        department.setDateUpdated(new Date());
        department.setUpdatedBy(currentUsername);

        return departmentRepository.save(department);
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findByVoidedFalse();
    }

    @Override
    public DepartmentEntity getDepartmentById(Long id) {
        return departmentRepository.findByIdAndVoidedFalse(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
    }

    @Override
    public void deleteDepartment(Long id, String currentUsername) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        departmentRepository.delete(department);
    }

    @Override
    public DepartmentEntity voidDepartment(Long id, String currentUsername) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        department.setVoided(true);
        department.setDateVoided(new Date());
        department.setVoidedBy(currentUsername);

        return departmentRepository.save(department);
    }
}