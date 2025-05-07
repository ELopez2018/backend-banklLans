package com.banklLans.infrastructure.controller;


import com.banklLans.application.dto.LoanRequestDto;
import com.banklLans.application.service.LoanService;
import com.banklLans.domain.model.Loan;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
  @RequestMapping("/api/loans")
  public class LoanRestController {

  @Autowired
  private LoanService loanService;

  @PostMapping("/apply")
  public ResponseEntity<Loan> applyForLoan(@RequestBody @Valid Loan loan) {
    return ResponseEntity.ok(loanService.applyForLoan(loan));
  }

  @PostMapping("/approve/{loanId}")
  public ResponseEntity<Loan> approveLoan(@PathVariable Long loanId) {
    return ResponseEntity.ok(loanService.approveLoan(loanId));
  }

  @PostMapping("/reject/{loanId}")
  public ResponseEntity<Loan> rejectLoan(@PathVariable Long loanId) {
    return ResponseEntity.ok(loanService.rejectLoan(loanId));
  }
}
