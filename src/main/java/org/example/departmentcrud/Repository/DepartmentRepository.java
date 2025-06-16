package org.example.departmentcrud.Repository;

import org.example.departmentcrud.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}