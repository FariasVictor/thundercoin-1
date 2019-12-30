package com.invillia.ThunderCoin.service.impl;

import com.invillia.ThunderCoin.domain.Quotation;
import com.invillia.ThunderCoin.domain.request.QuotationRequest;
import com.invillia.ThunderCoin.domain.response.QuotationResponse;
import com.invillia.ThunderCoin.mapper.QuotationMapper;
import com.invillia.ThunderCoin.repository.QuotationRepository;
import com.invillia.ThunderCoin.service.QuotationService;
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
