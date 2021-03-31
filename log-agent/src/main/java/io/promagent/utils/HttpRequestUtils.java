package io.promagent.utils;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 请描述类的业务用途
 * @Author ankangqi
 * @Date 2020/12/15 8:38 下午
 **/
public class HttpRequestUtils {


    /**
     * @param request
     * @Name:
     * @Description: 得到请求头信息
     * @return:
     * @Author: qiankang@kuaishou.com
     * @Date: 2020/12/14
     */
    public static Map<String, String> getHeaders(HttpServletRequest request) {

        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headers = request.getHeaderNames();

        while (headers.hasMoreElements()) {

            String key = headers.nextElement();
            String value = request.getHeader(key);
            headerMap.put(key, value);
        }

        return headerMap;

    }


    /**
     * @param request
     * @Name:
     * @Description: 得到请求中的 参数
     * @return:
     * @Author: qiankang@kuaishou.com
     * @Date: 2020/12/14
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> paramasMap = new HashMap();
        Enumeration parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = (String) parameterNames.nextElement();
            String paramValue = request.getParameter(paramName);
            paramasMap.put(paramName, paramValue);
        }
        return paramasMap;


    }

}

  