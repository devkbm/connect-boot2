create table if not exists COM.COMCODE (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    CODE_ID				VARCHAR(255) 	NOT NULL 	COMMENT '코드ID',    
	SYSTEM_TYPE_CODE	VARCHAR(3)		NOT NULL 	COMMENT '시스템구분코드',    
    P_CODE_ID			VARCHAR(242)	NULL 		COMMENT '상위코드ID',
   	CODE				VARCHAR(10) 	NOT NULL 	COMMENT '공통코드',
	CODE_NAME			VARCHAR(255) 	NOT NULL 	COMMENT '코드명칭',
	CODE_NAME_ABBR		VARCHAR(255) 	NULL 		COMMENT '코드명칭약어',	
	FROM_DT				DATETIME		NOT NULL	COMMENT '시작일시',
	TO_DT				DATETIME		NOT NULL	COMMENT '종료일시'	DEFAULT '9999-12-31',
	HIERARCHY_LEVEL		INT				NOT NULL	COMMENT '계층단계(1부터 시작)' DEFAULT 1,
	PRT_SEQ				INT				NULL		COMMENT '출력순서',	
	FIXED_LENGTH_YN		BOOLEAN			NOT NULL 	COMMENT '고정길이여부',
   	CODE_LENGTH			INT				NULL		COMMENT '코드길이' DEFAULT 0,
	CMT					VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_comcode primary key(CODE_ID)	
) COMMENT = '공통코드관리';

create table if not exists COM.COMDEPT (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    DEPT_CD				VARCHAR(10) 	NOT NULL 	COMMENT '부서코드',
    P_DEPT_CD			VARCHAR(255)	NULL 		COMMENT '상위부서코드',
    DEPT_NM_KOR			VARCHAR(255) 	NOT NULL 	COMMENT '부서명(한글)',
    DEPT_ABBR_KOR		VARCHAR(255) 	NULL 		COMMENT '부서약어(한글)',
    DEPT_NM_ENG			VARCHAR(255) 	NULL 		COMMENT '부서명(영어)',
    DEPT_ABBR_ENG		VARCHAR(255) 	NULL 		COMMENT '부서약어(영어)',
	FROM_DT				DATE			NULL		COMMENT '시작일',
	TO_DT				DATE			NULL		COMMENT '종료일',	
	PRT_SEQ				INT				NULL		COMMENT '출력순서',		
	CMT					VARCHAR(2000) 	NOT NULL 	COMMENT '비고',
	constraint pk_comdept primary key(DEPT_CD)	
) COMMENT = '통합부서관리';

create table if not exists COM.COMFILEINFO (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    PK_FILE				VARCHAR(40) 	NOT NULL 	COMMENT '키',
    PGM_ID				VARCHAR(10)		NOT NULL 	COMMENT '유저아이디',
    USER_ID				VARCHAR(20)		NULL		COMMENT '발령코드',
    CONTENT_TYPE		VARCHAR(50)		NULL 		COMMENT 'CONTENT-TYPE',
    UUID				VARCHAR(1000)	NOT NULL 	COMMENT 'UUID_서버에저장된파일명',
    FILE_PATH			VARCHAR(1000)	NULL 		COMMENT '파일경로',
    FILE_NM				VARCHAR(1000)	NULL 		COMMENT '파일명',
    FILE_SIZE			INT				NULL 		COMMENT '파일사이즈',
    DOWNLOAD_CNT		INT				NULL 		COMMENT '다운로드횟수',
	constraint pk_comfileinfo primary key(PK_FILE)
) COMMENT = '공통파일관리';

create table if not exists COM.COMUSER (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    USER_ID				VARCHAR(50)		NOT NULL	COMMENT '유저ID',
    USER_NAME			VARCHAR(100)	NULL		COMMENT '유저명',
    DEPT_CD				VARCHAR(10) 	NULL 		COMMENT '부서코드',
    MOBILE_NUM 			VARCHAR(20) 	NULL		COMMENT '핸드폰번호',
    EMAIL 				VARCHAR(320) 	NULL 		COMMENT '이메일주소',
    PWD 		   		VARCHAR(2000)	NULL		COMMENT '비밀번호',
    FK_FILE				VARCHAR(40)		NULL        COMMENT '이미지파일',
    NON_EXPIRED_YN		BOOLEAN			NOT NULL    COMMENT '계정만료여부',
    NON_LOCKED_YN		BOOLEAN			NOT NULL	COMMENT '계정잠금여부',
    PASS_NON_EXPIRED_YN	BOOLEAN			NOT NULL	COMMENT '비밀번호만료여부',
    ENABLED_YN			BOOLEAN			NOT NULL	COMMENT '사용여부',
	constraint pk_comuser primary key(USER_ID),
	constraint fk_comuser1	foreign key(FK_FILE) references COMFILEINFO(PK_FILE)
) COMMENT = '사용자관리';

