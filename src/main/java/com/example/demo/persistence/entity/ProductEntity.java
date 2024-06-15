package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "product")
@Data
@Audited
@EqualsAndHashCode(of = "id")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_code", referencedColumnName = "code")
    @Audited(targetAuditMode = NOT_AUDITED)
    private StatusEntity status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_code", referencedColumnName = "code")
    @Audited(targetAuditMode = NOT_AUDITED)
    private ProductTypeEntity type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "current_user_id", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    private UserEntity currentUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REMOVE }, mappedBy = "product", orphanRemoval = true)
    private List<CharacteristicEntity> characteristics;

    private LocalDateTime created;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    private UserEntity createdBy;

    private LocalDateTime updated;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", referencedColumnName = "id")
    @Audited(targetAuditMode = NOT_AUDITED)
    private UserEntity updatedBy;

    private LocalDateTime deleted;
}
