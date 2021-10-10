package com.iotcloud.system.analysis.exception;

import com.iotcloud.common.core.config.CommonResult;
import com.iotcloud.common.core.exception.NettyServerException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class NettyExceptionHandler {

    @ExceptionHandler(value = NettyServerException.class)
    public CommonResult handleNetty(NettyServerException e) {
        return CommonResult.failed(e.getMessage());
    }
}
