create table if not exists COM.GRWWORKGROUP (
	SYS_DT				DATETIME		NULL		COMMENT '���ʵ���Ͻ�',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '���ʵ������',
	UPD_DT				DATETIME		NULL		COMMENT '���������Ͻ�',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '������������',
    ID					INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '�����׷�ID',
    NAME				VARCHAR(2000)	NULL		COMMENT '�����׷��',    
	constraint pk_grwworkgroup primary key(ID)
) COMMENT = '�����׷�';

create table if not exists COM.GRWSCHEDULE (
	SYS_DT				DATETIME		NULL		COMMENT '���ʵ���Ͻ�',
	SYS_USER 			VARCHAR(50)		NULL		COMMENT '���ʵ������',
	UPD_DT				DATETIME		NULL		COMMENT '���������Ͻ�',
	UPD_USER			VARCHAR(50)		NULL		COMMENT '������������',
    ID					INT UNSIGNED	NOT NULL	AUTO_INCREMENT	COMMENT '��������ID',
    FK_WORKGROUP		INT UNSIGNED	NOT NULL	COMMENT '�����׷�FK',
    TITLE				VARCHAR(2000)	NULL		COMMENT '����',
    START_DT			DATETIME 		NULL		COMMENT '�����Ͻ�',
    END_DT				DATETIME 		NULL		COMMENT '�����Ͻ�',
    ALLDAY				BOOLEAN			NULL		DEFAULT FALSE COMMENT '���Ͽ���',
	constraint pk_grwschedule primary key(ID),
	constraint fk_grwschedule foreign key(FK_WORKGROUP) references GRWWORKGROUP(ID)
) COMMENT = '��������';
  


