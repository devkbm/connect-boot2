create table if not exists COM.GRWTEAM (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    TEAM_ID				INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '팀ID',
    TEAM_NAME			VARCHAR(50)		NULL		COMMENT '팀명',    
	constraint pk_grwteam primary key(TEAM_ID)
) COMMENT = '팀관리';


create table if not exists COM.GRWMEMBER (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    MEMBER_ID			INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '팀원ID',
    MEMBER_NAME			VARCHAR(50)		NULL		COMMENT '팀원명',    
	constraint pk_grwmember primary key(MEMBER_ID)
) COMMENT = '팀원관리';

create table if not exists COM.GRWTEAMMEMBER (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    ID					INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT 'ID',
    TEAM_ID				VARCHAR(10)		NOT NULL	COMMENT '팀ID',
    MEMBER_ID			VARCHAR(10)		NOT NULL	COMMENT '팀원ID',
    JOIN_DT				DATETIME		NOT NULL	COMMENT '가입일',
    WITHDRAW_DT			DATETIME		NOT NULL	COMMENT '탈퇴일',
	constraint pk_grwteammember 	primary key(ID),	
	constraint uk_grwteammember		unique index(TEAM_ID, MEMBER_ID, JOIN_DT),
    constraint fk_grwteammember01	foreign key(TEAM_ID) references TEAM(TEAM_ID),
    constraint fk_grwteammember02	foreign key(MEMBER_ID) references MEMBER(MEMBER_ID)
) COMMENT = '팀원 매핑 정보';
  
 
 
 