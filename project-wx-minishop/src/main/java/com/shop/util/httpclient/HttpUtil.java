package com.shop.util.httpclient;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Slf4j
public class HttpUtil {

    /**
     * 默认编码方式 -UTF8
     */
    public static final String DEFAULT_ENCODE = "utf-8";

    /**
     * https协议进行post请求,请求头带信息
     *
     * @param url
     * @param params
     * @param encode
     * @return
     */
    public static String postUrlAsStringHttps(String url, String params, Map<String, String> reqHeader, String encode) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            StringEntity entity = new StringEntity(params,encode);
            httpPost.setEntity(entity);
            if (reqHeader != null) {// 设置请求头信息
                for (String name : reqHeader.keySet()) {
                    httpPost.addHeader(name, reqHeader.get(name));
                }
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,encode);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * GET请求, 结果以字符串形式返回.
     *
     * @param url 请求地址
     * @return 内容字符串
     */
    public static String getUrlAsString(String url) throws Exception {
        return getUrlAsString(url, null, DEFAULT_ENCODE);
    }

    /**
     * GET请求, 结果以字符串形式返回.
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return 内容字符串
     */
    public static String getUrlAsString(String url, Map<String, Object> params) throws Exception {
        return getUrlAsString(url, params, DEFAULT_ENCODE);
    }

    /**
     * GET请求, 结果以字符串形式返回.
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param encode 编码方式
     * @return 内容字符串
     */
    public static String getUrlAsString(String url, Map<String, Object> params, String encode) throws Exception {
        return getUrlAsString(url, params, null, encode);
    }

    /**
     * GET请求, 结果以字符串形式返回.
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param encode 编码方式
     * @return 内容字符串
     */
    public static String getUrlAsString(String url, Map<String, Object> params, Map<String, String> header,
                                        String encode) throws Exception {
        // 开始时间
        long t1 = System.currentTimeMillis();
        // 获得HttpGet对象
        HttpGet httpGet = getHttpGet(url, params, encode);
        // 发送请求
        String result = executeHttpRequest(httpGet, header, encode);
        // 结束时间
        long t2 = System.currentTimeMillis();
        // 调试信息
        log.debug("consume time:" + ((t2 - t1)));
        // 返回结果
        return result;
    }

