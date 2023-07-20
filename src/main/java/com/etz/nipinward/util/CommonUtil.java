package com.etz.nipinward.util;


import com.etz.nipinward.exception.ExpectationFailedException;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Slf4j
public class CommonUtil {

	public static <T> String convertXMLObjectToXMLString(T xmlObject, Class<T> clazz) throws ExpectationFailedException {
		StringWriter writer = new StringWriter();
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
			jaxbMarshaller.marshal(xmlObject, writer);
		    return writer.toString();
		} catch(JAXBException ex) {
			log.error("Unable to convert xml object to xml string: ", ex);
			throw new ExpectationFailedException("Unable to convert xml object to xml string");
		}
	}
	
	

	public static <T> T convertXMLStringToXMLObject(String xmlString, Class<T> clazz) throws ExpectationFailedException {
		StringReader reader = new StringReader(xmlString);
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			return clazz.cast(jaxbUnmarshaller.unmarshal(reader));
		} catch(JAXBException ex) {
			log.error("Unable to convert xml String to xml Object: ", ex);
			throw new ExpectationFailedException("Unable to convert xml String to xml Object:");
		}
	}

	public static String getDateAsString(Date date) {
		return new SimpleDateFormat("dd MMMM yyyy").format(date);
	}

	public static String generateSessionID(String bankCode) {
		Random random = new Random();
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
		return bankCode + simpleDateFormat.format(date) + getRandomNumber(100000000L, 999999999L, random) + getRandomNumber(100L, 999L, random);
	}

	public static String getRandomNumber(long aStart, long aEnd, Random aRandom) {
		long range = aEnd - aStart + 1L;
		long fraction = (long)(range * aRandom.nextDouble());
		Long randomNumber = Long.valueOf(fraction + aStart);
		return randomNumber.toString();
	}
}
