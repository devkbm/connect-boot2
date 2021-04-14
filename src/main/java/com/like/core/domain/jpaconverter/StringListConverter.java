package com.like.core.domain.jpaconverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StringListConverter implements AttributeConverter<List<String>, String> {

	private static final String SEPARATOR = ",";
	 
	@Override
	public String convertToDatabaseColumn(List<String> attribute) {
		return String.join(SEPARATOR, attribute); 
	}

	@Override
	public List<String> convertToEntityAttribute(String dbData) {
		return new ArrayList<>(Arrays.asList(dbData.split(SEPARATOR)));
	}
	
}
