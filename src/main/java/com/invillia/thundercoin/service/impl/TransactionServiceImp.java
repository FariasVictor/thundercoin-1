package com.invillia.ThunderCoin.service.impl;

import com.invillia.ThunderCoin.domain.Account;
import com.invillia.ThunderCoin.domain.Origin;
import com.invillia.ThunderCoin.domain.Quotation;
import com.invillia.ThunderCoin.domain.Transaction;
import com.invillia.ThunderCoin.domain.request.TransactionRequest;
import com.invillia.ThunderCoin.domain.response.TransactionResponse;
import com.invillia.ThunderCoin.exception.ObjectNotFoundException;
import com.invillia.ThunderCoin.mapper.TransactionMapper;
import com.invillia.ThunderCoin.repository.AccountRepository;
import com.invillia.ThunderCoin.repository.OriginRepository;
import com.invillia.ThunderCoin.repository.QuotationRepository;
import com.invillia.ThunderCoin.repository.TransactionRepository;
import com.invillia.ThunderCoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImp implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final QuotationRepository quotationRepository;
    private final OriginRepository originRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public TransactionServiceImp(final TransactionRepository transactionRepository,
                                 final TransactionMapper transactionMapper,
                                 final QuotationRepository quotationRepository,
                                 final OriginRepository originRepository,
                                 final AccountRepository accountRepository) {
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

        final Account account = accountRepository.findById(transactionRequest.getUserId())
                .orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrada!"));

        final Origin origin = originRepository.findById(transactionRequest.getOriginId())
                .orElseThrow(() -> new ObjectNotFoundException("Origem não encontrada!"));

       final Transaction transaction = transactionMapper.transactionRequestToTransaction(transactionRequest);

       transaction.setOriginId(origin);
       transaction.setQuotationId(quotation);
       transaction.setAccountId(account);

       return transactionRepository.save(transaction);
    }
}
