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
@XmlRootElement(name = "CreateMandateRequest")
//@XmlType(propOrder = { "acctNumber","acctName", "transType", "bankCode", "billerID", "billerName","billerTransId","secondFactor", "hashValue"})
public class CreateMandateRequest {

    @XmlElement(name = "AcctNumber")
    private String acctNumber;
    @XmlElement(name = "AcctName")
    private String acctName;
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
    @XmlElement(name = "SecondFactor",required=false)
    private String secondFactor;
    @XmlElement(name = "HashValue")
    private String hashValue;

}
