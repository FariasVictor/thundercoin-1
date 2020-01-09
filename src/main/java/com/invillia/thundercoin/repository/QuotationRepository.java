package com.invillia.thundercoin.repository;

import com.invillia.thundercoin.domain.Quotation;
import com.invillia.thundercoin.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation,Long> {
    Quotation findByStatus(final StatusEnum statusEnum);

    List<Quotation> findTop5ByOrderByCreatedAtDesc();

    List<Quotation> findByCreatedAtBetweenOrderByIdAsc(final LocalDateTime dateInitial, final LocalDateTime dateFinal);
}
