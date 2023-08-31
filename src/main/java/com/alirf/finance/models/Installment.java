package com.alirf.finance.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dateDue;
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "id_transaction")
    private Transaction transaction;

    // n da parcela?
}