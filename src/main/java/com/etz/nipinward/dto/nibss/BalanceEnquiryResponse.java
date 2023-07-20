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
@XmlRootElement(name = "BalanceEnquiryResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class BalanceEnquiryResponse {

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

    @XmlElement(name = "AvailableBalance")
    private BigDecimal availableBalance = BigDecimal.ZERO;

    @XmlElement(name = "ResponseCode")
    private String responseCode;

}
