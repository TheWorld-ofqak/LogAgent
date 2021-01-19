package com.qak.logagent.hooks;


import com.qak.logagent.Logger;
import com.qak.logagent.core.LogObjectProxy;
import com.qak.logagent.entity.Method;
import com.qak.logagent.enums.TypeConstant;
import io.promagent.annotations.After;
import io.promagent.annotations.Before;
import io.promagent.annotations.Hook;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.UUID;

@Hook( instruments ={
        "javax.servlet.http.HttpServlet",
        "javax.servlet.Filter"
})
public class HttpServletHook {


    @Before(method = {"service","doFilter"})
    public void before(ServletRequest httpRequest,ServletResponse httpResponse){

        try {
            HttpServletRequest request  =(HttpServletRequest)httpRequest;
            String logId = UUID.randomUUID().toString().substring(0,11); //request.getHeader("TRACE_ID");

            long startTime = System.currentTimeMillis();
            LogObjectProxy.setTempDate(startTime);

            Logger.httpServletBefore(logId,request);

        } catch (Throwable e){
            Logger.error(e);
        }

    }


    @After(method = {"service"})
    public void after(ServletRequest httpRequest,ServletResponse httpResponse){


        try {
            long endTime = System.currentTimeMillis();
            long spendTime = endTime - (long)LogObjectProxy.getTempDate();
            String type = TypeConstant.Normal.name();

            int bodyLength = httpResponse.getBufferSize();
            byte[] buffer = new byte[bodyLength];

            ServletOutputStream outputStream =  httpResponse.getOutputStream();
            outputStream.write(buffer,0,bodyLength);

            Method method = new Method();
            method.setExecTime(spendTime);
            method.setReturnResult(Arrays.toString(buffer));
            method.setType(type);

            Logger.info(method);
            Logger.httpServletAfter();

        } catch (Exception e){
            Logger.error(e);
        }
    }















}
