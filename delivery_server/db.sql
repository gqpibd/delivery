CREATE TABLE member(
    ID VARCHAR2(10) primary key,
    NAME varchar2(20) not null,
    ADDRESS varchar2(100) NOT NULL,
    PHONE varchar2(20) not null,
    AUTH number(1) not null,
    BLACKLIST ,
); 