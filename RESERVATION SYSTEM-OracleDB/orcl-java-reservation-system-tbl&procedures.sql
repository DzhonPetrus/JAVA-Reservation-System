--------------------------------------------------------
--  File created - Thursday-March-19-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table ACCOUNTS
--------------------------------------------------------

  CREATE TABLE "C##PETRUS"."ACCOUNTS" 
   (	"ID" NUMBER(10,0), 
	"USERNAME" VARCHAR2(20 CHAR), 
	"PASSWORD" VARCHAR2(20 CHAR), 
	"PERSON_ID" VARCHAR2(20 CHAR), 
	"FNAME" VARCHAR2(25 CHAR), 
	"LNAME" VARCHAR2(25 CHAR), 
	"ROLE" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table ITEMS
--------------------------------------------------------

  CREATE TABLE "C##PETRUS"."ITEMS" 
   (	"ID" NUMBER(10,0), 
	"NAME" VARCHAR2(50 CHAR), 
	"STOCK" NUMBER(10,0)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Table TRANSACTIONS
--------------------------------------------------------

  CREATE TABLE "C##PETRUS"."TRANSACTIONS" 
   (	"ID" NUMBER(10,0), 
	"USER_ID" NUMBER(10,0), 
	"BORROWER_ID" VARCHAR2(20 CHAR), 
	"BORROWER_FNAME" VARCHAR2(25 CHAR), 
	"BORROWER_LNAME" VARCHAR2(25 CHAR), 
	"ITEM_ID" NUMBER(10,0), 
	"STATUS" VARCHAR2(15 CHAR), 
	"DATE_RES" DATE, 
	"DATE_UPDATED" TIMESTAMP (6)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
REM INSERTING into C##PETRUS.ACCOUNTS
SET DEFINE OFF;
Insert into C##PETRUS.ACCOUNTS (ID,USERNAME,PASSWORD,PERSON_ID,FNAME,LNAME,ROLE) values (21,'FACULTY','FACULTY','FAC-123','F','ACC',2);
Insert into C##PETRUS.ACCOUNTS (ID,USERNAME,PASSWORD,PERSON_ID,FNAME,LNAME,ROLE) values (23,'z','z','z','zXC','zX',0);
Insert into C##PETRUS.ACCOUNTS (ID,USERNAME,PASSWORD,PERSON_ID,FNAME,LNAME,ROLE) values (2,'STAFF','STAFF','STAFF-1',null,null,1);
Insert into C##PETRUS.ACCOUNTS (ID,USERNAME,PASSWORD,PERSON_ID,FNAME,LNAME,ROLE) values (1,'ADMIN','PASSWORD','ADMIN1',null,null,0);
REM INSERTING into C##PETRUS.ITEMS
SET DEFINE OFF;
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (1,'PROJECTOR',0);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (2,'HDMI CABLE',4);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (3,'TABLET/IPAD',5);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (4,'DRAWING TABLET',5);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (5,'LAPTOP',7);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (6,'PROJECTOR REMOTE',5);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (7,'MOUSE & KEYBOARD',5);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (8,'SPEAKER',5);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (9,'SCIENTIFIC CALCULATOR',5);
Insert into C##PETRUS.ITEMS (ID,NAME,STOCK) values (10,'LAPEL MICROPHONE',5);
REM INSERTING into C##PETRUS.TRANSACTIONS
SET DEFINE OFF;
Insert into C##PETRUS.TRANSACTIONS (ID,USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED) values (5,0,'BRUH-3','JUAN','PEDRO',2,'RETURNED',null,to_timestamp('17-MAR-20 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into C##PETRUS.TRANSACTIONS (ID,USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED) values (3,21,'FAC-123','F','ACC',5,'RETURNED',to_date('19-MAR-20','DD-MON-RR'),to_timestamp('17-MAR-20 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into C##PETRUS.TRANSACTIONS (ID,USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED) values (1,21,'FAC-123',null,null,1,'CANCELLED',to_date('19-MAR-20','DD-MON-RR'),to_timestamp('17-MAR-20 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into C##PETRUS.TRANSACTIONS (ID,USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED) values (22,21,'FAC-123','F','ACC',2,'BORROWED',to_date('20-MAR-20','DD-MON-RR'),to_timestamp('18-MAR-20 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
Insert into C##PETRUS.TRANSACTIONS (ID,USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED) values (21,21,'FAC-123','F','ACC',5,'RETURNED',to_date('18-MAR-20','DD-MON-RR'),to_timestamp('18-MAR-20 12.00.00.000000000 AM','DD-MON-RR HH.MI.SSXFF AM'));
--------------------------------------------------------
--  DDL for Index SYS_C007437
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##PETRUS"."SYS_C007437" ON "C##PETRUS"."ACCOUNTS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007441
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##PETRUS"."SYS_C007441" ON "C##PETRUS"."ITEMS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C007444
--------------------------------------------------------

  CREATE UNIQUE INDEX "C##PETRUS"."SYS_C007444" ON "C##PETRUS"."TRANSACTIONS" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger ACCOUNTS_ID_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##PETRUS"."ACCOUNTS_ID_TRIG" BEFORE INSERT OR UPDATE ON accounts
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.ID IS NULL THEN
    SELECT  accounts_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(ID),0) INTO v_newVal FROM accounts;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT accounts_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
   -- assign the value from the sequence to emulate the identity column
   :new.ID := v_newVal;
  END IF;
END;
/
ALTER TRIGGER "C##PETRUS"."ACCOUNTS_ID_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger ITEMS_ID_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##PETRUS"."ITEMS_ID_TRIG" BEFORE INSERT OR UPDATE ON items
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.ID IS NULL THEN
    SELECT  items_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(ID),0) INTO v_newVal FROM items;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT items_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
   -- assign the value from the sequence to emulate the identity column
   :new.ID := v_newVal;
  END IF;
END;
/
ALTER TRIGGER "C##PETRUS"."ITEMS_ID_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Trigger TRANSACTIONS_ID_TRIG
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE TRIGGER "C##PETRUS"."TRANSACTIONS_ID_TRIG" BEFORE INSERT OR UPDATE ON transactions
FOR EACH ROW
DECLARE 
v_newVal NUMBER(12) := 0;
v_incval NUMBER(12) := 0;
BEGIN
  IF INSERTING AND :new.ID IS NULL THEN
    SELECT  transactions_ID_SEQ.NEXTVAL INTO v_newVal FROM DUAL;
    -- If this is the first time this table have been inserted into (sequence == 1)
    IF v_newVal = 1 THEN 
      --get the max indentity value from the table
      SELECT NVL(max(ID),0) INTO v_newVal FROM transactions;
      v_newVal := v_newVal + 1;
      --set the sequence to that value
      LOOP
           EXIT WHEN v_incval>=v_newVal;
           SELECT transactions_ID_SEQ.nextval INTO v_incval FROM dual;
      END LOOP;
    END IF;
   -- assign the value from the sequence to emulate the identity column
   :new.ID := v_newVal;
  END IF;
END;
/
ALTER TRIGGER "C##PETRUS"."TRANSACTIONS_ID_TRIG" ENABLE;
--------------------------------------------------------
--  DDL for Procedure DELETEACCOUNT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."DELETEACCOUNT" (
    aID IN ACCOUNTS.ID%TYPE)
IS
BEGIN
    DELETE FROM ACCOUNTS WHERE ID=aID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure DELETEITEM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."DELETEITEM" (
    iID ITEMS.ID%TYPE)
IS
BEGIN
    DELETE FROM ITEMS WHERE ID=iID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure DELETETRANSACTION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."DELETETRANSACTION" (
    tID TRANSACTIONS.ID%TYPE)
IS
BEGIN
    DELETE FROM TRANSACTIONS WHERE ID=tID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure INSERTACCOUNT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."INSERTACCOUNT" (
    uname ACCOUNTS.USERNAME%TYPE,
    pass ACCOUNTS.PASSWORD%TYPE,
    p_id ACCOUNTS.PERSON_ID%TYPE,
    fname ACCOUNTS.FNAME%TYPE,
    lname ACCOUNTS.LNAME%TYPE,
    role ACCOUNTS.ROLE%TYPE)
IS
BEGIN
    INSERT INTO ACCOUNTS (USERNAME, PASSWORD, PERSON_ID, FNAME, LNAME, ROLE) VALUES(uname, pass, p_id, fname, lname, role);
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure INSERTITEM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."INSERTITEM" (
    iName ITEMS.NAME%TYPE,
    iStock ITEMS.STOCK%TYPE)
IS
BEGIN
    INSERT INTO ITEMS(name,stock) VALUES(iName, iStock);
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure INSERTTRANSACTION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."INSERTTRANSACTION" (
    uID TRANSACTIONS.USER_ID%TYPE,
    bID TRANSACTIONS.BORROWER_ID%TYPE,
    bFNAME TRANSACTIONS.BORROWER_FNAME%TYPE,
    bLNAME TRANSACTIONS.BORROWER_LNAME%TYPE,
    iID TRANSACTIONS.ITEM_ID%TYPE,
    tSTATUS TRANSACTIONS.STATUS%TYPE,
    dateRes VARCHAR2)
IS
BEGIN
    INSERT INTO TRANSACTIONS(USER_ID,BORROWER_ID,BORROWER_FNAME,BORROWER_LNAME,ITEM_ID,STATUS,DATE_RES,DATE_UPDATED)
        VALUES(uID,bID,bFNAME,bLNAME,iID,tSTATUS,TO_DATE(dateRes,'dd/MM/yyyy'),TO_TIMESTAMP(SYSDATE));
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure SEARCHACCOUNT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SEARCHACCOUNT" (
    C OUT SYS_REFCURSOR,
    aID IN ACCOUNTS.ID%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM ACCOUNTS WHERE ID=aID;
END SEARCHACCOUNT;

/
--------------------------------------------------------
--  DDL for Procedure SEARCHACCOUNTBYPERSON
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SEARCHACCOUNTBYPERSON" (
    C OUT SYS_REFCURSOR,
    pID IN ACCOUNTS.PERSON_ID%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM ACCOUNTS WHERE PERSON_ID=pID;
END SEARCHACCOUNTBYPERSON;

/
--------------------------------------------------------
--  DDL for Procedure SEARCHITEM
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SEARCHITEM" (
    C OUT SYS_REFCURSOR,
    iNAME ITEMS.NAME%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM ITEMS WHERE NAME LIKE iNAME;
END SEARCHITEM;

/
--------------------------------------------------------
--  DDL for Procedure SEARCHITEMNAME
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SEARCHITEMNAME" (
    C OUT SYS_REFCURSOR,
    iID ITEMS.ID%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM ITEMS WHERE ID=iID;
END SEARCHITEMNAME;

/
--------------------------------------------------------
--  DDL for Procedure SEARCHTRANSACTION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SEARCHTRANSACTION" (
    C OUT SYS_REFCURSOR,
    tID TRANSACTIONS.ID%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM TRANSACTIONS WHERE ID=tID ORDER BY ID;
END SEARCHTRANSACTION;

/
--------------------------------------------------------
--  DDL for Procedure SEARCHTRANSACTIONCURRENTUSER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SEARCHTRANSACTIONCURRENTUSER" (
    C OUT SYS_REFCURSOR,
    uID TRANSACTIONS.ID%TYPE
)
AS
BEGIN
    OPEN C FOR
        Select * from TRANSACTIONS where USER_ID=uid and STATUS='RESERVED' or STATUS='BORROWED';
