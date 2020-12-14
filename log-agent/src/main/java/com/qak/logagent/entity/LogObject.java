package com.qak.logagent.entity;


import com.qak.logagent.enums.TypeConstant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 请描述类的业务用途
 * @Author ankangqi
 * @Date 2020/12/12 4:38 下午
 **/
public class LogObject {


    private  Long traceId;
    private  String  type= TypeConstant.Normal.getDesc();

    private Map<String,Object> request = new ConcurrentHashMap<>();
    private Map<String,Object> method  = new ConcurrentHashMap<>();


    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> header,String url,Map<String, String> params) {
      // request.put()
    }

    public Map<String, Object> getMethod() {
        return method;
    }

    public void setMethod(Map<String, Object> method) {
        this.method = method;
    }





}

  