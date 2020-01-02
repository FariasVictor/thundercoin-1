package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.request.QuotationRequest;
import com.invillia.thundercoin.domain.response.QuotationResponse;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuotationMapper {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public QuotationResponse quotationToQuotationResponse(final Quotation quotation){
        return QuotationResponse.builder()
                .active(true)
                .value(100.0)
                .build();
    }

    public List<QuotationResponse> quotationToQuotationResponse(final List<Quotation> quotations) {
        return quotations.stream()
                .map(this::quotationToQuotationResponse)
                .collect(Collectors.toList());
    }

    public Quotation quotationRequestToQuotation(final QuotationRequest quotationRequest) {

        Quotation quotation = new Quotation();

        quotation.setValue(quotationRequest.getValue());
        quotation.setActive(quotationRequest.getActive());

        return quotation;
    }

    public void updateQuotationByQuotationRequest(final Quotation quotation, final QuotationRequest quotationRequest) {

        quotation.setActive(quotationRequest.getActive());
        quotation.setValue(quotationRequest.getValue());
    }
}
