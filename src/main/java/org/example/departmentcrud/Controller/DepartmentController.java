package org.example.departmentcrud.Controller;

import org.example.departmentcrud.Entity.DepartmentEntity;
import org.example.departmentcrud.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // POST - Create a new department
    @PostMapping
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity department,
                                       @RequestHeader("X-User") String createdBy) {
        return departmentService.createDepartment(department, createdBy);
    }

    // GET - Get all departments
    @GetMapping
    public List<DepartmentEntity> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // GET - Get a single department by ID
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id) {
        DepartmentEntity department = departmentService.getDepartmentById(id);
        if (department != null) {
            return ResponseEntity.ok(department);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT - Update a department
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentEntity> updateDepartment(@PathVariable Long id,
                                                       @RequestBody DepartmentEntity departmentDetails,
                                                       @RequestHeader("X-User") String updatedBy) {
        DepartmentEntity updatedDepartment = departmentService.updateDepartment(id, departmentDetails, updatedBy);
        if (updatedDepartment != null) {
            return ResponseEntity.ok(updatedDepartment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE - Void a department
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> voidDepartment(@PathVariable Long id,
                                               @RequestHeader("X-User") String voidedBy) {
        DepartmentEntity voidedDepartment = departmentService.voidDepartment(id, voidedBy);
        if (voidedDepartment != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
