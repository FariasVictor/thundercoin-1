package com.invillia.ThunderCoin.mapper;

import com.invillia.ThunderCoin.domain.Origin;
import com.invillia.ThunderCoin.domain.Transaction;
import com.invillia.ThunderCoin.domain.request.TransactionRequest;
import com.invillia.ThunderCoin.domain.response.TransactionResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TransactionResponse transactionToTransactionResponse(final Transaction transaction){
        return TransactionResponse.builder()
                .origin(String.valueOf(transaction.getOriginId()))
                .quotation(String.valueOf(transaction.getQuotationId()))
                .account(String.valueOf(transaction.getAccountId()))
                .value(transaction.getValue())
                .build();
    }

    public Transaction transactionRequestToTransaction(final TransactionRequest transactionRequest){
       final Transaction transaction = new Transaction();

       transaction.setValue(transactionRequest.getValue());

        return transaction;
    }

    public List<TransactionResponse> transactionToTransactionResponse(final List<Transaction> transactions){
        return transactions.stream()
                .map(this::transactionToTransactionResponse)
                .collect(Collectors.toList());
    }
}
