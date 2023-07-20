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
@XmlRootElement(name = "FinancialInstitutionListResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FinancialInstitutionListResponse {

    @XmlElement(name = "BatchNumber")
    private String batchNumber;

    @XmlElement(name = "DestinationInstitutionCode")
    private String destinationInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "NumberOfRecords")
    private String numberOfRecords;

    @XmlElement(name = "ResponseCode")
    private String responseCode;

}
