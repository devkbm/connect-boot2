create table if not exists COM.GRWSURVEYFORM (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    FORM_ID					INT			 	NOT NULL 	AUTO_INCREMENT COMMENT 'PK',
    TITLE					VARCHAR(2000) 	NOT NULL 	COMMENT '제목',
    CMT						VARCHAR(2000) 	NOT NULL 	COMMENT '비고',	
	constraint pk_grwsurveyform primary key(FORM_ID)	
) COMMENT = '설문서식';

create table if not exists COM.GRWSURVEYITEM (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    ITEM_ID					INT			 	NOT NULL 	AUTO_INCREMENT COMMENT 'PK',
    FORM_ID					INT			 	NOT NULL 	COMMENT '설문서식_ID',
    ITEM_TYPE				VARCHAR(10)		NOT NULL	COMMENT 'ITEMTYPE',    
    LABEL					VARCHAR(2000) 	NOT NULL 	COMMENT '라벨',
    VALUE					VARCHAR(2000) 	NOT NULL 	COMMENT '값',
    CMT						VARCHAR(2000) 	NOT NULL 	COMMENT '비고',
    REQUIRED_YN				BOOLEAN		 	NOT NULL 	COMMENT '필수여부',    
	constraint pk_grwsurveyitem primary key(ITEM_ID),
	constraint fk_grwsurveyitem1 foreign key(FORM_ID) references GRWSURVEYFORM(FORM_ID)
) COMMENT = '설문항목';