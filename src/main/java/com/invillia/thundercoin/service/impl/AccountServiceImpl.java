package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.domain.request.AccountRequest;
import com.invillia.thundercoin.domain.response.AccountResponse;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.exception.UserAlreadyRegistred;
import com.invillia.thundercoin.exception.ValueNotAllowed;
import com.invillia.thundercoin.mapper.AccountMapper;
import com.invillia.thundercoin.repository.AccountRepository;
import com.invillia.thundercoin.repository.UserRepository;
import com.invillia.thundercoin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Autowired
    public AccountServiceImpl(final AccountMapper accountMapper,
                              final AccountRepository accountRepository, final UserRepository userRepository) {

        this.accountMapper = accountMapper;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Account create(final AccountRequest accountRequest) {

        if(accountRepository.existsAccountByUserId(accountRequest.getUserId())){
            throw new UserAlreadyRegistred("Já existe uma conta para esse usuário!");
        }
        Account account = accountMapper.accountRequestToAccount(accountRequest);

        account.setUser(userRepository.findById(accountRequest.getUserId())
        .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado!")));
        account.setBalance(accountRequest.getBalance());
        account.setId(accountRequest.getId());

        return accountRepository.save(account);
    }

    @Transactional(readOnly = true)
    public List<AccountResponse> findAll() {
        final List<Account> accounts = accountRepository.findAll();

        return accountMapper.accountToAccountResponse(accounts);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountResponse findById(final Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountResponse)
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada!"));
    }

    @Transactional
    public void delete(final Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada!"));
        accountRepository.delete(account);
    }

    @Transactional
    public void withdraw(final Double value, final Account account) {
        if(value > 0){
            if (account.getBalance() >= value){
                account.setBalance(account.getBalance() - value);
            }else{
                throw new ValueNotAllowed("Você não possui saldo o suficiente para realizar esse saque!");
            }
        }else{
            throw new ValueNotAllowed("O valor deve ser maior que 0!");
        }

        accountRepository.save(account);
    }

    @Transactional
    public void deposit(final Double value, final Account account) {
        if(value > 0){
            account.setBalance(account.getBalance() + value);
        }else{
            throw new ValueNotAllowed("O valor deve ser maior que 0!");
        }

        accountRepository.save(account);
    }

}
