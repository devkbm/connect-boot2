package com.like.core.domain.jpaconverter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

/**
 * JPA 2.1 저장시 LocalDate를 저장하지 못하여 변환을 거침
 * @author 김병민
 *
 */
@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {
	
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
    	return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalDate());
    }

	
}

