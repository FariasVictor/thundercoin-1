package com.invillia.thundercoin.controller;

import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;
import com.invillia.thundercoin.service.impl.QuotationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
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
        Long id = quotationServiceImpl.create(quotationRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/quotations/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/filter")
    public List<QuotationResponse> filterByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate dateInitial,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate dateFinal
    ){
        return quotationServiceImpl.findByDateInitialAndDateFinal(dateInitial, dateFinal);
    }
}
