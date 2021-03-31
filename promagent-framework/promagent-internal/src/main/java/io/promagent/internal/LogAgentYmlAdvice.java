package io.promagent.internal;


import io.promagent.agent.ClassLoaderCache;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

import java.lang.reflect.Method;

/**
 * @Description 请描述类的业务用途
 * @Author ankangqi
 * @Date 2021/1/28 2:37 下午
 **/

public class LogAgentYmlAdvice {

    @Advice.OnMethodEnter
    public static Long before() {

        return System.currentTimeMillis();
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void after(@Advice.Enter Long startTime,
                             @Origin Method method,
                             @AllArguments Object[] args,
                             @Advice.Return(typing = Assigner.Typing.DYNAMIC) Object returned,
                             @Advice.Thrown Throwable throwable) {
        Class<?> logInfoClass = null;
        try {

            logInfoClass = ClassLoaderCache.getInstance().currentClassLoader().loadClass("com.qak.logagent.Logger");

            Method logMethod = logInfoClass.getMethod("info", Long.class, Throwable.class, Object.class, Method.class, Object.class);
            Long execTime = System.currentTimeMillis() - startTime;
            logMethod.invoke(null, execTime, throwable, returned, method, args);


        } catch (Throwable error) {

            try {
                if (logInfoClass == null) {
                    logInfoClass = ClassLoaderCache.getInstance().currentClassLoader().loadClass("com.qak.logagent.Logger");
                }
                if (logInfoClass != null) {
                    Method frameErrorMethod = logInfoClass.getMethod("error", Throwable.class);
                    frameErrorMethod.invoke(null, error);
                }
            } catch (Throwable throwable1) {
                throwable1.printStackTrace();
            }
        }

    }


}
