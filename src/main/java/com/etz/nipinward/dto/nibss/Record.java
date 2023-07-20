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
@XmlRootElement(name="RecordPart")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "institutionCode", "institutionName", "category" })
public class Record {

    @XmlElement(name = "InstitutionCode")
    private String institutionCode;

    @XmlElement(name = "InstitutionName")
    private String institutionName;

    @XmlElement(name = "Category")
    private String category;

}
