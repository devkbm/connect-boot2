package com.like.core.domain.jpaconverter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class BooleanYnConverter  implements AttributeConverter <Boolean, String> {

	@Override
	public String convertToDatabaseColumn(Boolean arg0) {
		return (arg0 == true) ? "Y" : "N";
	}

	@Override
	public Boolean convertToEntityAttribute(String arg0) {
		return (arg0 == "Y") ? true : false;
	}

}
