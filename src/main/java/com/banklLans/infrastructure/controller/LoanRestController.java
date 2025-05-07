package com.banklLans.infrastructure.controller;


import com.banklLans.application.dto.LoanRequestDto;
import com.banklLans.application.service.LoanService;
import com.banklLans.domain.model.Loan;
import com.banklLans.domain.model.LoanStatus;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
  @RequestMapping("/api/loans")
  public class LoanRestController {

  @Autowired
  private LoanService loanService;
  // Endpoint para solicitar un préstamo
  @PostMapping("/apply")
  public ResponseEntity<Loan> applyForLoan(@RequestBody @Valid Loan loan) {
    return ResponseEntity.ok(loanService.applyForLoan(loan));
  }
  // Endpoint para aprobar un préstamo por su ID
  @PostMapping("/approve/{loanId}")
  public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
    return ResponseEntity.ok(loanService.approveLoan(loanId));
  }
  // Endpoint para rechazar un préstamo por su ID
  @PostMapping("/reject/{loanId}")
  public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId) {
    return ResponseEntity.ok(loanService.rejectLoan(loanId));
  }
  // Endpoint para obtener un préstamo por su ID
  @PostMapping("/status/{loanId}")
  public ResponseEntity<Loan> getLoanByStatus(@PathVariable Long loanId) {
    return ResponseEntity.ok(loanService.rejectLoan(loanId));
  }

  // Endpoint para obtener un préstamo por su ID
  @GetMapping("/{loanId}")
  public ResponseEntity<Loan> getLoanById(@PathVariable Long loanId) {
    try {
      Loan loan = loanService.getLoanbyId(loanId);
      return ResponseEntity.ok(loan);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  // Endpoint para obtener todos los préstamos de un usuario
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Loan>> getLoansByUser(@PathVariable Long userId) {
    List<Loan> loans = loanService.getLoanbyUser(userId);
    if (loans.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    return ResponseEntity.ok(loans);
  }

  // Endpoint para actualizar el estado de un préstamo
  @PutMapping("/{loanId}/status")
  public ResponseEntity<Loan> updateLoanStatus(
          @PathVariable Long loanId,
          @RequestParam LoanStatus status) {
    try {
      Loan updatedLoan = loanService.updateLoanStatus(loanId, status);
      return ResponseEntity.ok(updatedLoan);
    } catch (RuntimeException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }
}
