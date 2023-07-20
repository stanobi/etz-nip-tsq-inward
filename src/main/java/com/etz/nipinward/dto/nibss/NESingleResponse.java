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
@XmlRootElement(name = "NESingleResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class NESingleResponse {

    @XmlElement(name = "SessionID")
    private String sessionID = "";

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode = "";

    @XmlElement(name = "ChannelCode")
    private String channelCode = "";

    @XmlElement(name = "AccountNumber")
    private String accountNumber = "";

    @XmlElement(name = "AccountName")
    private String accountName = "";

    @XmlElement(name = "BankVerificationNumber")
    private String bankVerificationNumber = "";

    @XmlElement(name = "KYCLevel")
    private String kYCLevel = "";

    @XmlElement(name = "ResponseCode")
    private String responseCode = "";
}
