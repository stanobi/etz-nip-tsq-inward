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
@XmlRootElement(name = "FTSingleCreditRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FTSingleCreditRequest {

    @XmlElement(name = "SessionID")
    private String sessionID;

    @XmlElement(name = "NameEnquiryRef")
    private String nameEnquiryRef;

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "BeneficiaryAccountName")
    private String beneficiaryAccountName;

    @XmlElement(name = "BeneficiaryAccountNumber")
    private String beneficiaryAccountNumber;

    @XmlElement(name = "BeneficiaryBankVerificationNumber")
    private String beneficiaryBankVerificationNumber;

    @XmlElement(name = "BeneficiaryKYCLevel")
    private String beneficiaryKYCLevel;

    @XmlElement(name = "OriginatorAccountName")
    private String originatorAccountName;

    @XmlElement(name = "OriginatorAccountNumber")
    private String originatorAccountNumber;

    @XmlElement(name = "OriginatorBankVerificationNumber")
    private String originatorBankVerificationNumber;

    @XmlElement(name = "OriginatorKYCLevel")
    private String originatorKYCLevel;

    @XmlElement(name = "TransactionLocation")
    private String transactionLocation;

    @XmlElement(name = "Narration")
    private String narration;

    @XmlElement(name = "PaymentReference")
    private String paymentReference;

    @XmlElement(name = "Amount")
    private BigDecimal amount;

}
