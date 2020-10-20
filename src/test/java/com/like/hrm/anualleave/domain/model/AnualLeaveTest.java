package com.like.hrm.anualleave.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AnualLeaveTest {
	
	@Test	
	public void TEST01_월만근하지않으면_휴가발생하지않음() {
		// Given
		AnualLeave entity = new AnualLeave(2020
										  ,"test"
										  ,LocalDate.of(2020, 1, 1)
										  ,LocalDate.of(2020, 1, 1)
										  ,LocalDate.of(2020, 12, 31));
		
		// When
		entity.calc(LocalDate.of(2020, 1, 20));
		
		
		// Then					
		assertThat(entity.cnt).isEqualTo(0);
	}
	
	@Test
	public void TEST02_일년미만_월만근시마다_한개발생() {
		// Given
		AnualLeave entity = new AnualLeave(2020
										  ,"test"
										  ,LocalDate.of(2020, 1, 1)
										  ,LocalDate.of(2020, 1, 1)
										  ,LocalDate.of(2020, 12, 31));
		
		// When
		entity.calc(LocalDate.of(2020, 3, 1));
		
		// Then					
		assertThat(entity.cnt).isEqualTo(2);
	}

	@Test
	public void TEST03_일년초과_근무시_15개발생() {
		// Given
		AnualLeave entity = new AnualLeave(2020
										  ,"test"
										  ,LocalDate.of(2020, 1, 1)
										  ,LocalDate.of(2021, 1, 1)
										  ,LocalDate.of(2021, 12, 31));
		
		// When
		entity.calc(LocalDate.of(2021, 1, 1));
		
		// Then					
		assertThat(entity.cnt).isEqualTo(15);
	}
	
	@Test
	public void TEST04_3년초과시_2년마다_누진갯수발생() {
		// Given
		AnualLeave entity = new AnualLeave(2020
										  ,"test"
										  ,LocalDate.of(2020, 1, 1)
										  ,LocalDate.of(2023, 1, 1)
										  ,LocalDate.of(2023, 12, 31));
		
		// When
		entity.calc(LocalDate.of(2023, 1, 1));
		
		// Then					
		assertThat(entity.cnt).isEqualTo(15);
		assertThat(entity.add_cnt).isEqualTo(1);
	}
}