    /**
     * <b>概要：</b> 执行HTTP请求 <b>作者：</b>SUXH </br> <b>日期：</b>2015-7-25 </br>
     * 请求对象
     *
     * @param reqHeader 请求头 map
     * @param encode    编码
     * @return 响应字符串
     * @throws Exception
     */
    private static String executeHttpRequest(HttpUriRequest httpUriRequest, Map<String, String> reqHeader, String encode) throws Exception {
        HttpClient client = null;
        String result = null;
        try {
            // 创建HttpClient对象
            client = new DefaultHttpClient();

            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 366000);// 设置连接超时时间
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 366000);// 设置Socket超时时间
            if (reqHeader != null) {// 设置请求头信息
                for (String name : reqHeader.keySet()) {
                    httpUriRequest.addHeader(name, reqHeader.get(name));
                }
            }
            SSLConnectionSocketFactory scsf = new SSLConnectionSocketFactory(SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(), NoopHostnameVerifier.INSTANCE);
            client = HttpClients.custom().setSSLSocketFactory(scsf).build();
            HttpResponse response = client.execute(httpUriRequest);// 获得返回结果
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 如果成功
                result = EntityUtils.toString(response.getEntity(), encode);
            } else {// 如果失败
                StringBuffer errorMsg = new StringBuffer();
                errorMsg.append("httpStatus:");
                errorMsg.append(response.getStatusLine().getStatusCode());
                errorMsg.append(response.getStatusLine().getReasonPhrase());
                errorMsg.append(", Header: ");
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    errorMsg.append(header.getName());
                    errorMsg.append(":");
                    errorMsg.append(header.getValue());
                }
                log.error("HttpResonse Error:" + errorMsg);
            }
        } catch (Exception e) {
            log.error("http连接异常", e);
            throw new Exception("http连接异常");
        } finally {
            try {
                client.getConnectionManager().shutdown();
            } catch (Exception e) {
                log.error("finally HttpClient shutdown error", e);
            }
        }
        log.info(result);
        return result;
    }

    /**
     * https协议进行post请求
     *
     * @param url
     * @param params
     * @param encode
     * @return
     */
    public static String postUrlAsString(String url, String params, String encode) {
        log.info("HttpUtil-postUrlAsString-url-" + url + "-input-" + JSON.toJSONString(params));
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            StringEntity entity = new StringEntity(params, encode);
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, encode);
                    log.info("HttpUtil-postUrlAsString-output-" + result);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * 提交json body数据
     *
     * @param url
     * @param params
     * @param encode
     * @return
     */
    public static String postJson(String url, String params, String encode, Map<String, String> reqHeader) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            StringEntity entity = new StringEntity(encode);
            if (null != params) {
                entity = new StringEntity(params, encode);
            }
            if (reqHeader != null) {// 设置请求头信息
                for (String name : reqHeader.keySet()) {
                    httpPost.addHeader(name, reqHeader.get(name));
                }
            }
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, encode);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * GET请求, 结果以字符串形式返回.
     *
     * @param url 请求地址
     * @return 内容字符串
     */
    public static String getUrlAsStringHttps(String url) throws Exception {
        return getUrlAsStringHttps(url, null, null, DEFAULT_ENCODE);
    }


    public static String getUrlAsStringHttps(String url, Map<String, Object> params, Map<String, String> header,
                                             String encode) throws Exception {
        // 开始时间
        long t1 = System.currentTimeMillis();
        // 获得HttpGet对象
        HttpGet httpGet = getHttpGet(url, params, encode);
        // 发送请求
        String result = executeHttpsRequest(httpGet, header, encode);
        // 结束时间
        long t2 = System.currentTimeMillis();
        // 调试信息
        log.debug("result:" + result);
        log.debug("consume time:" + ((t2 - t1)));
        // 返回结果
        return result;
    }

    private static String executeHttpsRequest(HttpUriRequest httpUriRequest, Map<String, String> reqHeader, String encode) throws Exception {
        HttpClient client = null;
        String result = null;
        try {
            // 创建HttpClient对象
            client = new SSLClient();

            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 366000);// 设置连接超时时间
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 366000);// 设置Socket超时时间
            if (reqHeader != null) {// 设置请求头信息
                for (String name : reqHeader.keySet()) {
                    httpUriRequest.addHeader(name, reqHeader.get(name));
                }
            }

            HttpResponse response = client.execute(httpUriRequest);// 获得返回结果

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {// 如果成功
                result = EntityUtils.toString(response.getEntity(), encode);
            } else {// 如果失败
                StringBuffer errorMsg = new StringBuffer();
                errorMsg.append("httpStatus:");
                errorMsg.append(response.getStatusLine().getStatusCode());
                errorMsg.append(response.getStatusLine().getReasonPhrase());
                errorMsg.append(", Header: ");
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    errorMsg.append(header.getName());
                    errorMsg.append(":");
                    errorMsg.append(header.getValue());
                }
                log.error("HttpResonse Error:" + errorMsg);
            }
        } catch (Exception e) {
            log.error("http连接异常", e);
            throw new Exception("http连接异常");
        } finally {
            try {
                client.getConnectionManager().shutdown();
            } catch (Exception e) {
                log.error("finally HttpClient shutdown error", e);
            }
        }
        return result;
    }


    /**
     * 获得HttpGet对象
     *
     * @param url    请求地址
     * @param params 请求参数
     * @param encode 编码方式
     * @return HttpGet对象
     */
    public static HttpGet getHttpGet(String url, Map<String, Object> params, String encode) {
        StringBuffer buf = new StringBuffer(url);
        if (params != null) {
            // 地址增加?或者&
            String flag = (url.indexOf('?') == -1) ? "?" : "&";
            // 添加参数
            for (String name : params.keySet()) {
                buf.append(flag);
                buf.append(name);
                buf.append("=");
                try {
                    String param = String.valueOf(params.get(name));
                    if (param == null) {
                        param = "";
                    }
                    buf.append(URLEncoder.encode(param, encode));
                } catch (UnsupportedEncodingException e) {
                    log.error("URLEncoder Error,encode=" + encode + ",param=" + params.get(name), e);
                }
                flag = "&";
            }
        }
        log.info("url=" + buf.toString());
        HttpGet httpGet = new HttpGet(buf.toString());

        return httpGet;
    }
}
