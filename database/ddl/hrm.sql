create table if not exists COM.HRMTYPECODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    TYPE_ID					VARCHAR(20) 	NOT NULL 	COMMENT '인사유형ID_타입+코드',
    TYPE_CODE				VARCHAR(10) 	NOT NULL 	COMMENT '유형코드',
    CODE					VARCHAR(10) 	NOT NULL 	COMMENT '코드',
	CODE_NAME				VARCHAR(255) 	NOT NULL 	COMMENT '코드명칭',
	USE_YN					BOOLEAN			NOT NULL 	COMMENT '사용여부',		
	PRT_SEQ					INT				NULL		COMMENT '출력순서',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmtypecode primary key(TYPE_ID)	
) COMMENT = '인사유형코드정보';

create table if not exists COM.HRMTYPECODEDETAIL (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	DETAIL_ID				VARCHAR(20)		NOT NULL	COMMENT '인사유형상세ID_인사유형ID+코드',
    TYPE_ID					VARCHAR(20) 	NOT NULL 	COMMENT '인사유형ID_FK',       
    CODE					VARCHAR(10) 	NOT NULL 	COMMENT '코드',
	CODE_NAME				VARCHAR(255) 	NOT NULL 	COMMENT '코드명칭',
	USE_YN					BOOLEAN			NOT NULL 	COMMENT '사용여부',		
	PRT_SEQ					INT				NULL		COMMENT '출력순서',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmtypecodedetail primary key(DETAIL_ID),
	constraint fk_hrmtypecodedetail foreign key(TYPE_ID) references HRMTYPECODE(TYPE_ID)
) COMMENT = '인사유형상세정보';

create table if not exists COM.HRMRELATIONCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	RELATION_ID				INT				NOT NULL	COMMENT '인사연관코드ID' AUTO_INCREMENT,    
    REL_CODE				VARCHAR(20)		NOT NULL	COMMENT '연관코드',
    PARENT_TYPE_ID			VARCHAR(20)		NOT NULL	COMMENT '부모구분ID',
	PARENT_DETAIL_ID		VARCHAR(20) 	NOT NULL 	COMMENT '부모구분상세ID',
	CHILD_TYPE_ID			VARCHAR(20) 	NOT NULL 	COMMENT '자식구분ID',	
	CHILD_DETAIL_ID			VARCHAR(20) 	NOT NULL 	COMMENT '자식구분상세ID',
	constraint pk_HRMRELATIONCODE primary key(RELATION_ID)
) COMMENT = '인사연관코드정보';

create table if not exists COM.HRMAPPOINTMENTCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    APPOINTMENT_CODE		VARCHAR(10) 	NOT NULL 	COMMENT '발령코드',
	APPOINTMENT_CODE_NAME	VARCHAR(255) 	NOT NULL 	COMMENT '발령코드명칭',
	USE_YN					BOOLEAN			NOT NULL 	COMMENT '사용여부',
	EMP_STATUS_CODE			VARCHAR(3)		NULL		COMMENT '근무상태코드',
	END_DATE_YN				BOOLEAN			NOT NULL 	COMMENT '발령종료일여부',		
	PRT_SEQ					INT				NULL		COMMENT '출력순서',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmappointmentcode primary key(APPOINTMENT_CODE)	
) COMMENT = '발령코드정보';

create table if not exists COM.HRMAPPOINTMENTCODEDETAIL (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(20)		NULL		COMMENT '최종수정유저',
    TYPE_ID				VARCHAR(20) 	NOT NULL 	COMMENT '타입ID_변경타입+변경타입상세',    
	APPOINTMENT_CODE	VARCHAR(10) 	NOT NULL 	COMMENT '발령코드',
	CHANGE_TYPE			VARCHAR(20)		NULL		COMMENT '변경타입',
	CHANGE_TYPE_DETAIL	VARCHAR(20)		NULL		COMMENT '변경타입상세',	
    PRT_SEQ				INT				NULL		COMMENT '출력순서',	    
	constraint pk_hrmappointmentcodedetail primary key(TYPE_ID),
	constraint fk_hrmappointmentcodedetail foreign key(APPOINTMENT_CODE) references HRMAPPOINTMENTCODE(APPOINTMENT_CODE) 
) COMMENT = '발령코드상세정보';

