package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "status_transition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusTransitionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "current_status_code", referencedColumnName = "code")
  private StatusEntity currentStatus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "next_status_code", referencedColumnName = "code")
  private StatusEntity nextStatus;
}