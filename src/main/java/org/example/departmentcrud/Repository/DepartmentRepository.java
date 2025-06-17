package org.example.departmentcrud.Repository;

import org.example.departmentcrud.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    // Find all non-voided departments
    List<DepartmentEntity> findByVoidedFalse();

    // Find a specific non-voided department by ID
    Optional<DepartmentEntity> findByIdAndVoidedFalse(Long id);
}