create table if not exists HRMEMPLOYEE (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	EMP_ID			VARCHAR(10) 	not null  	COMMENT '사원ID',
	EMP_NAME		VARCHAR(500)	null		COMMENT '이름_한글',
	EMP_NAME_ENG	VARCHAR(500)	null		COMMENT '이름_영문',
	EMP_NAME_CHI	VARCHAR(500)	null		COMMENT '이름_한문',
	EMP_NAME_LEGAL	VARCHAR(500)	null		COMMENT '법적이름',
	RREGNO			VARCHAR(20)		null		COMMENT '주민등록번호',
	GENDER			VARCHAR(1)		null		COMMENT '성별',
	BIRTHDAY		DATE			null		COMMENT '생일',
	WORK_CONDITION	VARCHAR(2)		null		COMMENT '근무상태',
	IMG_PATH		VARCHAR(2000)  	null 		COMMENT '이미지경로',
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

create table if not exists HRMEMPFAMILY (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	ID				INT				not null	COMMENT '직원학력ID'	AUTO_INCREMENT,
	EMP_ID			VARCHAR(10) 	not null  	COMMENT '사원ID',
	FAMILY_NAME		VARCHAR(500)	not null	COMMENT '가족성명',
	RREGNO			VARCHAR(20)		not null	COMMENT '주민등록번호',
	FAMILY_REL_CODE	VARCHAR(3)		not null	COMMENT '가족관계코드',
	OCCUPATION_NAME	VARCHAR(500)	not null	COMMENT '직업명',
	SCHOOL_CAREER_TYPE	VARCHAR(3)	not null	COMMENT '학력구분',
	CMT				VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmempfamily primary key(ID),
	constraint fk_hrmempfamily1 foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
) COMMENT = '직원가족정보';

create table if not exists HRMEMPSCHOOLCAREER (
	SYS_DT				DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT				DATETIME		null		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		null		COMMENT '최종수정유저',
	ID					INT				not null	COMMENT '직원학력ID'	AUTO_INCREMENT,
	EMP_ID				VARCHAR(10) 	not null  	COMMENT '사원ID',
	SCHOOL_CAREER_TYPE	VARCHAR(2)		not null	COMMENT '학력유형',	
	SCHOOL_CODE			VARCHAR(5)		not null	COMMENT '학교코드',
	FROM_DT				DATE			null 		COMMENT '시작일자',
	TO_DT				DATE			null 		COMMENT '종료일자',
	MAJOR_NAME			VARCHAR(500)	null 		COMMENT '전공학과명',
	PLURAL_MAJOR_NAME	VARCHAR(500)	null 		COMMENT '복수전공학과명',
	LOCATION			VARCHAR(500)	null 		COMMENT '소재지',
	LESSON_YEAR			INT				null 		COMMENT '복수전공학과명',
	CMT					VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmempschoolcareer primary key(ID),
	constraint fk_hrmempschoolcareer foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
) COMMENT = '직원학력정보';

create table if not exists HRMEMPEDUCATION (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	ID				INT				not null	COMMENT '직원학력ID'	AUTO_INCREMENT,
	EMP_ID			VARCHAR(10) 	not null  	COMMENT '사원ID',
	EDU_TYPE		VARCHAR(2)		not null	COMMENT '학력유형',
	SCHOOL_CODE		VARCHAR(5)		not null	COMMENT '학교코드',	
	CMT				VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmempeducation primary key(ID),
	constraint fk_hrmempeducation foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
) COMMENT = '직원학력정보';

create table if not exists HRMEMPLICENSE (
	SYS_DT					DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT					DATETIME		null		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		null		COMMENT '최종수정유저',
	ID						INT				not null	COMMENT '직원자격면허ID'	AUTO_INCREMENT,
	EMP_ID					VARCHAR(10) 	not null  	COMMENT '사원ID',
	LICENSE_TYPE			VARCHAR(2)		not null	COMMENT '자격면허유형',
	LICENSE_CODE			VARCHAR(5)		not null	COMMENT '자격면허코드',
	DATE_OF_ACQUISITION		DATE			null		COMMENT '취득일자',
	CERTIFICATION_AUTHORITY	VARCHAR(500)	null		COMMENT '인증기관',
	MANDATORY_YN			BOOLEAN			not null	COMMENT '필수여부',
	CMT						VARCHAR(2000) 	null 		COMMENT '비고',
	constraint pk_hrmemplicense primary key(ID),
	constraint fk_hrmemplicense foreign key(EMP_ID) references HRMEMPLOYEE(EMP_ID)  
) COMMENT = '직원자격면허';


create table if not exists COM.HRMAPPOINTMENTLEDGER (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    LEDGER_ID				VARCHAR(20) 	NOT NULL 	COMMENT '발령대장_ID',
	APPOINTMENT_TYPE		VARCHAR(3) 		NOT NULL 	COMMENT '발령유형',
	RGST_DT					DATETIME		NOT NULL 	COMMENT '발령등록일',			
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmappointmentledger primary key(LEDGER_ID)	
) COMMENT = '발령대장';

create table if not exists COM.HRMAPPOINTMENTLEDGERLIST (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    LIST_ID					INT 			NOT NULL 	COMMENT '발령대장명단_ID' AUTO_INCREMENT,
	SEQ						INT				NOT NULL 	COMMENT '순번',    
    EMP_ID					VARCHAR(10) 	NOT NULL  	COMMENT '직원ID',
	APPOINTMENT_CODE		VARCHAR(10)		NOT NULL	COMMENT '발령코드',    
	FROM_DT					DATETIME		NOT NULL 	COMMENT '발령일',			
	TO_DT					DATETIME	 	NULL 		COMMENT '발령종료일',
	LEDGER_ID				VARCHAR(20) 	NULL 		COMMENT '발령대장ID',
	FINISH_YN				BOOLEAN			NULL 		COMMENT '완료여부',
	constraint pk_hrmappointmentledgerlist 	primary key(LIST_ID)	
) COMMENT = '발령대장명단';


create table if not exists COM.HRMAPPOINTMENTINFO (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    ID						INT				NOT NULL	COMMENT '발령정보_ID'	AUTO_INCREMENT,
	LIST_ID					INT 			NOT NULL 	COMMENT '발령대장명단_ID',
	CHANGE_TYPE				VARCHAR(20)		NULL		COMMENT '변경타입',
	CHANGE_TYPE_DETAIL		VARCHAR(20)		NULL		COMMENT '변경타입상세',	    	    	       
	CODE					VARCHAR(10)		NULL		COMMENT '변경코드',
	PRT_SEQ					INT				NULL		COMMENT '출력순서',
	constraint pk_hrmappointmentinfo 	primary key(ID),	
	constraint fk_hrmappointmentinfo1 	foreign key(LIST_ID) references HRMAPPOINTMENTLEDGERLIST(LIST_ID)  
) COMMENT = '발령정보';

create table HRMDUTYCODE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',	   
    DUTY_CODE				VARCHAR(20)		NOT NULL	COMMENT '근무코드',
	DUTY_NAME				VARCHAR(50) 	NOT NULL 	COMMENT '근무명',
	DUTY_GROUP				VARCHAR(20) 	NOT NULL 	COMMENT '근무그룹',
	ENABLE_YN				BOOLEAN 		NOT NULL 	COMMENT '사용여부',	
	FAMILY_EVENT_YN			BOOLEAN 		NOT NULL 	COMMENT '사용여부',
	FAMILY_EVENT_AMT		INT				null		COMMENT '경조사지급금액',
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmdutycode primary key(DUTY_CODE)
) COMMENT = '근무코드정보';

