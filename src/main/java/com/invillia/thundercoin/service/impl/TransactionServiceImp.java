package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.Transaction;
import com.invillia.thundercoin.domain.request.TransactionRequest;
import com.invillia.thundercoin.domain.response.TransactionResponse;
import com.invillia.thundercoin.enums.OriginType;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.mapper.TransactionMapper;
import com.invillia.thundercoin.repository.AccountRepository;
import com.invillia.thundercoin.repository.OriginRepository;
import com.invillia.thundercoin.repository.QuotationRepository;
import com.invillia.thundercoin.repository.TransactionRepository;
import com.invillia.thundercoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    private final AccountServiceImpl accountService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final QuotationRepository quotationRepository;
    private final OriginRepository originRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImp(final AccountServiceImpl accountService, final TransactionRepository transactionRepository,
                                 final TransactionMapper transactionMapper,
                                 final QuotationRepository quotationRepository,
                                 final OriginRepository originRepository,
                                 final AccountRepository accountRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.quotationRepository = quotationRepository;
        this.originRepository = originRepository;
        this.accountRepository = accountRepository;
    }


    @Override
     public List<TransactionResponse> findAll() {
        final List<Transaction> transactions = transactionRepository.findAll();

        return transactionMapper.transactionToTransactionResponse(transactions);
    }

    @Override
    public TransactionResponse findById(Long id) {
        final Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Transação não encontrada!"));

        return transactionMapper.transactionToTransactionResponse(transaction);
    }

    @Override
    public Transaction save(TransactionRequest transactionRequest) {
        final Quotation quotation = quotationRepository.findById(transactionRequest.getQuotationId())
                .orElseThrow(() -> new ObjectNotFoundException("Quotação não encontrada!"));

        final Account account = accountRepository.findById(transactionRequest.getAccountId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrada!"));

        final Origin origin = originRepository.findById(transactionRequest.getOriginId())
                .orElseThrow(() -> new ObjectNotFoundException("Origem não encontrada!"));

       final Transaction transaction = transactionMapper.transactionRequestToTransaction(transactionRequest);

       transaction.setOriginId(origin);
       transaction.setQuotationId(quotation);
       transaction.setAccountId(account);

       if(origin.getOriginType() == OriginType.INPUT){
           accountService.deposit(transaction.getValue(), transaction.getAccountId());
       }else{
           accountService.withdraw(transaction.getValue(), transaction.getAccountId());

       }

       accountRepository.save(account);
       transaction.setValue(transactionRequest.getValue());

       return transactionRepository.save(transaction);
    }
}
