package com.invillia.thundercoin.service.impl;

import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;
import com.invillia.thundercoin.mapper.QuotationMapper;
import com.invillia.thundercoin.repository.QuotationRepository;
import com.invillia.thundercoin.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationServiceImpl implements QuotationService {

    private QuotationRepository quotationRepository;
    private QuotationMapper quotationMapper;

    @Autowired
    public QuotationServiceImpl(QuotationRepository quotationRepository, QuotationMapper quotationMapper) {
        this.quotationRepository = quotationRepository;
        this.quotationMapper = quotationMapper;
    }

    @Override
    public Long save(QuotationRequest quotationRequest) {
        Quotation quotation=quotationMapper.quotationRequestToQuotation(quotationRequest);
        Long id = quotationRepository.save(quotation).getId();
        return id;
    }

    @Override
    public List<QuotationResponse> findAll() {
        return null;
    }

    @Override
    public QuotationResponse findById(Long id) {
        return null;
    }

    @Override
    public void update(QuotationRequest quotationRequest, Long id) {

    }

    @Override
    public void delete(Long id) {

    }
}
