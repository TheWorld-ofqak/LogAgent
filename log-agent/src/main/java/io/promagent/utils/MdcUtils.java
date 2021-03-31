package io.promagent.utils;


import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;


public class MdcUtils {


    private static final String LogId = "LogId";

    public static void setLogId(String val) {
        val = StringUtils.isEmpty(val) ? UUID.randomUUID().toString() : val;
        MDC.put(LogId, val);
//        MDC.put(LogConstants.mdc_appName, LogConfig.appName);
//        MDC.put(LogConstants.mdc_appEvn, LogConfig.appEvn);
    }

    public static String getLogId() {
        String logId = MDC.get(LogId);
        if (StringUtils.isEmpty(logId)) {
            logId = UUID.randomUUID().toString();
            MDC.put(logId, logId);
            return logId;
        }
        return logId;
    }

    public static void mdcClear() {
        MDC.clear();
    }


}