create table HRMDUTYAPPLICATION (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		NULL		COMMENT '최종수정유저',	   
    DUTY_ID					INT				NOT NULL	COMMENT '근태신청ID' AUTO_INCREMENT,
	EMP_ID					VARCHAR(50) 	NOT NULL 	COMMENT '사원번호',
	DUTY_CODE				VARCHAR(20)		NOT NULL	COMMENT '근무코드',
	DUTY_REASON				VARCHAR(2000) 	NOT NULL 	COMMENT '근태사유',
	DUTY_START_DT			DATETIME		NOT NULL 	COMMENT '근태시작일',
	DUTY_END_DT				DATETIME		NOT NULL 	COMMENT '근태종료일',
	FAMILY_EVENT_DT			DATETIME		NULL		COMMENT '경조사발생일자',
	FAMILY_EVENT_AMT		INT				NULL		COMMENT '경조사지급금액',
	constraint pk_hrmdutyapplication primary key(DUTY_ID),
	constraint fk_hrmdutyapplication1 foreign key(DUTY_CODE) references HRMDUTYCODE(DUTY_CODE)
) COMMENT = '근태신청정보';

create table HRMDUTYAPPLICATIONDATE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		NULL		COMMENT '최종수정유저',	   
    ID						INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,
    FK_DUTY_ID				INT				NOT NULL	COMMENT '근태신청ID',	
	DUTY_DT					DATETIME		NOT NULL 	COMMENT '근태일시',	
	constraint pk_hrmdutyapplicationdate primary key(ID),
	constraint fk_hrmdutyapplicationdate foreign key(FK_DUTY_ID) references HRMDUTYAPPLICATION(DUTY_ID)
) COMMENT = '근태신청일시정보';

create table HRMDUTYAPPLICATIONLIMIT (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(50)		NULL		COMMENT '최종수정유저',	   
	LIMIT_ID				INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,
    FROM_YEAR_TYPE			VARCHAR(10)		NOT NULL	COMMENT '시작년도구분',
    FROM_MMDD				VARCHAR(4)		NOT NULL	COMMENT '시작월일',
    TO_YEAR_TYPE			VARCHAR(10)		NOT NULL	COMMENT '종료년도구분',
    TO_MMDD					VARCHAR(4)		NOT NULL	COMMENT '종료월일',
    CNT						INT				NOT NULL	COMMENT '제한갯수',
    INVALID_MSG				VARCHAR(2000)	NULL		COMMENT '초과시메시지',
	CMT						VARCHAR(2000)	NULL		COMMENT '비고',	
	constraint pk_hrmdutyapplicationlimit primary key(LIMIT_ID)	
) COMMENT = '근태신청제한정보';

