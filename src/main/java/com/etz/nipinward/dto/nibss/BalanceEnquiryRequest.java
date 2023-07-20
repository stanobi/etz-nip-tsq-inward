package com.etz.nipinward.dto.nibss;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "BalanceEnquiryRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class BalanceEnquiryRequest {

    @XmlElement(name = "SessionID")
    private String sessionID;

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "AuthorizationCode")
    private String authorizationCode;

    @XmlElement(name = "TargetAccountName")
    private String targetAccountName;

    @XmlElement(name = "TargetBankVerificationNumber")
    private String targetBankVerificationNumber;

    @XmlElement(name = "TargetAccountNumber")
    private String targetAccountNumber;

}
