package com.invillia.thundercoin.controller;

import com.invillia.thundercoin.domain.request.AccountRequest;
import com.invillia.thundercoin.domain.response.AccountResponse;
import com.invillia.thundercoin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }


    @GetMapping
    public List<AccountResponse> findAll(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable final Long id){
        return accountService.findById(id);
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody AccountRequest accountRequest){
        Long accountId = accountService.create(accountRequest).getId();

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/accounts/{id}")
                .build(accountId);

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping
    public HttpEntity<?> delete(@PathVariable final Long id){
        accountService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
