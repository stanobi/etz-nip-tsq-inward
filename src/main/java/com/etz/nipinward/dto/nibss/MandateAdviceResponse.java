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
@XmlRootElement(name = "MandateAdviceResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class MandateAdviceResponse {

    @XmlElement(name = "SessionID")
    private String sessionID;

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "MandateReferenceNumber")
    private String mandateReferenceNumber;

    @XmlElement(name = "Amount")
    private BigDecimal amount;

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

    @XmlElement(name = "ResponseCode")
    private String responseCode;
}
