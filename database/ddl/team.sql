create table if not exists COM.TEAM (
	/*SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',*/
    TEAM_ID				VARCHAR(10)		NOT NULL	COMMENT '팀ID',
    TEAM_NAME			VARCHAR(50)		NULL		COMMENT '팀명',    
	constraint pk_team primary key(TEAM_ID)
);


create table if not exists COM.MEMBER (
	/*SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',*/
    MEMBER_ID			VARCHAR(10)		NOT NULL	COMMENT '팀원ID',
    MEMBER_NAME			VARCHAR(50)		NULL		COMMENT '팀원명',    
	constraint pk_member primary key(MEMBER_ID)
);

create table if not exists COM.JOINTEAM (
	/*SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',*/
    ID					INT				NOT NULL	auto_increment	COMMENT 'ID',
    TEAM_ID				VARCHAR(10)		NOT NULL	COMMENT '팀ID',
    MEMBER_ID			VARCHAR(10)		NOT NULL	COMMENT '팀원ID',    
	constraint pk_jointeam primary key(ID),
    constraint fk_team 		foreign key(TEAM_ID) references TEAM(TEAM_ID),
    constraint fk_member	foreign key(MEMBER_ID) references MEMBER(MEMBER_ID)
);


