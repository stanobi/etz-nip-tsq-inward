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

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "NIBS_REQUEST_LOG")
public class NIBSSRequestLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "METHOD_CALL")
    private String methodCall;

    @Column(name = "SESSION_ID")
    private String sessionId;

    @Column(name = "SOURCE_CODE")
    private String sourceCode;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    @Column(name = "CHANNEL_ID")
    private String channel;

    @Column(name = "DESTINATION_CODE")
    private String destinationCode;

    @Column(name = "RESPONSE_CODE")
    private String responseCode;

    @Column(name = "REMOTE_IP")
    private String remoteIp;

}
