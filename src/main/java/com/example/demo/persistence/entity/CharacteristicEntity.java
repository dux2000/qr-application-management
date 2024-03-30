package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import java.util.UUID;

@Data
@Entity
@Table(name = "characteristics")
@Audited
@EqualsAndHashCode(of = "id")
public class CharacteristicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    private String code;
    private String value;
    private String globalCode;
}
