package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;

import java.time.LocalDate;
import java.util.List;


public interface QuotationService {

     List<QuotationResponse> findAll();

     QuotationResponse findById(Long id);

     Long create(QuotationRequest quotationRequest);

     List<QuotationResponse> findByDateInitialAndDateFinal(final LocalDate dateInitial, final LocalDate dateFinal);

}
