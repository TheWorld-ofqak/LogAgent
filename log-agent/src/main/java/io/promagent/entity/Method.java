package io.promagent.entity;


import java.util.Arrays;

/**
 * @Description 请求controller 的方法相关
 * @Author ankangqi
 * @Date 2020/12/12 6:54 下午
 **/

public class Method {


    private String methodSignature;
    private Object[] args;
    private Long execTime;
    private Object returnResult;
    private Throwable methodThrow;
    private String type;

    public Method() {

    }


    public String getMethodSignature() {
        return methodSignature;
    }

    public void setMethodSignature(String methodSignature) {
        this.methodSignature = methodSignature;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Long getExecTime() {
        return execTime;
    }

    public void setExecTime(Long execTime) {
        this.execTime = execTime;
    }

    public Object getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(Object returnResult) {
        this.returnResult = returnResult;
    }

    public Throwable getMethodThrow() {
        return methodThrow;
    }

    public void setMethodThrow(Throwable methodThrow) {
        this.methodThrow = methodThrow;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Method{" +
                "methodSignature='" + methodSignature + '\'' +
                ", args=" + Arrays.toString(args) +
                ", execTime=" + execTime +
                ", returnResult=" + returnResult +
                ", methodThrow=" + methodThrow +
                ", type='" + type + '\'' +
                '}';
    }
}
