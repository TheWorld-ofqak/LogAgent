package com.qak.logagent.core;


import com.qak.logagent.entity.LogObject;
import sun.rmi.runtime.Log;

import java.util.LinkedHashMap;

/**
 * @Description
 * @Author ankangqi
 * @Date 2020/12/12 4:31 下午
 **/
public class LogContext {

      private  static  final   ThreadLocal<LogObject>  threadLocal = ThreadLocal.withInitial(LogObject::new);


      public static LogObject get(){  return  threadLocal.get(); }

      public static void clear(){ threadLocal.remove(); }


}

  