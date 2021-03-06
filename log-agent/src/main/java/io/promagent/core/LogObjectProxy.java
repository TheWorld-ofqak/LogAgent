package io.promagent.core;


import io.promagent.entity.HttpRequest;
import io.promagent.entity.LogObject;
import io.promagent.entity.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static io.promagent.utils.HttpRequestUtils.getHeaders;
import static io.promagent.utils.HttpRequestUtils.getParams;

/**
 * @Descriptio 用于要打出来日志的日志代理   日志实体类的代理类，对日志进行打印
 * @Author ankangqi
 * @Date 2020/12/15 8:35 下午
 **/
public class LogObjectProxy {


    private static final Logger logger = LoggerFactory.getLogger(LogObjectProxy.class);


    public static void setRequest(HttpServletRequest request) {

        String requestUrl = request.getRequestURI();   // 得到url
        Map<String, String> headers = getHeaders(request);
        Map<String, String> params = getParams(request);

        HttpRequest httpRequesT = new HttpRequest(requestUrl, headers, params);


        LogObject currLogObject = LogContext.get();
        currLogObject.setHttpRequest(httpRequesT);

    }

    public static void setResponse(HttpServletResponse response) {


        LogObject currLogObject = LogContext.get();
        currLogObject.getHttpRequest().setResponseBody(response.toString());


    }


    public static void setMethod(Long execTime, Throwable error, Object ret, String sign, String type, Object... args) {
        Method method = LogContext.get().getMethod();

        method.setMethodSignature(sign);
        method.setExecTime(execTime);
        method.setArgs(args);
        method.setReturnResult(ret);
        method.setMethodThrow(error);
        method.setType(type);
    }


    public static void setTempDate(Object tempObject) {

        LogObject currLogObject = LogContext.get();
        currLogObject.setTempDate(tempObject);

    }

    public static Object getTempDate() {

        LogObject currLogObject = LogContext.get();
        return currLogObject.getTempDate();

    }


    public static void doLog() {

        String jsonMsg = LogContext.get()
                .getLogJSON();

        logger.info(jsonMsg);

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

  