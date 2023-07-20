package com.etz.nipinward.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "NIBS_BLOCK")
public class NIBSSBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BLOCK_REF")
    private String blockRef;

    @Column(name = "RESPONSE_CODE")
    private String responseCode;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "ACCOUNT")
    private String account;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

}
