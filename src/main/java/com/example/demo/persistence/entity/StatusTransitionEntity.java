package com.example.demo.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "status_transition")
@Data
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