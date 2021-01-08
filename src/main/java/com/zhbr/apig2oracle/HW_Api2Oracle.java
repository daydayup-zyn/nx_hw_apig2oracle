package com.zhbr.apig2oracle;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.log4j.Logger;

import javax.net.ssl.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName HW_Api2Oracle
 * @Description TODO
 * @Autor yanni
 * @Date 2020/12/16 14:46
 * @Version 1.0
 **/
public class HW_Api2Oracle {
    private static Logger logger = Logger.getLogger(HW_Api2Oracle.class);
    private static QueryRunner queryRunner = null;

    public static void main(String[] args) {

        //测试数据
        //final String dataStr = "{\"requestId\":\"75baff498dbe2d9d8ce86d56bc8605fb\",\"errCode\":null,\"errMsg\":null,\"data\":{\"success\":true,\"data\":\"[{\\\"id\\\":\\\"8a5989c86e22469d016fa6e7d1d23dc2\\\",\\\"train_name\\\":\\\"新闻突发事件应急预案\\\",\\\"train_type\\\":\\\"应急管理\\\",\\\"train_team\\\":null,\\\"start_time\\\":\\\"2019-05-23 00:00:00\\\",\\\"end_time\\\":\\\"2019-05-31 00:00:00\\\",\\\"train_address\\\":\\\"银川供电公司\\\",\\\"student_demand\\\":null,\\\"annex\\\":null,\\\"remark\\\":null,\\\"create_user_id\\\":null,\\\"create_user_name\\\":null,\\\"create_time\\\":null,\\\"create_dep_id\\\":\\\"ff8080814a4310c7014a46be0e7100bb\\\",\\\"create_dep_name\\\":null,\\\"del_flag\\\":\\\"0\\\",\\\"link_man\\\":\\\"杜凯伦\\\",\\\"link_way\\\":\\\"13895190287\\\",\\\"goal\\\":\\\"提高管理人员应急管理水平。\\\",\\\"training_aid\\\":null,\\\"train_obj\\\":\\\"新闻发言人，网评员，各单位主管新闻舆论负责人，专责\\\",\\\"data_common_id\\\":\\\"8a5989c86e2246a3016fa6e7d1d240b3\\\",\\\"system_id\\\":\\\"ff8080814a4310c7014a46be0e7100bb\\\",\\\"send_dept_id\\\":null,\\\"is_report\\\":\\\"4\\\",\\\"state\\\":\\\"1\\\",\\\"downstate\\\":null,\\\"train_user_num\\\":null,\\\"is_supporting_materials\\\":\\\"0\\\",\\\"extend_field_source\\\":null,\\\"extend_field_op_type\\\":null,\\\"extend_field_op_ts\\\":null,\\\"extend_field_current_ts\\\":null,\\\"extend_field_op_position\\\":null,\\\"extend_field_pos\\\":null,\\\"extend_field_delete_flag\\\":0},{\\\"id\\\":\\\"8a5989c86e22469d016fa816ce4f3e5a\\\",\\\"train_name\\\":\\\"高空救援中级技能培训\\\",\\\"train_type\\\":\\\"应急技能\\\",\\\"train_team\\\":null,\\\"start_time\\\":\\\"2020-07-01 00:00:00\\\",\\\"end_time\\\":\\\"2020-07-31 00:00:00\\\",\\\"train_address\\\":\\\"石嘴山培训基地\\\",\\\"student_demand\\\":\\\"开展高空施救技能培训，对绳索、防坠设备、救援方法开展培训，并实操\\\",\\\"annex\\\":\\\"3.2020年应急工作计划-表1-应急培训计划-石嘴山公司.doc;\\\",\\\"remark\\\":null,\\\"create_user_id\\\":null,\\\"create_user_name\\\":null,\\\"create_time\\\":null,\\\"create_dep_id\\\":\\\"ff8080814a4310c7014a46bf233200be\\\",\\\"create_dep_name\\\":null,\\\"del_flag\\\":\\\"0\\\",\\\"link_man\\\":\\\"杨慧\\\",\\\"link_way\\\":\\\"18795325888\\\",\\\"goal\\\":\\\"开展高空施救技能培训，对绳索、防坠设备、救援方法开展培训，并实操\\\",\\\"training_aid\\\":null,\\\"train_obj\\\":\\\"应急救援基干队员\\\",\\\"data_common_id\\\":\\\"8a5989c86e2246a3016fa816ce4f474a\\\",\\\"system_id\\\":\\\"ff8080814a4310c7014a46bf233200be\\\",\\\"send_dept_id\\\":null,\\\"is_report\\\":\\\"4\\\",\\\"state\\\":\\\"1\\\",\\\"downstate\\\":null,\\\"train_user_num\\\":null,\\\"is_supporting_materials\\\":\\\"0\\\",\\\"extend_field_source\\\":null,\\\"extend_field_op_type\\\":null,\\\"extend_field_op_ts\\\":null,\\\"extend_field_current_ts\\\":null,\\\"extend_field_op_position\\\":null,\\\"extend_field_pos\\\":null,\\\"extend_field_delete_flag\\\":0}]\",\"msg\":null}}";

        //定时任务(每天执行一次)
        Timer ddlc = new Timer();
        ddlc.schedule(new TimerTask() {
            @Override
            public void run() {
                HashMap<String,String> mapList = getUrlAndTableRelationship("DDLC");
                for(String key : mapList.keySet()){
                    String dataStr = getHWDataByAppKey("xxxx", "xxxx", key);

                    data2Oracle2(dataStr,mapList.get(key));
                }
                //测试（成功）
                //data2Oracle2(dataStr,"YJZH_TEST");
            }
        },1000,1000*60*60*24);

        //定时任务(每月执行一次)
        Timer da = new Timer();
        da.schedule(new TimerTask() {
            @Override
            public void run() {
                HashMap<String,String> mapList = getUrlAndTableRelationship("DASJ");
                for(String key : mapList.keySet()){
                    String dataStr = getHWDataByAppKey("xxxx", "xxxx", key);

                    data2Oracle2(dataStr,mapList.get(key));
                }
            }
        },1000,1000*60*60*24*30L);
    }

