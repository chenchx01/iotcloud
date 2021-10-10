package com.iotcloud.common.core.exception;

import java.io.Serializable;

public class NettyServerException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    /*
     * 模版异常
     */
    private ErrorType exceptionEnums;
    /*
     * 自定义异常信息
     */
    private String message;

    /**
     * 带自定义异常信息的构造方法
     * @param exceptionEnums
     * @param message
     */
    public NettyServerException(ErrorType exceptionEnums,String message){
        this.exceptionEnums = exceptionEnums;
        this.message = message;
    }

    /**
     * 模版异常的构造方法
     * @param exceptionEnums
     */
    public NettyServerException(ErrorType exceptionEnums){
        this.exceptionEnums = exceptionEnums;
        this.message = exceptionEnums.getEmsg();
    }

    public ErrorType getExceptionEnums() {
        return exceptionEnums;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
