package com.like.hrm.appointment.boundary;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import com.like.hrm.appointment.domain.model.AppointmentCode;
import com.like.hrm.appointment.domain.model.AppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.QAppointmentCode;
import com.like.hrm.appointment.domain.model.QAppointmentCodeDetail;
import com.like.hrm.appointment.domain.model.enums.ChangeType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AppointmentCodeDTO {

	public static SaveCodeDetail convertDTO(AppointmentCodeDetail entity) {
		return new SaveCodeDetail(entity.getId()
				                 ,entity.getAppointmentCode().getCode()
				                 ,entity.getChangeType().toString()
				                 ,entity.getChangeTypeDetail()
				                 ,entity.getSequence());
	}
	
	/**
	 * 발령코드 조회조건 
	 */
	@Data
	public static class SearchCode implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QAppointmentCode qType = QAppointmentCode.appointmentCode;
		
		private String code;
		
		private String codeName;
					
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(likeCode(this.code))
				.and(likeCodeName(this.codeName));
						
			
			return builder;
		}
		
		private BooleanExpression likeCode(String code) {
			if (StringUtils.isEmpty(code)) {
				return null;
			}
			
			return qType.code.like("%"+code+"%");
		}
		
		private BooleanExpression likeCodeName(String codeName) {
			if (StringUtils.isEmpty(codeName)) {
				return null;
			}
			
			return qType.codeName.like("%"+codeName+"%");
		}
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class SaveCode implements Serializable {
				
		private static final long serialVersionUID = -4482323353197356218L;
					
		private String code;
			
		private String codeName;					
								
		private int sequence;
			
		private boolean useYn;
		
		private boolean endDateYn;
		
		private String comment;
		
		public AppointmentCode newAppointmentCode() {
			return new AppointmentCode(this.code
									  ,this.codeName
									  ,this.useYn
									  ,this.endDateYn
									  ,this.sequence
									  ,this.comment									  
									  ,null);
		}
		
		public void modifyEntity(AppointmentCode entity) {
			entity.changeInfo(this.codeName
							 ,this.useYn
							 ,this.endDateYn
							 ,this.sequence
							 ,this.comment);					
		}
		
		public static SaveCode convertDTO(AppointmentCode entity) {
			return SaveCode.builder()
						   .code(entity.getCode())
						   .codeName(entity.getCodeName())
						   .sequence(entity.getSequence())
						   .useYn(entity.isUseYn())
						   .endDateYn(entity.isEndDateYn())
						   .comment(entity.getComment())
						   .build();
		}
	}
	
	@Data
	public static class SearchCodeDetail implements Serializable {
		
		private static final long serialVersionUID = 1L;

		private final QAppointmentCodeDetail qType = QAppointmentCodeDetail.appointmentCodeDetail;
		
		@NotEmpty(message = "발령코드는 필수 값입니다.")
		private String appointmentCode;
		
		private String changeType;
		
		public SearchCodeDetail(String appointmentCode) {
			this.appointmentCode = appointmentCode;
		}
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			builder
				.and(equalAppointmentCode(this.appointmentCode))
				.and(equalChangeType(this.changeType));
									
			return builder;
		}
		
		private BooleanExpression equalAppointmentCode(String appointmentCode) {						
			return qType.appointmentCode.code.eq(appointmentCode);
		}
		
		private BooleanExpression equalChangeType(String changeType) {
			if (StringUtils.isEmpty(changeType)) {
				return null;
			}
			
			return qType.changeType.eq(ChangeType.valueOf(changeType));
		}
	}
	
	@Data
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	@AllArgsConstructor
	public static class SaveCodeDetail implements Serializable {
					
		private static final long serialVersionUID = 309168416341042059L;

		private String detailId;
		
		private String code;
							
		private String changeType;					
		
		private String changeTypeDetail;
								
		private int sequence;	
		
		public AppointmentCodeDetail newAppointmentCodeDetail(AppointmentCode code) {
						
			AppointmentCodeDetail detail = new AppointmentCodeDetail(code
																	,ChangeType.valueOf(this.changeType)
																	,this.changeTypeDetail
																	,this.sequence);
			return detail;
		}
		
		/*public String getDetailId() {
			return this.code + this.changeType + this.changeTypeDetail;
		}*/
	}
}
