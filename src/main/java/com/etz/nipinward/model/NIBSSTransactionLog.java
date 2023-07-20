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
@Table(name = "NIBS_TRANSACTION_LOG")
public class NIBSSTransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SOURCE_RESPONSE_CODE")
    private String sourceResponseCode;

    @Column(name = "RESPONSE_CODE")
    private String responseCode;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "UNIQUE_TRANSID")
    private String uniqueTransId;

    @Column(name = "CHANNEL_ID")
    private String channel;

    @Column(name = "TRANSACTION_AMOUNT")
    private String amount;

    @Column(name = "SOURCE_CODE")
    private String sourceCode;

    @Column(name = "DESTINATION_CODE")
    private String destinationCode;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    @Column(name = "PAYMENT_REFERENCE")
    private String paymentReference;

    @Column(name = "MANDATE_REFERENCE")
    private String mandateReference;

    @Column(name = "BENEFICIARY_ACCOUNT_NUMBER")
    private String beneficiaryAccountNumber;

    @Column(name = "DEBIT_ACCOUNT_NUMBER")
    private String debitAccountNumber;

    @Column(name = "TRANS_TYPE")
    private String transType;

    @Column(name = "TRANS_NARRATION")
    private String transNarration;

    @Column(name = "REMOTE_IP")
    private String remoteIp;

    @Column(name = "CREDITED")
    private boolean credited = false;

    @Column(name = "RETRIAL_COUNT")
    private int retrialCount = 0;

}
