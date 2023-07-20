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
@XmlRootElement(name = "AccountBlockResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class AccountBlockResponse {

    @XmlElement(name = "SessionID")
    private String sessionID;

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "ReferenceCode")
    private String referenceCode;

    @XmlElement(name = "TargetAccountName")
    private String targetAccountName;

    @XmlElement(name = "TargetBankVerificationNumber")
    private String targetBankVerificationNumber;

    @XmlElement(name = "TargetAccountNumber")
    private String targetAccountNumber;

    @XmlElement(name = "ReasonCode")
    private String reasonCode;

    @XmlElement(name = "Narration")
    private String narration;

    @XmlElement(name = "Amount")
    private BigDecimal amount;

    @XmlElement(name = "ResponseCode")
    private String responseCode;
}
