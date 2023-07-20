package com.etz.nipinward.dto.nibss;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "FinancialInstitutionListRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@ToString
public class FinancialInstitutionListRequest {

    @XmlElement(name = "Header")
    private Header header;

    @XmlElement(name = "Record")
    private List<Record> record;

}



