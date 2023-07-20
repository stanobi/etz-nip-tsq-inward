package com.etz.nipinward.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "NIBS_MANDATE_ADVICE_REQUESTS")
public class NIBSSMandateAdviceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "REF")
    private String ref;

    @Column(name = "DEBIT_ACCOUNT")
    private String debitAccount;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "BENEFICIARY_ACCOUNT")
    private String beneficiaryAccount;

    @Column(name = "REQUEST_DATE")
    private LocalDateTime requestDate;
}
