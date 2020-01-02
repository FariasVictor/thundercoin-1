package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.domain.request.AccountRequest;
import com.invillia.thundercoin.domain.response.AccountResponse;

import java.util.List;

public interface AccountService{
    Account create(final AccountRequest accountRequest);
    List<AccountResponse> findAll();
    AccountResponse findById(final Long id);
    void delete(final Long id);
    void withdraw(final Double value, final Account account);
    void deposit(final Double value, final Account account);
}
