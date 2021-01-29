package com.qak.logagent.hooks;


import com.qak.logagent.Logger;
import com.qak.logagent.core.LogObjectProxy;
import com.qak.logagent.enums.TypeConstant;
import io.promagent.annotations.After;
import io.promagent.annotations.Before;
import io.promagent.annotations.Hook;
import io.promagent.annotations.Thrown;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Hook( instruments ={
        "javax.servlet.http.HttpServlet",
        "javax.servlet.Filter"
})
public class HttpServletHook {

    @Before(method = {"service"})
    public void serviceBefore (ServletRequest request, ServletResponse response){
        doBefore(request,response);
    }
    @Before(method = {"doFilter"})
    public void doFilterBefore(ServletRequest request, ServletResponse response, FilterChain chain){
        doBefore(request,response);
    }

    @After(method = {"service"})
    public void serviceAfter(ServletRequest request, ServletResponse response , @Thrown Throwable t){
        doAfter(request,response ,t, "service");
    }

    @After(method = {"doFilter"})
    public void serviceAfter(ServletRequest request, ServletResponse response, FilterChain chain, @Thrown Throwable t){
        doAfter(request,response ,t, "doFilter");
    }



    public void doBefore(ServletRequest httpRequest,ServletResponse httpResponse){

        try {

            HttpServletRequest request  =(HttpServletRequest)httpRequest;

            long startTime = System.currentTimeMillis();
            LogObjectProxy.setTempDate(startTime);

            LogObjectProxy.setRequest(request);

        } catch (Throwable e){
            Logger.error(e);
        }

    }


    public void doAfter(ServletRequest httpRequest,ServletResponse httpResponse,Throwable t, String signature){


        try {
            long endTime = System.currentTimeMillis();
            long execTime = endTime - (long)LogObjectProxy.getTempDate();

            int bodyLength = httpResponse.getBufferSize();
            byte[] buffer = new byte[bodyLength];

            ServletOutputStream outputStream =  httpResponse.getOutputStream();
            outputStream.write(buffer,0,bodyLength);

            LogObjectProxy.setMethod(execTime,t,httpResponse.toString(), Arrays.toString(buffer),TypeConstant.Normal.name(),null);

            LogObjectProxy.doLog();

        } catch (Exception e){
            Logger.error(e);
        }
    }















}
