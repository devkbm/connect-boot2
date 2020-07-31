package com.like.hrm.code.boundary;

import java.io.Serializable;

import com.like.hrm.code.domain.model.HrmRelationCode;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data		
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SaveHrmRelationCode implements Serializable {										
	
	private static final long serialVersionUID = 8605426896397957467L;

	private Long relationId;
	
	private String relCode;
	
	private String relCodeName;
	
	private String parentTypeId;
	
	private String parentTypeName;
	
	private String parentDetailId;					

	private String parentDetailName;
	
	private String childTypeId;
	
	private String childTypeName;
	
	private String childDetailId;
	
	private String childDetailName;
					
	public HrmRelationCode newHrmRelationCode() {
		return new HrmRelationCode(this.relCode
				  				  ,this.parentTypeId
				  				  ,this.parentDetailId
				  				  ,this.childTypeId
				  				  ,this.childDetailId);
	}

	@QueryProjection
	public SaveHrmRelationCode(Long relationId
				   ,String relCode
				   ,String relCodeName
				   ,String parentTypeId
				   ,String parentTypeName
				   ,String parentDetailId
				   ,String parentDetailName
				   ,String childTypeId
				   ,String childTypeName
				   ,String childDetailId
				   ,String childDetailName) {
		this.relationId = relationId;
		this.relCode = relCode;
		this.relCodeName = relCodeName;
		this.parentTypeId = parentTypeId;
		this.parentTypeName = parentTypeName;
		this.parentDetailId = parentDetailId;
		this.parentDetailName = parentDetailName;
		this.childTypeId = childTypeId;
		this.childTypeName = childTypeName;
		this.childDetailId = childDetailId;
		this.childDetailName = childDetailName;		
	}
	
	
				
}