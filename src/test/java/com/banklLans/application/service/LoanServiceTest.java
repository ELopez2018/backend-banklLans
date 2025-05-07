package com.banklLans.application.service;

import com.banklLans.domain.model.Loan;
import com.banklLans.domain.model.LoanStatus;
import com.banklLans.domain.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class LoanServiceTest {

  @Mock
  private LoanRepository loanRepository;

  private LoanService loanService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    loanService = new LoanService(loanRepository);
  }

  @Test
  void applyForLoan() {
    Loan loan = new Loan();
    loan.setAmount(new BigDecimal("1000.00"));
    loan.setStatus(LoanStatus.PENDING);

    when(loanRepository.save(any(Loan.class))).thenReturn(loan);

    Loan result = loanService.applyForLoan(loan);

    assertEquals(LoanStatus.PENDING, result.getStatus());
    verify(loanRepository).save(loan);
  }

  @Test
  void approveLoan() {
    Long loanId = 1L;
    Loan loan = new Loan();
    loan.setId(loanId);
    loan.setStatus(LoanStatus.PENDING);

    when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
    when(loanRepository.save(any(Loan.class))).thenReturn(loan);

    Loan result = loanService.approveLoan(loanId);

    assertEquals(LoanStatus.APPROVED, result.getStatus());
    verify(loanRepository).findById(loanId);
    verify(loanRepository).save(loan);
  }

  @Test
  void rejectLoan() {
    Long loanId = 1L;
    Loan loan = new Loan();
    loan.setId(loanId);
    loan.setStatus(LoanStatus.PENDING);

    when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
    when(loanRepository.save(any(Loan.class))).thenReturn(loan);

    Loan result = loanService.rejectLoan(loanId);

    assertEquals(LoanStatus.REJECTED, result.getStatus());
    verify(loanRepository).findById(loanId);
    verify(loanRepository).save(loan);
  }

  @Test
  void getLoanbyId() {
    Long loanId = 1L;
    Loan loan = new Loan();
    loan.setId(loanId);

    when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

    Loan result = loanService.getLoanbyId(loanId);

    assertNotNull(result);
    assertEquals(loanId, result.getId());
    verify(loanRepository).findById(loanId);
  }

  @Test
  void getLoanbyUser() {
    Long userId = 1L;
    Loan loan = new Loan();
    loan.setUserId(userId);

    when(loanRepository.findAllById(Collections.singleton(userId))).thenReturn(List.of(loan));

    List<Loan> result = loanService.getLoanbyUser(userId);

    assertNotNull(result);
    assertFalse(result.isEmpty());
    assertEquals(userId, result.get(0).getUserId());
    verify(loanRepository).findAllById(Collections.singleton(userId));
  }

  @Test
  void updateLoanStatus() {
    Long loanId = 1L;
    Loan loan = new Loan();
    loan.setId(loanId);
    loan.setStatus(LoanStatus.PENDING);

    when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
    when(loanRepository.save(any(Loan.class))).thenReturn(loan);

    Loan result = loanService.updateLoanStatus(loanId, LoanStatus.APPROVED);

    assertEquals(LoanStatus.APPROVED, result.getStatus());
    verify(loanRepository).findById(loanId);
    verify(loanRepository).save(loan);
  }
}