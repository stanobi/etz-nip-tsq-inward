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
@XmlRootElement(name = "TSQuerySingleResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class TSQuerySingleResponse {

    @XmlElement(name = "SourceInstitutionCode")
    private String sourceInstitutionCode;

    @XmlElement(name = "ChannelCode")
    private String channelCode;

    @XmlElement(name = "SessionID")
    private String sessionID;

    @XmlElement(name = "ResponseCode")
    private String responseCode;
}
