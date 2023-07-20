package com.etz.nipinward.dto.nibss;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@XmlRootElement(name="HeaderPart")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "batchNumber", "numberOfRecords", "channelCode", "transactionLocation" })
public class Header {

  @XmlElement(name = "BatchNumber")
  private String batchNumber;

  @XmlElement(name = "NumberOfRecords")
  private String numberOfRecords;

  @XmlElement(name = "ChannelCode")
  private String channelCode;

  @XmlElement(name = "TransactionLocation")
  private String transactionLocation;

}


