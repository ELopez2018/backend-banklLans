package com.banklLans.application.service;


import com.banklLans.domain.model.Loan;
import com.banklLans.domain.model.LoanStatus;
import com.banklLans.domain.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

  @Autowired
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
}