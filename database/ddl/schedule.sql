create table if not exists COM.GRWWORKGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    ID					INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '업무그룹ID',
    NAME				VARCHAR(2000)	NULL		COMMENT '업무그룹명',    
	constraint pk_grwworkgroup primary key(ID)
) COMMENT = '업무그룹';

create table if not exists COM.GRWSCHEDULE (
	SYS_DT				DATETIME		NULL		COMMENT '최초등록일시',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '최초등록유저',
	UPD_DT				DATETIME		NULL		COMMENT '최종수정일시',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '최종수정유저',
    ID					INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '업무일정ID',
    FK_WORKGROUP		INT UNSIGNED	NOT NULL	COMMENT '업무그룹FK',
    TITLE				VARCHAR(2000)	NULL		COMMENT '제목',
    START_DT			DATETIME 		NULL		COMMENT '시작일시',
    END_DT				DATETIME 		NULL		COMMENT '종료일시',
    ALLDAY				BOOLEAN			NULL		DEFAULT FALSE COMMENT '종일여부',
	constraint pk_grwschedule primary key(ID),
	constraint fk_grwschedule foreign key(FK_WORKGROUP) references GRWWORKGROUP(ID)
) COMMENT = '업무일정';
  


