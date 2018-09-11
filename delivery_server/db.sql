-- ID, PW, NAME, ADDRESS, AUTH --> �ֹ�ȸ��
-- ID, PW, NAME, LOCATIONS, DELIVERCOUNTS, SCORE, AUTH --> ���ȸ��
-- ID, PW, NAME, AUTH --> ������
-- AUTH : 0 : ������,  1 : �ֹ�ȸ��, 2 : ���ȸ��

CREATE TABLE members(
    ID VARCHAR2(20) primary key,
    PW VARCHAR2(20) not null,
    NAME varchar2(20) not null,
    ADDRESS varchar2(150),
    LOCATIONS varchar2(200),
    DELIVERCOUNTS number(5),
    SCORE number(5,1),
    AUTH number(2) not null    
);

INSERT INTO members (ID, PW, NAME, AUTH)
VALUES ('admin','admin','administrator',0);

CREATE TABLE blacklist(
	USERID VARCHAR2(20) not null,
	BLACKID VARCHAR2(20) not null,
	CONSTRAINT FK_USER FOREIGN KEY(USERID) REFERENCES members(ID),
	CONSTRAINT FK_BLACK FOREIGN KEY(BLACKID) REFERENCES members(ID)
);

DROP TABLE REQUESTS
CASCADE CONSTRAINTS

CREATE TABLE requests(
	REQNUMBER number(5) primary key,
	REQTYPE varchar2(20) not null,
	WRITER varchar2(20) not null,
	DELIVERER varchar2(20),
	PRICE number(8) not null,
	LOCATION varchar2(50) not null, -- OO��
	CONTENTS varchar2(1000) not null,
	APPLICANTS varchar2(400), -- ������ ��� (ID)
	STATE varchar2(20), -- ������� (��û,������,�Ϸ�)
	SCORE number(2),
	REVIEW varchar2(1000), 
	CONSTRAINT FK_WRITER FOREIGN KEY(WRITER) REFERENCES members(ID),
	CONSTRAINT FK_DELIVERER FOREIGN KEY(DELIVERER) REFERENCES members(ID)
);



CREATE TABLE LOADNAME_ADD(
	LOAD VARCHAR2(30), 
	SIDO VARCHAR2(30), 
	SIGUNGU VARCHAR2(30), 
	EUBMEONDONG VARCHAR2(30)
);