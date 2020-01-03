package com.invillia.thundercoin.domain;

import com.invillia.thundercoin.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quotation",nullable =  false)
    private Quotation quotationId;

    @ManyToOne
    @JoinColumn(name = "account",nullable = false)
    private Account accountId;

    @ManyToOne
    @JoinColumn(name = "origin",nullable = false)
    private Origin originId;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(nullable = false)
    private Double value;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createAt;
}
