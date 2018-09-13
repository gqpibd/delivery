<<<<<<< HEAD
-- ID, PW, NAME, ADDRESS, AUTH --> 구매자
-- ID, PW, NAME, LOCATIONS, DELIVERCOUNTS, SCORE, AUTH --> 배달자
-- ID, PW, NAME, AUTH --> 관리자
-- AUTH : 0 : 관리자,  1 : 구매자, 2 : 관리자
=======
-- ID, PW, NAME, ADDRESS, AUTH --> �ֹ�ȸ��
-- ID, PW, NAME, LOCATIONS, DELIVERCOUNTS, SCORE, AUTH --> ���ȸ��
-- ID, PW, NAME, AUTH --> ������
-- AUTH : 0 : ������,  1 : �ֹ�ȸ��, 2 : ���ȸ��
>>>>>>> refs/remotes/origin/h2gon

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

INSERT INTO members (ID, PW, NAME, ADDRESS, AUTH)
VALUES ('h2__gon','gongon','영곤','서울',2);

INSERT INTO members (ID, PW, NAME, ADDRESS, AUTH)
VALUES ('dh','dohyeon','도현','성남',1);

INSERT INTO members (ID, PW, NAME, ADDRESS, AUTH)
VALUES ('dh_deliver','dohyeon','도현','성남',2);

CREATE TABLE blacklist(
	USERID VARCHAR2(20) not null,
	BLACKID VARCHAR2(20) not null,
	CONSTRAINT FK_USER FOREIGN KEY(USERID) REFERENCES members(ID),
	CONSTRAINT FK_BLACK FOREIGN KEY(BLACKID) REFERENCES members(ID)
);

DROP TABLE REQUESTS
CASCADE CONSTRAINTS

CREATE TABLE ORDERS(
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

alter table requests rename orders

INSERT INTO requests
VALUES (1,'배달','h2gon','dh_deliver',15000,'서울','도미노 피자 사다 주세요', null, '진행중',null,null);

select * from requests;

CREATE TABLE LOADNAME_ADD(
	LOAD VARCHAR2(30), 
	SIDO VARCHAR2(30), 
	SIGUNGU VARCHAR2(30), 
	EUBMEONDONG VARCHAR2(30)
);