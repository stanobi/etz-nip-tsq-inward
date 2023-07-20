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
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
@XmlRootElement(name = "CancelMandateResponse")
//@XmlType(propOrder = { "mandateCode","transType", "bankCode", "billerID", "billerName","billerTransId", "hashValue"})
public class CancelMandateResponse {
    @XmlElement(name = "MandateCode")
    private String mandateCode;
    @XmlElement(name = "TransType")
    private String transType;
    @XmlElement(name = "BankCode")
    private String bankCode;
    @XmlElement(name = "BillerID")
    private String billerID;
    @XmlElement(name = "BillerName")
    private String billerName;
    @XmlElement(name = "BillerTransId")
    private String billerTransId;
    @XmlElement(name = "HashValue")
    private String hashValue;
    @XmlElement(name = "ResponseCode")
    private String responseCode;
}