package com.invillia.thundercoin.controller;

import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;
import com.invillia.thundercoin.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quotations")
public class QuotationController {

    private QuotationServiceImpl quotationServiceImpl;

    @Autowired
    public QuotationController(QuotationServiceImpl quotationServiceImpl) {
        this.quotationServiceImpl = quotationServiceImpl;
    }

    @GetMapping
    public List<QuotationResponse> findAll(){
        return quotationServiceImpl.findAll();
    }

    @GetMapping("/{id}")
    public QuotationResponse findById(@PathVariable final Long id){
        return quotationServiceImpl.findById(id);
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody QuotationRequest quotationRequest){
        Long id = quotationServiceImpl.save(quotationRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/quotations/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }


    @PutMapping("/{id}")
    public HttpEntity<?> update(@PathVariable final Long id,
                                @RequestBody @Valid final QuotationRequest quotationRequest){
        quotationServiceImpl.update(quotationRequest, id);

        return ResponseEntity.noContent().build();
    }

}