create table HRMDUTYCODERULE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',	   
    RULE_ID					INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,
    DUTY_CODE				VARCHAR(20)		NOT NULL	COMMENT '근무코드',	
	FK_LIMIT_ID				INT				NOT NULL	COMMENT 'FK_제한정보ID',
	constraint pk_hrmdutycoderule primary key(RULE_ID),
	constraint fk_hrmdutycoderule1 foreign key(DUTY_CODE) references HRMDUTYCODE(DUTY_CODE),
	constraint fk_hrmdutycoderule2 foreign key(FK_LIMIT_ID) references HRMDUTYAPPLICATIONLIMIT(LIMIT_ID)
) COMMENT = '근무코드정책정보';

create table if not exists COM.HRMANUALLEAVE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    YYYY					INT				NOT NULL	COMMENT '귀속년도',
    EMP_ID					VARCHAR(20)		NOT NULL	COMMENT '사원번호',
    BASE_DT					DATETIME		NOT NULL   	COMMENT '기준일',
    FROM_DT					DATETIME	 	NOT NULL 	COMMENT '연차시작일',
	TO_DT					DATETIME 		NOT NULL 	COMMENT '연차종료일',
	TOTAL_WORK_DAYS			INT				NULL 		COMMENT '총근무일수',
	EXCEPT_DAYS				INT				NULL 		COMMENT '총근무일수',
	CNT						DECIMAL(16,5)	NULL 		COMMENT '발생갯수',		
	ADD_CNT					DECIMAL(16,5)	NULL 		COMMENT '가산갯수',
	USE_CNT					DECIMAL(16,5)	NULL 		COMMENT '사용갯수',
	INTRA_ANUAL				BOOLEAN			NULL 		COMMENT '총근무일수',	
	CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_hrmanualleave primary key(YYYY,EMP_ID)	
) COMMENT = '직원연차정보';


create table if not exists COM.HRMPAYITEM (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
    CODE					VARCHAR(10)		NOT NULL	COMMENT '급여항목코드',	
    CODE_NM					VARCHAR(50)		NOT NULL	COMMENT '급여항목명',
    ITEM_TYPE				VARCHAR(10)		NOT NULL	COMMENT '구분코드',    
	PAY_TABLE_YN			BOOLEAN			NOT NULL	COMMENT '급여테이블사용여부',
	SEQ						INT				NULL		COMMENT '순번',
    CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',	
	constraint pk_hrmpayitem primary key(CODE)	
) COMMENT = '급여항목코드정보';

create table if not exists COM.HRMPAYTABLE (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	ID						INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,    
    TABLE_NM				VARCHAR(50)		NOT NULL	COMMENT '급여테이블명',
    ENABLE_YN				BOOLEAN			NOT NULL	COMMENT '사용여부',
    TYPE_CODE1				VARCHAR(10)		NULL 		COMMENT '급여구분코드1',    
    TYPE_CODE2				VARCHAR(10)		NULL 		COMMENT '급여구분코드2',
    TYPE_CODE3				VARCHAR(10)		NULL 		COMMENT '급여구분코드3',	
	SEQ						INT				NULL		COMMENT '순번',
    CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',	
	constraint pk_hrmpaytable primary key(ID)	
) COMMENT = '급여테이블정보';

create table if not exists COM.HRMPAYTABLEITEM (
	SYS_DT					DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 				VARCHAR(20)		NULL		COMMENT '최초등록유저',
	UPD_DT					DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER				VARCHAR(20)		NULL		COMMENT '최종수정유저',
	ID						INT				NOT NULL	COMMENT 'ID'	AUTO_INCREMENT,           
	PAY_TABBLE_ID			INT				NOT NULL	COMMENT 'FK_PAYTABLE',
    CODE1					VARCHAR(10)		NULL 		COMMENT '급여테이블항목코드1',    
    CODE2					VARCHAR(10)		NULL 		COMMENT '급여테이블항목코드2',
    CODE3					VARCHAR(10)		NULL 		COMMENT '급여테이블항목코드3',	
	AMT						DECIMAL			null		COMMENT '금액',
    CMT						VARCHAR(2000) 	NULL 		COMMENT '비고',	
	constraint pk_hrmpaytableitem primary key(ID),
	constraint fk_hrmpaytableitem1 foreign key(PAY_TABBLE_ID) references HRMPAYTABLE(ID)
) COMMENT = '급여테이블항목정보';