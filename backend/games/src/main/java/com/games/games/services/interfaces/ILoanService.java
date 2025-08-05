package com.games.games.services.interfaces;

import com.games.games.infrastructure.dto.LoanRequest;
import com.games.games.infrastructure.dto.LoanResponse;

import java.util.List;

public interface ILoanService {
    LoanResponse create(LoanRequest req);
    List<LoanResponse> getAll();
    LoanResponse marcarComoDevuelto(Long id);
}