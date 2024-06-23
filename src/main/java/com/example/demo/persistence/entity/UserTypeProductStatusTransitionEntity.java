package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_type_product_status_transition")
public class UserTypeProductStatusTransitionEntity {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "type_code", insertable = false, updatable = false)
    private UserTypeDefinitionEntity userTypeDefinition;

    @ManyToOne
    @JoinColumn(name = "status_code", insertable = false, updatable = false)
    private StatusEntity status;
}
