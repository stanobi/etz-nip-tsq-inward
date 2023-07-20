package com.etz.nipinward.dto.nibss;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "FTAdviceDebitResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FTAdviceDebitResponse {

    @XmlElement(name = "SessionID")
    private String sessionID;

    @XmlElement(name = "NameEnquiryRef")
    private String nameEnquiryRef;

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "DebitAccountName")
    private String debitAccountName;

    @XmlElement(name = "DebitAccountNumber")
    private String debitAccountNumber;

    @XmlElement(name = "DebitBankVerificationNumber")
    private String debitBankVerificationNumber;

    @XmlElement(name = "DebitKYCLevel")
    private String debitKYCLevel;

    @XmlElement(name = "BeneficiaryAccountName")
    private String beneficiaryAccountName;

    @XmlElement(name = "BeneficiaryAccountNumber")
    private String beneficiaryAccountNumber;

    @XmlElement(name = "BeneficiaryBankVerificationNumber")
    private String beneficiaryBankVerificationNumber;

    @XmlElement(name = "BeneficiaryKYCLevel")
    private String beneficiaryKYCLevel;

    @XmlElement(name = "TransactionLocation")
    private String transactionLocation;

    @XmlElement(name = "Narration")
    private String narration;

    @XmlElement(name = "PaymentReference")
    private String paymentReference;

    @XmlElement(name = "MandateReferenceNumber")
    private String mandateReferenceNumber;

    @XmlElement(name = "TransactionFee")
    private BigDecimal transactionFee;

    @XmlElement(name = "Amount")
    private BigDecimal amount;

    @XmlElement(name = "ResponseCode")
    private String responseCode;
}
