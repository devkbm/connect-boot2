
create table if not exists HRMEMPLOYEE (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	EMP_ID			VARCHAR(10) 	not null  	COMMENT '사원ID',
	EMP_NAME		VARCHAR(500)	null		COMMENT '성명(한글)',
	EMP_NAME_ENG	VARCHAR(500)	null		COMMENT '성명(영어)',
	EMP_NAME_CHI	VARCHAR(500)	null		COMMENT '성명(한문)',
	RREGNO			VARCHAR(20)		null		COMMENT '주민등록번호',
	GENDER			VARCHAR(1)		null		COMMENT '성별',
	BIRTHDAY		DATE			null		COMMENT '생일',
	WORK_CONDITION	VARCHAR(2)		null		COMMENT '근무상태',
	constraint pk_hrmemployee primary key(EMP_ID)
) COMMENT = '직원기본';

create table if not exists HRMEMPDEPTHISTORY (
	SYS_DT		DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 	VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT		DATETIME		null		COMMENT '최종수정일시',
	UPD_USER	VARCHAR(50)		null		COMMENT '최종수정유저',
	ID			INT				not null	COMMENT '부서이력ID'	AUTO_INCREMENT,
	EMP_ID		VARCHAR(10) 	not null  	COMMENT '사원ID',
	DEPT_TYPE	VARCHAR(10)		not null	COMMENT '부서타입',
	DEPT_CODE	VARCHAR(10)		not null	COMMENT '부서코드',
	FROM_DT		DATE			not null	COMMENT '시작일자',
	TO_DT		DATE			not null	COMMENT '종료일자',	
	constraint pk_hrmempdepthistory primary key(ID),
	constraint fk_hrmempdepthistory foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
) COMMENT = '직원부서이력';

create table if not exists HRMEMPJOBHISTORY (
	SYS_DT		DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 	VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT		DATETIME		null		COMMENT '최종수정일시',
	UPD_USER	VARCHAR(50)		null		COMMENT '최종수정유저',
	ID			INT				not null	COMMENT '부서이력ID'	AUTO_INCREMENT,
	EMP_ID		VARCHAR(10) 	not null  	COMMENT '사원ID',
	JOB_TYPE	VARCHAR(10)		not null	COMMENT '직제타입',
	JOB_CODE	VARCHAR(10)		not null	COMMENT '직제코드',
	FROM_DT		DATE			not null	COMMENT '시작일자',
	TO_DT		DATE			not null	COMMENT '종료일자',	
	constraint pk_hrmempjobhistory primary key(ID),
	constraint fk_hrmempjobhistory foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
) COMMENT = '직원인사이력';

create table if not exists HRMEMPSTATUSHISTORY (
	SYS_DT		DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 	VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT		DATETIME		null		COMMENT '최종수정일시',
	UPD_USER	VARCHAR(50)		null		COMMENT '최종수정유저',
	ID			INT				not null	COMMENT '근무상태이력ID'	AUTO_INCREMENT,
	EMP_ID		VARCHAR(10) 	not null  	COMMENT '사원ID',
	APPOINTMENT_CODE	VARCHAR(10)		not null	COMMENT '발령코드',
	STATUS_CODE	VARCHAR(10)		not null	COMMENT '상태코드',
	FROM_DT		DATE			not null	COMMENT '시작일자',
	TO_DT		DATE			not null	COMMENT '종료일자',	
	constraint pk_hrmempstatushistory primary key(ID),
	constraint fk_hrmempstatushistory foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
)  COMMENT = '직원상태이력';

create table if not exists COM.HRMAPPOINTMENTCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    APPOINTMENT_CODE		VARCHAR(10) 	NOT NULL 	COMMENT '발령코드',
	APPOINTMENT_CODE_NAME	VARCHAR(255) 	NOT NULL 	COMMENT '발령코드명칭',
	USE_YN					BOOLEAN			NOT NULL 	COMMENT '사용여부',		
	PRT_SEQ					INT				NULL		COMMENT '출력순서',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmappointmentcode primary key(APPOINTMENT_CODE)	
) COMMENT = '발령코드정보';


create table if not exists COM.HRMAPPOINMENTCODEDETAILS (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    PK_CODE_DETAIL		INT				NOT NULL	AUTO_INCREMENT COMMENT '발령상세키',    
    APPOINTMENT_CODE	VARCHAR(10) 	NOT NULL 	COMMENT '발령코드',
	CHANGE_TYPE			VARCHAR(20)		NULL		COMMENT '변경타입',
	CHANGE_TYPE_DETAIL	VARCHAR(20)		NULL		COMMENT '변경타입상세',
    PRT_SEQ				INT				NULL		COMMENT '출력순서',	    
	constraint pk_hrmappointmentcodedetails primary key(PK_CODE_DETAIL),
	constraint fk_hrmappointmentcodedetails foreign key(APPOINTMENT_CODE) references HRMAPPOINTMENTCODE(APPOINTMENT_CODE) 
);