    /**
     * 获取url与表之间的关系（把api取到的数据插入对应的表中）
     * @param xtmc
     * @return
     */
    public static HashMap<String,String> getUrlAndTableRelationship(String xtmc){
        List<Map<String, Object>> mapList = null;
        HashMap<String,String> hashMap = new HashMap<String,String>();
        queryRunner = JDBCUtil.getQueryRunner();
        try {
            mapList = queryRunner.query("select * from huawei_zt_url_table_rel where xtmc='"+xtmc+"'", new MapListHandler());
            for( Map<String,Object> map : mapList ){
                String newKey = (String)map.get("url");
                String newValue = (String)map.get("tablename");
                hashMap.put(newKey,newValue);
            }
            return hashMap;
        } catch (SQLException e) {
            //e.printStackTrace();
            logger.error(e.getMessage());
        }
        return hashMap;
    }

    /**
     * 获取华为中台数据
     * @param appKey
     * @param appSecret
     * @param url
     * @return
     */
    public static String getHWDataByAppKey(String appKey,String appSecret,String url){

        Request request = new Request();
        try {
            //Set the request parameters.
            //AppKey, AppSecrect, Method and Url are required parameters.
            request.setKey(appKey);
            request.setSecret(appSecret);
            request.setMethod("GET");
            request.setUrl(url);
            request.addHeader("Content-Type", "application/json");
            //if it was published in other envs(except for Release),you need to add the information x-stage and the value is env's name
            //request.addHeader("x-stage", "publish_env_name");
            request.setBody("demo");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        OkHttpClient client = null;
        String result = null;
        try {
            //Sign the request.
            okhttp3.Request signedRequest = Client.signOkhttp(request);
            String authorization = signedRequest.header("Authorization");
            signedRequest = signedRequest.newBuilder().addHeader("x-Authorization",authorization).build();

            //Send the request.
            client = getClientWithoutSSL();
            Response response = client.newCall(signedRequest).execute();

            //Print the status line of the response.
            int statusCode = response.code();
            logger.info("statusCode: "+statusCode);

            result = response.body().string();
            if (statusCode == 4 || statusCode == 5) {
                //throw new IOException(result);
                logger.error("未知错误: "+statusCode);
            }
            if (result == null || result.length() == 0) {
                logger.error("未知错误: 结果为空");
                return null;
            }

            return result;
        } catch (Exception e) {
            logger.error("获取response失败: "+e.getMessage());
        }

        return result;
    }


    /**
     * 内网绕过ssl验证
     * @return
     */
    private static OkHttpClient getClientWithoutSSL(){
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {}
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {}
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        OkHttpClient clientWithoutSSL = new OkHttpClient.Builder().sslSocketFactory(sslContext.getSocketFactory(), xtm).hostnameVerifier(DO_NOT_VERIFY).build();
        return clientWithoutSSL;
    }

    /**
     * 将API取到的数据保存到Oracle数据库（弃用）
     * @param data
     * @return
     */
    public static boolean data2Oracle(String data,String tableName){
        JSONArray jsonArray = JSONArray.parseArray(data);

        for (int i = 0;i < jsonArray.size();i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String columns = getTableSchema(tableName);

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("insert into ").append(tableName).append(" (").append(columns).append(") values(");

            StringBuffer cols = new StringBuffer();
            String[] split = columns.split(",");
            for (int y = 0;y < split.length;y++){
                String column = split[y];
                Object col_value = jsonObject.get(column.toLowerCase());
                String type = getTypeByColumn(column);

                if (type.startsWith("VARCHAR2")){
                    cols.append("'").append(col_value).append("'").append(",");
                }else if (type.startsWith("NUMBER")){
                    cols.append(col_value).append(",");
                }else if (type.startsWith("DATE") && col_value.toString().length()>15){
                    cols.append("to_date('").append(col_value).append("','yyyy-mm-dd hh24:mi:ss')").append(",");
                }else if (type.startsWith("DATE") && col_value.toString().length()<15){
                    cols.append("to_date('").append(col_value).append("','yyyy-mm-dd')").append(",");
                }
            }

            String substring = cols.toString().substring(0, cols.toString().length() - 1);
            stringBuffer.append(substring).append(")");

            queryRunner = JDBCUtil.getQueryRunner();
            try {
                queryRunner.update(stringBuffer.toString());
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    /**
     * 将API取到的数据保存到Oracle数据库
     * @param data
     * @param tableName
     * @return
     */
    public static boolean data2Oracle2(String data,String tableName){
        JSONObject parseObject = JSONObject.parseObject(data);
        JSONObject data1 = parseObject.getJSONObject("data");
        String data2 = data1.getString("data");
        JSONArray jsonArray = JSONArray.parseArray(data2);

        for (int i = 0;i < jsonArray.size();i++){

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String columns = getTableSchema(tableName.toLowerCase());

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("insert into ").append(tableName).append(" (").append(columns).append(") values(");

            StringBuffer cols = new StringBuffer();
            String[] split = columns.split(",");
            for (int y = 0;y < split.length;y++){
                String column = split[y];

                stringBuffer.append("?,");
            }

            String sqlStr = stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1)+")";
            //stringBuffer.append(substring).append(")");
            //System.out.println(sqlStr);
            queryRunner = JDBCUtil.getQueryRunner();

            ArrayList<Object> arrayList = new ArrayList<Object>();
            for (int y = 0;y < split.length;y++){
                String column = split[y];
                Object col_value = jsonObject.get(column.toLowerCase());
                String type = getTypeByColumn(column);

                if (type.equals("VARCHAR2")){
                    arrayList.add(col_value);
                }else if (type.equals("NUMBER")){
                    arrayList.add(col_value);
                }else if (type.equals("DATE") && col_value.toString().length()>15){
                    //arrayList.add("to_date('"+col_value+"','yyyy-mm-dd hh24:mi:ss')");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
                    java.util.Date utilDate = null;
                    try {
                        utilDate = sdf.parse(col_value.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date = new Date(utilDate.getTime());
                    arrayList.add(date);
                }else if (type.equals("DATE") && col_value.toString().length()<15){
                    //arrayList.add("to_date('"+col_value+"','yyyy-mm-dd hh24:mi:ss')");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = null;
                    try {
                        utilDate = sdf.parse(col_value.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Date date = new Date(utilDate.getTime());
                    arrayList.add(date);
                }else if(type.equals("BLOB")){
                    try {
                        SerialBlob serialBlob = new SerialBlob(convert(col_value));
                        arrayList.add(serialBlob);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (type.equals("CLOB")){
                    try {
                        SerialClob serialClob = new SerialClob(col_value.toString().toCharArray());
                        arrayList.add(serialClob);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            try {
                int update = queryRunner.update(sqlStr, arrayList.toArray());
                if (update==1){
                    logger.info("表数据"+tableName.toUpperCase()+"-->保存Oracle: successful");
                }else{
                    logger.info("表数据"+tableName.toUpperCase()+"-->保存Oracle: failed");
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return true;
    }

    /**
     * 获取表的字段
     * @param tableName
     */
    public static String getTableSchema(String tableName){
        List<Map<String, Object>> mapList = null;
        queryRunner = JDBCUtil.getQueryRunner();
        StringBuffer stringBuffer = new StringBuffer();
        String str = null;
        try {
            mapList = queryRunner.query("select column_name,column_type from huawei_zt_table_schema where tablename = '" + tableName + "'", new MapListHandler());
            for( Map<String,Object> map : mapList ){
                String column_name = (String)map.get("column_name");
                stringBuffer.append(column_name).append(",");
            }
            str = stringBuffer.toString();
            String substring = str.substring(0, str.length() - 1);
            return substring;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return str;
    }

    /**
     * 根据字段名称查找字段类型
     * @param columnName
     * @return
     */
    public static String getTypeByColumn(String columnName){
        List<String> str = null;
        String column = null;
        queryRunner = JDBCUtil.getQueryRunner();
        try {
            str = queryRunner.query("select column_type from huawei_zt_table_schema where column_name = '" + columnName + "'", new ColumnListHandler<String>("column_type"));
            for (String column_type:str){
                column = column_type;
            }
            return column;
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return column;
    }

    /**
     * object类型转byte[]
     * @param obj
     * @return
     * @throws IOException
     */
    public static byte[] convert(Object obj) throws IOException {
        ObjectOutputStream os = null;

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
        os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
        os.flush();
        os.writeObject(obj);
        os.flush();
        byte[] sendBuf = byteStream.toByteArray();
        os.close();
        return sendBuf;
    }
}
