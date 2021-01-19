package com.qak.logagent;


import com.qak.logagent.core.LogObjectProxy;
import com.qak.logagent.entity.Method;
import com.qak.logagent.enums.TypeConstant;
import com.qak.logagent.utils.MdcUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 请描述类的业务用途
 * @Author ankangqi
 * @Date 2020/12/25 6:29 下午
 **/
public class Logger {


    public static  void info(Method method){

        LogObjectProxy.doLog(method);

    }









    /**
     * @Name:  http 请求之前的记录
     * @Date: 2021/1/19
     *
    */
    public static void httpServletBefore(String logId,HttpServletRequest request) {

        MdcUtils.setLogId(logId);
        LogObjectProxy.setRequest(request);
    }

    /**
     * @Name:  http 请求之后的记录
     * @Date: 2021/1/19
     *
     */
    public static void httpServletAfter(){

        MdcUtils.mdcClear();
        LogObjectProxy.clean();
    }



    public static void error(Throwable frameError) {
        try {
            frameError.printStackTrace();
            LogObjectProxy.error(frameError);
        } catch (Throwable ignore) {
            ignore.printStackTrace();
        }
    }

}

  