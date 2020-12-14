package com.qak.logagent.hooks;


import com.qak.logagent.core.LogContext;
import com.qak.logagent.entity.LogObject;
import io.promagent.annotations.After;
import io.promagent.annotations.Before;
import io.promagent.annotations.Hook;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Hook( instruments ={
        "javax.servlet.Servlet",
        "javax.servlet.Filter"
})
public class HttpServletHook {


    @Before(method = {"service","doFilter"})
    public void before(ServletRequest httpRequest,ServletResponse httpResponse){

       HttpServletRequest request  =(HttpServletRequest)httpRequest;

       String requestUrl = request.getRequestURI();   // 得到url

                                          //得到请求的参数
                                            // 从请求头里面去得到一些traceid 用于链路追踪




        LogObject currLogObject = LogContext.get();
       // currLogObject.setRequest();



    }






}
