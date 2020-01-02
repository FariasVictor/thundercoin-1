package com.invillia.thundercoin.controller;

import com.invillia.thundercoin.domain.request.TransactionRequest;
import com.invillia.thundercoin.domain.response.TransactionResponse;
import com.invillia.thundercoin.service.TransactionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    private List<TransactionResponse> findAll(){
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    private TransactionResponse findById(@PathVariable final Long id){
        return transactionService.findById(id);
    }

    @PostMapping
    private HttpEntity<?> save(@RequestBody final TransactionRequest transactionRequest){

        final Long idTransaction  = transactionService.save(transactionRequest).getId();

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/transactions/{id}")
                .build(idTransaction);

        return ResponseEntity.created(location).build();
    }
}
