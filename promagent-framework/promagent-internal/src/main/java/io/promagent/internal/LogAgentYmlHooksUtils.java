package io.promagent.internal;


import io.promagent.agent.ClassLoaderCache;
import net.bytebuddy.agent.builder.AgentBuilder;

/**
 * @Description 用于通用方法
 * @Author ankangqi
 * @Date 2021/1/28 2:32 下午
 **/
public class LogAgentYmlHooksUtils {

    public static AgentBuilder applyHooks(AgentBuilder agentBuilder, ClassLoaderCache classLoaderCache) {

        // 将自己配置的 配置文件中的类和方法 进行增强 进行收集日志参数
        agentBuilder = applyAnnMethodHook(agentBuilder, classLoaderCache); // 对注解的方法来增强

        return agentBuilder;

    }

    private static AgentBuilder applyAnnMethodHook(AgentBuilder agentBuilder, ClassLoaderCache classLoaderCache) {

        String annMethodHook = System.getProperty("");
        if (annMethodHook == null) {
            return agentBuilder;
        }


        return agentBuilder;
    }


}

  