create table if not exists COM.GRWTEAM (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    TEAM_ID				INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '팀ID',
    TEAM_NAME			VARCHAR(50)		NULL		COMMENT '팀명',    
	constraint pk_grwteam primary key(TEAM_ID)
) COMMENT = '팀관리';


create table if not exists COM.GRWTEAMUSER (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저', 
    TEAM_ID				INT UNSIGNED	NOT NULL	COMMENT '팀ID',
    USER_ID				VARCHAR(20)		NOT NULL	COMMENT '유저ID',
	constraint pk_grwteamuser 	primary key(TEAM_ID, USER_ID),		
    constraint fk_grwteamuser01	foreign key(TEAM_ID) references GRWTEAM(TEAM_ID),
    constraint fk_grwteamuser02	foreign key(USER_ID) references COMUSER(USER_ID)
) COMMENT = '팀원 매핑 정보';