package com.invillia.ThunderCoin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE)
    @Column(nullable = false)
    private List<Transaction> transaction;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;


    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private Boolean active;

}