END SEARCHTRANSACTIONCURRENTUSER;

/
--------------------------------------------------------
--  DDL for Procedure SELECTACCOUNTS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SELECTACCOUNTS" (
    SELECTACCOUNTS_C OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN SELECTACCOUNTS_C FOR
        SELECT * FROM ACCOUNTS ORDER BY ID;
END SELECTACCOUNTS;

/
--------------------------------------------------------
--  DDL for Procedure SELECTITEMS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SELECTITEMS" (
    SELECTITEMS_C OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN SELECTITEMS_C FOR
        SELECT * FROM ITEMS ORDER BY ID;
END SELECTITEMS;

/
--------------------------------------------------------
--  DDL for Procedure SELECTITEMSAVAILABLE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SELECTITEMSAVAILABLE" (
    SELECTITEMS_C OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN SELECTITEMS_C FOR
        SELECT * FROM ITEMS WHERE STOCK > 0 ORDER BY ID;
END SELECTITEMSAVAILABLE;

/
--------------------------------------------------------
--  DDL for Procedure SELECTTRANSACTIONS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SELECTTRANSACTIONS" (
    C OUT SYS_REFCURSOR
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM TRANSACTIONS ORDER BY ID;
END SELECTTRANSACTIONS;

/
--------------------------------------------------------
--  DDL for Procedure SELECTTRANSACTIONSBYSTATUS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SELECTTRANSACTIONSBYSTATUS" (
    C OUT SYS_REFCURSOR,
    tSTATUS TRANSACTIONS.STATUS%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM TRANSACTIONS WHERE STATUS LIKE tSTATUS ORDER BY ID;
