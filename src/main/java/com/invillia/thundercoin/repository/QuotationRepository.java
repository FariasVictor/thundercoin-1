package com.invillia.thundercoin.repository;

import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
    Quotation findByStatus(final StatusEnum statusEnum);

    @Query("select q from Quotation q order by q.createdAt desc")
    List<Quotation> findAllOrderByCreatedAt();
}
