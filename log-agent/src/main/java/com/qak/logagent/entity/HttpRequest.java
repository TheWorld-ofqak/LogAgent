package com.qak.logagent.entity;


import java.util.Map;

/**
 * @Description httpRequest 请求实体
 * @Author ankangqi
 * @Date 2020/12/12 6:49 下午
 **/

public class HttpRequest {


    private Map<String,String>  header;
    private String url;
    private Map<String,String> params;

    private String responseBody;



    public HttpRequest(Map<String, String> header, String url, Map<String, String> params, String responseBody) {
        this.header = header;
        this.url = url;
        this.params = params;
        this.responseBody = responseBody;
    }

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }
}