create table if not exists COM.COMAUTHORITY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    AUTHORITY_NAME		VARCHAR(50)		NOT NULL	COMMENT '권한명',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '권한설명',
	constraint pk_comauthority primary key(AUTHORITY_NAME)
) COMMENT = '권한관리';

create table if not exists COM.COMUSERAUTHORITY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    USER_ID				VARCHAR(50)		NOT NULL	COMMENT '유저ID',
    AUTHORITY_NAME		VARCHAR(2000)	NOT NULL	COMMENT '권한명',
	constraint pk_comuserauthority 	primary key(USER_ID, AUTHORITY_NAME),
	constraint fk_comuserauthority1	foreign key(USER_ID) references COMUSER(USER_ID),
	constraint fk_comuserauthority2	foreign key(AUTHORITY_NAME) references COMAUTHORITY(AUTHORITY_NAME)
) COMMENT = '사용자권한매핑관리';

create table if not exists COM.COMRESOURCE (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',	
    RESOURCE_CODE		VARCHAR(10)		NOT NULL	COMMENT '리소스코드',    
    RESOURCE_NAME		VARCHAR(50)		NOT NULL	COMMENT '리소스명',
    RESOURCE_TYPE		VARCHAR(10)		NOT NULL	COMMENT '리소스타입',
    URL					VARCHAR(500)	NULL		COMMENT 'URL',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '설명',
	constraint pk_comresource	primary key(RESOURCE_CODE)
) COMMENT = '웹서버 리소스관리';

create table if not exists COM.COMMENUGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
    MENU_GROUP_NAME		VARCHAR(50)		NOT NULL	COMMENT '메뉴그룹명',
    DESCRIPTION			VARCHAR(500)	NULL		COMMENT '설명',
	constraint pk_commenugroup	primary key(MENU_GROUP_CODE)
) COMMENT = '메뉴그룹관리';

create table if not exists COM.COMMENU (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
	MENU_CODE			VARCHAR(10)		NOT NULL	COMMENT '메뉴코드',
	MENU_NAME			VARCHAR(50)		NOT NULL	COMMENT '메뉴명',
    MENU_TYPE			VARCHAR(10)		NOT NULL	COMMENT '메뉴타입',
	P_MENU_CODE			VARCHAR(10)		NULL		COMMENT '상위메뉴코드',
	MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',	        
    RESOURCE_CODE		VARCHAR(10)		NULL		COMMENT '프로그램코드',
    SEQ					INT(11)			NULL		COMMENT '계층별 순번',
    LVL					INT(11)			NULL		COMMENT '계층레벨',    
	constraint pk_commenu		primary key(MENU_CODE),
	constraint fk_commenu1	 	foreign key(P_MENU_CODE) references COMMENU(MENU_CODE),
	constraint fk_commenu2	 	foreign key(MENU_GROUP_CODE) references COMMENUGROUP(MENU_GROUP_CODE),
	constraint fk_commenu3 		foreign key(RESOURCE_CODE) references COMRESOURCE(RESOURCE_CODE)
) COMMENT = '메뉴관리';

create table if not exists COM.COMUSERMENUGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
	USER_ID				VARCHAR(50)		NOT NULL	COMMENT '유저ID',
    MENU_GROUP_CODE		VARCHAR(10)		NOT NULL	COMMENT '메뉴그룹코드',
	constraint pk_comusermenugroup 		primary key(USER_ID, MENU_GROUP_CODE),
	constraint fk_comusermenugroup1 	foreign key(USER_ID) references COMUSER(USER_ID),
	constraint fk_comusermenugroup2 	foreign key(MENU_GROUP_CODE) references COMMENUGROUP(MENU_GROUP_CODE)
) COMMENT = '사용자메뉴그룹매핑관리';

create table if not exists COM.COMLOGINHISTORY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
	ID					INT				NOT NULL	COMMENT 'ID' 		AUTO_INCREMENT,
	USER_ID				VARCHAR(50)		NOT NULL	COMMENT '유저ID',
	EVENT_TYPE			VARCHAR(500)	NOT NULL	COMMENT '로그인구분',
	EVENT_DT			DATETIME		NOT NULL	COMMENT '이벤트일시',	
	CLIENT_IP			VARCHAR(500)	NOT NULL	COMMENT '클라이언트IP',
	SUCCESS_YN			BOOLEAN			NOT NULL	COMMENT '성공여부',
	constraint pk_comloginhistory primary key(ID) 	
) COMMENT = '로그인이력';

create table if not exists COM.COMHOLIDAY (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
	HOLIDAY_DT			DATETIME		NOT NULL	COMMENT '휴일',
   	HOLIDAY_NM			VARCHAR(255) 	NOT NULL 	COMMENT '휴일명',    
	CMT					VARCHAR(2000) 	NULL 		COMMENT '비고',
	constraint pk_comholiday primary key(HOLIDAY_DT)	
) COMMENT = '공휴일관리';
