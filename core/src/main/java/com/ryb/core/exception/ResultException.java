package com.ryb.core.exception;


import com.ryb.core.result.APIResult;
import com.ryb.core.resultenum.ResultEnum;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ResultException {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public static APIResult resultException(Exception e) {
        return APIResult.newFailResult(ResultEnum.ERROR);
    }
}