END SELECTTRANSACTIONSBYSTATUS;

/
--------------------------------------------------------
--  DDL for Procedure SELECTTRANSACTIONSOFUSER
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."SELECTTRANSACTIONSOFUSER" (
    C OUT SYS_REFCURSOR,
    uID TRANSACTIONS.USER_ID%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM TRANSACTIONS WHERE USER_ID=uID ORDER BY ID;
END SELECTTRANSACTIONSOFUSER;

/
--------------------------------------------------------
--  DDL for Procedure UPDATEACCOUNT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."UPDATEACCOUNT" (
    uname ACCOUNTS.USERNAME%TYPE,
    pass ACCOUNTS.PASSWORD%TYPE,
    pID ACCOUNTS.PERSON_ID%TYPE,
    afname ACCOUNTS.FNAME%TYPE,
    alname ACCOUNTS.LNAME%TYPE,
    arole ACCOUNTS.ROLE%TYPE,
    aID ACCOUNTS.ID%TYPE
)
IS
BEGIN
    UPDATE ACCOUNTS SET USERNAME=uname, PASSWORD=pass, PERSON_ID=pID, FNAME=afname, LNAME=alname, ROLE=arole WHERE ID=aID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure UPDATESTATUS
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."UPDATESTATUS" (
    tSTATUS TRANSACTIONS.STATUS%TYPE,
    uID TRANSACTIONS.ID%TYPE
)
IS
BEGIN
    UPDATE TRANSACTIONS SET STATUS=tSTATUS WHERE ID=uID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure UPDATESTOCK
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."UPDATESTOCK" (
    iSTOCK ITEMS.STOCK%TYPE,
    iID ITEMS.ID%TYPE
)
IS
BEGIN
    UPDATE ITEMS SET STOCK=iSTOCK WHERE ID=iID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure UPDATETRANSACTION
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."UPDATETRANSACTION" (
    uID TRANSACTIONS.USER_ID%TYPE,
    bID TRANSACTIONS.BORROWER_ID%TYPE,
    bFNAME TRANSACTIONS.BORROWER_FNAME%TYPE,
    bLNAME TRANSACTIONS.BORROWER_LNAME%TYPE,
    iID TRANSACTIONS.ITEM_ID%TYPE,
    tSTATUS TRANSACTIONS.STATUS%TYPE,
    dateRes TRANSACTIONS.DATE_RES%TYPE,
    tID TRANSACTIONS.ID%TYPE)
IS
BEGIN
    UPDATE TRANSACTIONS SET USER_ID=uID, BORROWER_ID=bID, BORROWER_FNAME=bFNAME, BORROWER_LNAME=bLNAME, ITEM_ID=iID, STATUS=tSTATUS, DATE_RES=dateRes, DATE_UPDATED=TO_DATE(SYSDATE, 'MM/DD/YYYY HH24:MI:SS')
    WHERE ID=tID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure UPDATETRANSACTION_RETURNED
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."UPDATETRANSACTION_RETURNED" (
    tID TRANSACTIONS.ID%TYPE)
IS
BEGIN
    UPDATE TRANSACTIONS SET STATUS='RETURNED' WHERE ID=tID;
    UPDATE TRANSACTIONS SET DATE_UPDATED=TO_DATE(SYSDATE, 'MM/DD/YYYY HH24:MI:SS') WHERE ID=tID;
    COMMIT;
END;

/
--------------------------------------------------------
--  DDL for Procedure VERIFYACCOUNT
--------------------------------------------------------
set define off;

  CREATE OR REPLACE EDITIONABLE PROCEDURE "C##PETRUS"."VERIFYACCOUNT" (
    C OUT SYS_REFCURSOR,
    uname IN ACCOUNTS.USERNAME%TYPE,
    pword IN ACCOUNTS.PASSWORD%TYPE
)
AS
BEGIN
    OPEN C FOR
        SELECT * FROM ACCOUNTS WHERE USERNAME=uname AND PASSWORD=pword;
END VERIFYACCOUNT;

/
--------------------------------------------------------
--  Constraints for Table ACCOUNTS
--------------------------------------------------------

  ALTER TABLE "C##PETRUS"."ACCOUNTS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ACCOUNTS" MODIFY ("USERNAME" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ACCOUNTS" MODIFY ("PASSWORD" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ACCOUNTS" MODIFY ("PERSON_ID" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ACCOUNTS" MODIFY ("ROLE" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ACCOUNTS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table ITEMS
--------------------------------------------------------

  ALTER TABLE "C##PETRUS"."ITEMS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ITEMS" MODIFY ("NAME" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ITEMS" MODIFY ("STOCK" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."ITEMS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
--------------------------------------------------------
--  Constraints for Table TRANSACTIONS
--------------------------------------------------------

  ALTER TABLE "C##PETRUS"."TRANSACTIONS" MODIFY ("ID" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."TRANSACTIONS" MODIFY ("BORROWER_ID" NOT NULL ENABLE);
  ALTER TABLE "C##PETRUS"."TRANSACTIONS" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
