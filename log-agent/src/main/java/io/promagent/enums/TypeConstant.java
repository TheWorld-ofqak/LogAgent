package io.promagent.enums;

public enum TypeConstant {

    Normal(0, "正常的http请求"),
    CREATE(1, "Httpclient的http请求"),
    ASYNC(2, "异步请求"),
    DNS(3, "DNS的请求");


    private Integer value;
    private String desc;


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    TypeConstant(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
