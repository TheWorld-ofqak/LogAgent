package com.qak.logagent.core;


import com.qak.logagent.entity.HttpRequest;
import com.qak.logagent.entity.LogObject;
import com.qak.logagent.entity.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static com.qak.logagent.utils.HttpRequestUtils.getHeaders;
import static com.qak.logagent.utils.HttpRequestUtils.getParams;

/**
 * @Descriptio 用于要打出来日志的日志代理   日志实体类的代理类，对日志进行打印
 * @Author ankangqi
 * @Date 2020/12/15 8:35 下午
 **/
public class LogObjectProxy {


    private static final Logger logger = LoggerFactory.getLogger(LogObjectProxy.class);



    public static  void setRequest(HttpServletRequest request){

        String requestUrl = request.getRequestURI();   // 得到url
        Map<String, String> headers = getHeaders(request);
        Map<String, String> params = getParams(request);

        HttpRequest httpRequesT = new HttpRequest(requestUrl,headers,params);

        LogObject currLogObject = LogContext.get();
        currLogObject.setHttpRequest(httpRequesT);

    }

    public static void setTempDate(Object tempObject){

        LogObject currLogObject = LogContext.get();
        currLogObject.setTempDate(tempObject);

    }

    public static Object getTempDate(){

        LogObject currLogObject = LogContext.get();
        return currLogObject.getTempDate();

    }




    public static void doLog(Method method) {
        LogObject logObject = LogContext.get();
        logObject.setMethod(method);
        String msg = logObject.getLogJSON();

        if (method.getMethodThrow() !=null){
            logger.error(msg);
        } else {
            logger.info(msg);
        }
    }



    public static void clean() {
        LogContext.clear();
    }


    public static void error(Throwable error) {
        LogObject logObject = LogContext.get();
        logObject.setMsg(error.toString());

        logger.error(logObject.getLogJSON());
    }

}

  