package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.Origin;
import com.invillia.thundercoin.domain.Transaction;
import com.invillia.thundercoin.domain.request.TransactionRequest;
import com.invillia.thundercoin.domain.response.TransactionResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public TransactionResponse transactionToTransactionResponse(final Transaction transaction){
        return TransactionResponse.builder()
                    .origin(transaction.getOrigin().getName())
                    .valueQuotation(transaction.getQuotation().getValue())
                    .userAccount(transaction.getAccount().getUser().getName())
                    .value(transaction.getValue())
                    .dateTransaction(transaction.getDateTransaction().format(formatter))
                    .transactionType(transaction.getTransactionType().toString())
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
