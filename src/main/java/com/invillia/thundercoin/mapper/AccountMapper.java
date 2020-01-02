package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.domain.request.AccountRequest;
import com.invillia.thundercoin.domain.response.AccountResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public AccountResponse accountToAccountResponse(final Account account) {
        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setId(account.getId());
        accountResponse.setNameUser(account.getUser().getName());
        accountResponse.setBalance(account.getBalance());

        return accountResponse;
    }

    public List<AccountResponse> accountToAccountResponse(final List<Account> accounts) {
        return accounts.stream()
                .map(this::accountToAccountResponse)
                .collect(Collectors.toList());

    }

    public Account accountRequestToAccount(AccountRequest accountRequest){
        Account account = new Account();

        account.setBalance(accountRequest.getBalance());

        return account;
    }
}
