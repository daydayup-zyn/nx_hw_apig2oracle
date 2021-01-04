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

 Date: 29/12/2020 16:36:37
*/


-- ----------------------------
-- Table structure for HUAWEI_ZT_URL_TABLE_REL
-- ----------------------------
DROP TABLE "US_APP"."HUAWEI_ZT_URL_TABLE_REL";
CREATE TABLE "US_APP"."HUAWEI_ZT_URL_TABLE_REL" (
  "ID" NUMBER(11) ,
  "XTMC" VARCHAR2(255 BYTE) ,
  "URL" VARCHAR2(255 BYTE) ,
  "TABLENAME" VARCHAR2(255 BYTE) 
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

-- ----------------------------
-- Records of HUAWEI_ZT_URL_TABLE_REL
-- ----------------------------
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('1', 'DDLC', 'http://e8f3851c19ea46e9b35847eaa37a554f.apig.cn-north-4.huaweicloudapis.com/getApi/test', 'E_MP_DAY_ENERGY');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('2', 'DDLC', NULL, 'E_MP_CUR_CURVE_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('3', 'DDLC', NULL, 'E_MP_DAY_READ');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('4', 'DDLC', NULL, 'E_MP_FACTOR_CURVE_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('5', 'DDLC', NULL, 'E_MP_POWER_CURVE_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('6', 'DDLC', NULL, 'E_MP_VOL_CURVE_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('7', 'DDLC', NULL, 'HIGH_LINE_CV_TEMP_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('8', 'DDLC', NULL, 'HIGH_LINE_TEMP_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('9', 'DDLC', NULL, 'MED_LINE_CV_TEMP_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('10', 'DDLC', NULL, 'MED_LINE_TEMP_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('11', 'DDLC', NULL, 'TRAN_CV_TEMP_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('12', 'DDLC', NULL, 'TRAN_TEMP_NEW');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('13', 'DASJ', NULL, 'att_mid_cha_line_tran');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('14', 'DASJ', NULL, 'att_mid_cha_subs_line');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('15', 'DASJ', NULL, 'EVENT_ERC_14');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('16', 'DASJ', NULL, 'I_C_CONS');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('17', 'DASJ', NULL, 'I_C_IT_RUN');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('18', 'DASJ', NULL, 'I_C_METER');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('19', 'DASJ', NULL, 'I_C_METER_MP_RELA');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('20', 'DASJ', NULL, 'I_C_MP');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('21', 'DASJ', NULL, 'I_C_MP_IT_RELA');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('22', 'DASJ', NULL, 'I_C_PS');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('23', 'DASJ', NULL, 'I_C_SP');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('24', 'DASJ', NULL, 'I_D_METER');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('25', 'DASJ', NULL, 'I_G_LINE');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('26', 'DASJ', NULL, 'I_G_LINE_TG_RELA');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('27', 'DASJ', NULL, 'I_G_SUBS_LINE_RELA');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('28', 'DASJ', NULL, 'I_G_TG');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('29', 'DASJ', NULL, 'I_G_TRAN');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('30', 'DASJ', NULL, 'I_O_ORG');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('31', 'DASJ', NULL, 'I_P_CODE');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('32', 'DASJ', NULL, 'I_R_COLL_OBJ');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('33', 'DASJ', NULL, 'ISC_SPECIALORG_UNIT_LOCEXT');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('34', 'DASJ', NULL, 'ISC_USER_LOCEXT');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('35', 'DASJ', NULL, 'T_DW_BZZX_GGDMB');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('36', 'DASJ', NULL, 't_sb_znyc_dz');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('37', 'DASJ', NULL, 't_sb_znyc_pdbyq');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('38', 'DASJ', NULL, 't_sb_znyc_zbyq');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('39', 'DASJ', NULL, 't_sb_zwyc_daoxian');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('40', 'DASJ', NULL, 't_sb_zwyc_dld');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('41', 'DASJ', NULL, 't_sb_zwyc_xl');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('42', 'DASJ', NULL, 't_sb_zwyc_zsbyq');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('43', 'DASJ', NULL, 'T_TX_ZNYC_DZ');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('44', 'DASJ', NULL, 'T_YD_XL_BYQ');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('45', 'DASJ', NULL, 'T_YJ_DWYJ_TDSQD');
INSERT INTO "US_APP"."HUAWEI_ZT_URL_TABLE_REL" VALUES ('46', 'TEST', 'https://742426f87ecd41edb30783e1b8cd455f.apigw.nx-region-2.sgic.sgcc.com.cn/getUserInfo/yjzh', 'YJZH_TEST');