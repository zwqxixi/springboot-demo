package com.itstudent.springbootdemo.constant;

/**
 * @Project: springbootdemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/8/9 16:33
 * @Description:
 */
public class BaseException extends RuntimeException {
    private String errorCode;
    private String errorMessage;
    public BaseException(ExceptionEnum exceptionEnum){
        this.errorCode=exceptionEnum.getErrorCode();
        this.errorMessage=exceptionEnum.getErrorMsg();
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
