package com.banklLans.application.service;


import com.banklLans.domain.model.Loan;
import com.banklLans.domain.model.LoanStatus;
import com.banklLans.domain.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@AllArgsConstructor
@Service
public class LoanService {

  private LoanRepository loanRepository;

  public Loan applyForLoan(Loan loan) {
    loan.setStatus(LoanStatus.PENDING);
    return loanRepository.save(loan);
  }

  public Loan approveLoan(Long loanId) {
    Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
    loan.setStatus(LoanStatus.APPROVED);
    return loanRepository.save(loan);
  }

  public Loan rejectLoan(Long loanId) {
    Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
    loan.setStatus(LoanStatus.REJECTED);
    return loanRepository.save(loan);
  }
  public Loan getLoanbyId(Long loanId) {
    return loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
  }
  public List<Loan> getLoanbyUser(Long userId) {
    return loanRepository.findAllById(Collections.singleton(userId));
  }

  public Loan updateLoanStatus(Long loanId, LoanStatus newStatus) {
    Loan loan = loanRepository.findById(loanId)
            .orElseThrow(() -> new RuntimeException("Loan not found with id " + loanId));

    loan.setStatus(newStatus);
    return loanRepository.save(loan);
  }
}