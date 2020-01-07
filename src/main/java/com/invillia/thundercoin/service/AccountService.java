package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.domain.User;
import com.invillia.thundercoin.domain.request.AccountRequest;
import com.invillia.thundercoin.domain.response.AccountResponse;

import java.util.List;

public interface AccountService{
    List<AccountResponse> findAll();
    AccountResponse findById(final Long id);
    void create(final User user);
//    void delete(final Long id);
    void withdraw(final Double value, final Account account);
    void deposit(final Double value, final Account account);
}
