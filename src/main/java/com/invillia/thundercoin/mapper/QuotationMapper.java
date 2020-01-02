package com.invillia.thundercoin.mapper;

import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.domain.request.QuotationRequest;
import org.springframework.stereotype.Component;

@Component
public class QuotationMapper {
    public Quotation quotationRequestToQuotation(QuotationRequest quotationRequest){
        Quotation quotation= new Quotation();
        quotation.setValue(quotationRequest.getValue());
        quotation.setActive(quotationRequest.getActive());
        return quotation;
    }
}
