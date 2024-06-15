package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "product_type")
@Data
@Audited
public class ProductTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    private String code;

    private String name;
}
