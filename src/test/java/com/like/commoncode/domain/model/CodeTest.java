package com.like.commoncode.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.like.commoncode.domain.model.Code;
import com.like.commoncode.service.CommonCodeCommandService;
import com.like.commoncode.service.CommonCodeQueryService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CodeTest {	
	
	@Autowired
	CommonCodeCommandService commonCodeCommandService;
		
	@Autowired
	CommonCodeQueryService commonCodeQueryService;
		
	@Test
	public void test002_코드등록및조회() {
		//Given
		Code code = Code.builder()								
				.code("HRM")
				.codeName("테스트코드")
				.codeNameAbbreviation("약어")
				.fromDate(LocalDate.of(2019, 1, 01).atStartOfDay())
				.toDate(LocalDate.of(9999, 12, 31).atStartOfDay())				
				.fixedLengthYn(false)
				.cmt("테스트비고")
				.build();
		
		//When		
		commonCodeCommandService.saveCode(code);
		
		Code test = commonCodeQueryService.getCode(code.id);
		
		//Then
		assertThat(test.id).isEqualTo("HRM");
		assertThat(test.codeName).isEqualTo("테스트코드");
		assertThat(test.codeNameAbbreviation).isEqualTo("약어");
		assertThat(test.fromDate).isEqualTo(LocalDate.of(2019, 1, 01).atStartOfDay());
		assertThat(test.toDate).isEqualTo(LocalDate.of(9999, 12, 31).atStartOfDay());
		assertThat(test.cmt).isEqualTo("테스트비고");

		System.out.println(test.toString());
	}
	
	
	
	
}
