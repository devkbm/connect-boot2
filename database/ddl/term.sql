create table if not exists COMTERM (
	SYS_DT			DATETIME		null		COMMENT '최초등록일시',
	SYS_USER 		VARCHAR(50)		null		COMMENT '최초등록유저',
	UPD_DT			DATETIME		null		COMMENT '최종수정일시',
	UPD_USER		VARCHAR(50)		null		COMMENT '최종수정유저',
	PK_TERM			INT				NOT NULL	AUTO_INCREMENT COMMENT '용어사전 키',
	DOMAIN_TYPE 	VARCHAR(50)		not null	COMMENT '업무종류',
	TERM			VARCHAR(2000)	null		COMMENT '용어',
	NAME_KOR		VARCHAR(2000)	null		COMMENT '한글명',
	ABBR_KOR		VARCHAR(2000)	null		COMMENT '한글약어',
	NAME_ENG		VARCHAR(2000)	null		COMMENT '영어명',
	ABBR_ENG		VARCHAR(2000)	null		COMMENT '영어약어',
	DESCRIPTION		VARCHAR(2000)	null		COMMENT '상세',
	CMT				VARCHAR(2000)	null		COMMENT '비고',
	constraint pk_comterm primary key(PK_TERM)
);

