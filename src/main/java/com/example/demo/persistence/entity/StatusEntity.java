package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "status")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusEntity {
    @Id
    private String code;

    private String description;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentStatus")
    private List<StatusTransitionEntity> transitions;
}
