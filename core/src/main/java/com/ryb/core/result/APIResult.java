package com.ryb.core.result;

import com.ryb.core.resultenum.ResultEnum;

public class APIResult<T> extends ResultSupport {
    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 接口调用失败,有错误字符串码和描述,有返回对象
     * @param code
     * @param message
     * @param data
     * @param <U>
     * @return
     */
    public static <U> APIResult<U> newFailResult(int code, String message, U data) {
        APIResult<U> apiResult = new APIResult<U>();
        apiResult.setCode(code);
        apiResult.setMessage(message);
        apiResult.setData(data);
        return apiResult;
    }

    /**
     * 接口调用失败,有错误字符串码和描述,没有返回对象
     * @param
     * @param
     * @param <U>
     * @return
     */
    public static <U> APIResult<U> newFailResult(ResultEnum resultEnum) {
        APIResult<U> apiResult = new APIResult<U>();
        apiResult.setCode(Integer.parseInt(resultEnum.getCode()));
        apiResult.setMessage(resultEnum.getMsg());
        return apiResult;
    }


    public static <U> APIResult<U> newSuccessResult(U data){
        APIResult<U> apiResult = new APIResult<U>();
        apiResult.setData(data);
        apiResult.setCode(200);
        return apiResult;
    }

    public static <U> APIResult<U> newSuccessResult(){
        APIResult<U> apiResult = new APIResult<U>();
        return apiResult;
    }
}
