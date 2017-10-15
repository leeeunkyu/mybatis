/* 부서테이블 dml.sql */
CREATE TABLE USERS(
	USER_ID VARCHAR(20) 
		CONSTRAINT USERS_USER_ID_PK PRIMARY KEY, 
	USER_PWD VARCHAR(30) 
		CONSTRAINT USERS_USER_PWD_NN NOT NULL,
	USER_NAME VARCHAR(30)
		CONSTRAINT USERS_USER_NAME_NN NOT NULL,
	GENDER CHAR(30)
		 CONSTRAINT USERS_USER_GENDER_NN NOT NULL,
	USER_SIGNUP VARCHAR(60)	
		CONSTRAINT USERS_USER_SIGNUP_NN NOT NULL
 );

comment on table USERS is ‘회원정보 테이블’;
comment on column users.user_id is ‘아이디’;
comment on column users.user_pwd is '비밀번호';
comment on column users.user_pwd is '이름';
comment on column users.user_pwd is '사용자 성별';
comment on column users.user_pwd is '회원가입 날짜';

CREATE TABLE BORDERS(
	USER_ID VARCHAR(20),
	BORD_INDEX NUMBER(3)
		CONSTRAINT BORDERS_BORD_NN PRIMARY KEY,
	BORD_HEAD VARCHAR2(20)
		CONSTRAINT BORDERS_BORD_HEAD_NN NOT NULL,
	BORD_BODY VARCHAR2(60),
	BORD_COUNT NUMBER(5)
		CONSTRAINT BORDERS_BORD_HITCNT_NN NOT NULL,
	BORD_DATE VARCHAR2(60)
		CONSTRAINT BORDERS_BORD_DATE_NN NOT NULL
);



comment on table BORDERS is ‘회원정보 테이블’;
comment on column borders.user_id is ‘아이디’;
comment on column borders.bord_index is '게시판인덱스';
comment on column borders.bord_head is '제목';
comment on column borders.bord_body is '게시판내용';
comment on column borders.bord_hitcnt is '조회수';
comment on column borders.bord_date is '작성날짜';

CREATE TABLE LOGS(
	USER_ID VARCHAR2(20),
	USER_HISTORY VARCHAR2(60),
	PERMISSION CHAR(1)
		CONSTRAINT LOG_USER_PERMISSION_NN NOT NULL
 );
ALTER TABLE LOGS
add constraint LOGS_USERID_fk 
foreign key(USER_id) references USERS(USER_ID);

comment on table LOGS is ‘회원로그 테이블’;
comment on column logs.user_id is ‘아이디’;
comment on column logs.user_history is '게시판인덱스';
comment on column logs.permission is '제목';
comment on column logs.cnt is '게시판내용';

CREATE TABLE COMMENTS(
	USER_ID VARCHAR2(20)
		CONSTRAINT COMMENT_USER_ID_NN NOT NULL,
	BORD_INDEX NUMBER(3)
		CONSTRAINT COMMENT_BORD_INDEX_NN NOT NULL,
	COMMENT_CONTENT VARCHAR2(60),
	COMMENT_HISTORY VARCHAR2(60)
		CONSTRAINT COMMENT_COMMENT_HISTORY_NN NOT NULL
 );
ALTER TABLE COMMENTS
add constraint COMMENT_BORDETS_fk 
foreign key(BORD_INDEX) references BORDERS(BORD_INDEX);

CREATE TABLE BLOCKUSERS(
	USER_ID VARCHAR2(20)
		CONSTRAINT BLOCKUSERS_USER_ID_NN NOT NULL,
	USER_STATUS VARCHAR2(10),
	BLOCKDAY VARCHAR(60)
);
ALTER TABLE BLOCKUSERS
add constraint BLOCKUSERS_USER_ID_fk 
foreign key(USER_ID) references USERS(USER_ID);

