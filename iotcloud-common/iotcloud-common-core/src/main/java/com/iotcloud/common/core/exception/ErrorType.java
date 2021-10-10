package com.iotcloud.common.core.exception;

public enum ErrorType {

    SERVER_START_ERROR       ("1","端口已有实例运行"),
    SERVER_STOP_ERROR        ("2","端口无运行实例"),
    SERVER_RUN_ERROR        ("3","运行出现异常"),
    SERVER_JAR_ERROR        ("4","自定义类加载异常"),
    SERVER_JARNOTFOUND_ERROR   ("5","自定义类为空"),
    SERVER_OTHER_ERROR      ("6","其他异常"),
    SERVER_JAREMPTY_ERROR      ("6","定义类为空");//枚举类如果写方法的话，此处需要写分号

    private String ecode;

    private String emsg;

    ErrorType(String ecode, String emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public String getEcode() {
        return ecode;
    }

    public String getEmsg() {
        return emsg;
    }

    public static ErrorType statOf(String ecode) {
        for (ErrorType state : values())
            if (state.getEcode().equals(ecode))
                return state;
        return null;
    }

}
