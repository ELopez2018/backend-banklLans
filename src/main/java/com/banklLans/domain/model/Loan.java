package com.banklLans.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loans")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  @NotNull
  private Long userId;

  @NotNull
   private BigDecimal amount;

  @NotNull
  private int term;

  @NotNull
  @Enumerated(EnumType.STRING)
  private LoanStatus status;

  private LocalDate requestDate = LocalDate.now();
}
