/*
 Navicat Premium Data Transfer

 Source Server         : 102_oracle
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 192.168.56.102:1521
 Source Schema         : US_APP

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 29/12/2020 17:09:22
*/


-- ----------------------------
-- Table structure for YJZH_TEST
-- ----------------------------
DROP TABLE "US_APP"."YJZH_TEST";
CREATE TABLE "US_APP"."YJZH_TEST" (
  "ID" VARCHAR2(100 BYTE) ,
  "TRAIN_NAME" VARCHAR2(1000 BYTE) ,
  "TRAIN_TYPE" VARCHAR2(100 BYTE) ,
  "START_TIME" DATE 
)
TABLESPACE "TS_APP"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
