package com.invillia.ThunderCoin.controller;

import com.invillia.ThunderCoin.domain.request.QuotationRequest;
import com.invillia.ThunderCoin.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class QuotationController {

    private QuotationServiceImpl quotationServiceImpl;

    @Autowired
    public QuotationController(QuotationServiceImpl quotationServiceImpl) {
        this.quotationServiceImpl = quotationServiceImpl;
    }

    @PostMapping("/")
    public HttpEntity<?> save(@Valid @RequestBody QuotationRequest quotationRequest){
        Long id=quotationServiceImpl.save(quotationRequest);
        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path(("/{id}")).build(id);

        return ResponseEntity.created(location).build();
    }

}
