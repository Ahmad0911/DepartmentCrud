package org.example.departmentcrud.Controller;

import org.example.departmentcrud.Entity.DepartmentEntity;
import org.example.departmentcrud.Services.DepartmentService; // Services (plural)
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentEntity> createDepartment(
            @RequestBody DepartmentEntity department,
            Authentication authentication) {
        String currentUsername = authentication.getName();
        DepartmentEntity createdDepartment = departmentService.createDepartment(department, currentUsername);
        return ResponseEntity.ok(createdDepartment);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
        List<DepartmentEntity> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id) {
        DepartmentEntity department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentEntity> updateDepartment(
            @PathVariable Long id,
            @RequestBody DepartmentEntity departmentDetails,
            Authentication authentication) {
        String currentUsername = authentication.getName();
        DepartmentEntity updatedDepartment = departmentService.updateDepartment(id, departmentDetails, currentUsername);
        return ResponseEntity.ok(updatedDepartment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id,
            Authentication authentication) {
        String currentUsername = authentication.getName();
        departmentService.deleteDepartment(id, currentUsername);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/void")
    public ResponseEntity<DepartmentEntity> voidDepartment(
            @PathVariable Long id,
            Authentication authentication) {
        String currentUsername = authentication.getName();
        DepartmentEntity voidedDepartment = departmentService.voidDepartment(id, currentUsername);
        return ResponseEntity.ok(voidedDepartment);
    }
}