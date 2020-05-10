package com.ryb.core.exception;

import com.ryb.core.resultenum.ResultEnum;

public class ExceptionHandler extends Exception {
    public ExceptionHandler(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
    }
}
