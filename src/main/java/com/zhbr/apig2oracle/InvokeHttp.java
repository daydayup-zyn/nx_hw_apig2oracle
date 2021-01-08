package com.zhbr.apig2oracle;

import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * 测试使用
 */
public class InvokeHttp {

    public static void main(String[] args) {

        Request request = new Request();
        try {
            String key = "xxxx";
            String secret = "xxxx";
            request.setKey(key);
            request.setSecret(secret);
            request.setMethod("GET");
            request.setUrl("https://742426f87ecd41edb30783e1b8cd455f.apigw.nx-region-2.sgic.sgcc.com.cn/getUserInfo/yjzh");
            request.addHeader("Content-Type", "application/json");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            //Sign the request.
            okhttp3.Request signedRequest = Client.signOkhttp(request);
            String authorization = signedRequest.header("Authorization");
            signedRequest = signedRequest.newBuilder().addHeader("x-Authorization",authorization).build();

            //Send the request.
            OkHttpClient client = getClientWithoutSSL();
            Response response = client.newCall(signedRequest).execute();


            //Print the status line of the response.
            System.out.println("status:" + response.code());

            //Print the header fields of the response.
            Headers resHeaders = response.headers();
            for (String h : resHeaders.names()) {
                System.out.println(h + ":" + resHeaders.get(h));
            }

            //Print the body of the response.
            ResponseBody resEntity = response.body();
            System.out.println("\n" + resEntity.string());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 内网绕过ssl验证
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
}
