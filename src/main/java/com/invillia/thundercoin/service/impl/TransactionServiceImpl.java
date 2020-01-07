package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Account;
import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.Transaction;
import com.invillia.thundercoin.domain.request.TransactionRequest;
import com.invillia.thundercoin.domain.response.TransactionResponse;
import com.invillia.thundercoin.enums.StatusEnum;
import com.invillia.thundercoin.enums.TransactionType;
import com.invillia.thundercoin.exception.DataDisableException;
import com.invillia.thundercoin.exception.ObjectNotFoundException;
import com.invillia.thundercoin.mapper.TransactionMapper;
import com.invillia.thundercoin.repository.AccountRepository;
import com.invillia.thundercoin.repository.OriginRepository;
import com.invillia.thundercoin.repository.QuotationRepository;
import com.invillia.thundercoin.repository.TransactionRepository;
import com.invillia.thundercoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final AccountServiceImpl accountService;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final QuotationRepository quotationRepository;
    private final OriginRepository originRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImpl(final AccountServiceImpl accountService, final TransactionRepository transactionRepository,
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


    @Transactional
     public List<TransactionResponse> findAll() {
        final List<Transaction> transactions = transactionRepository.findAll();

        return transactionMapper.transactionToTransactionResponse(transactions);
    }

    @Transactional
    public TransactionResponse findById(Long id) {
        final Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Transação não encontrada!"));

        return transactionMapper.transactionToTransactionResponse(transaction);
    }

    @Transactional
    public Transaction create(TransactionRequest transactionRequest) {
        final Quotation quotation = quotationRepository.findById(transactionRequest.getQuotationId())
                .orElseThrow(() -> new ObjectNotFoundException("Quotação não encontrada!"));

        if (quotation.getStatus() == StatusEnum.DISABLED)
            throw new DataDisableException("Esta Quotação não esta ativa!");

        final Account account = accountRepository.findById(transactionRequest.getAccountId())
                .orElseThrow(() -> new ObjectNotFoundException("Conta não encontrada!"));

        if(account.getUser().getStatus() == StatusEnum.DISABLED)
            throw new DataDisableException("Esta Conta não esta ativa!");

        final Origin origin = originRepository.findById(transactionRequest.getOriginId())
                .orElseThrow(() -> new ObjectNotFoundException("Origem não encontrada!"));

        if(origin.getStatus() == StatusEnum.DISABLED)
            throw new DataDisableException("Esta Origem não esta ativa!");

        if(!validateTypeTransaction(transactionRequest.getTransactionType()))
            throw new ObjectNotFoundException("Tipo de conta inválida!");

       final Transaction transaction = transactionMapper.transactionRequestToTransaction(transactionRequest);

       transaction.setOrigin(origin);
       transaction.setQuotation(quotation);
       transaction.setAccount(account);
       transaction.setTransactionType(TransactionType.valueOf(transactionRequest.getTransactionType()));

       if(transaction.getTransactionType() == TransactionType.INPUT){
           accountService.deposit(transaction.getValue(), transaction.getAccount());
       }else{
           accountService.withdraw(transaction.getValue(), transaction.getAccount());
       }

       accountRepository.save(account);
       transaction.setValue(transactionRequest.getValue());

       return transactionRepository.save(transaction);
    }

    private boolean validateTypeTransaction(final String typeTransaction){
        for (TransactionType tp: TransactionType.values()) {
            if(tp.name().equals(typeTransaction)){
                return true;
            }
        }
        return false;
    }
}
