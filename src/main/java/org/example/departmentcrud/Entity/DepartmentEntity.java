package org.example.departmentcrud.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_updated")
    private Date dateUpdated;

    @Column(name = "date_voided")
    private Date dateVoided;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "voided_by")
    private String voidedBy;

    private Boolean voided = false;

    // Custom constructor for required fields
    public DepartmentEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
