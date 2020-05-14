package com.itstudent.springbootdemo.constant;

/**
 * @Project: springbootdemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/8/9 16:36
 * @Description:
 */
public enum ExceptionEnum {
    INVALID_INPUT("001","请求参数错误"),
    ACCOUNT_IS_NOTEXIT("401","用户未登录"),
    ACCOUNT_OR_PASSWORD_ERROR("002","账户名或密码错误！");
    private String errorCode;
    private String errorMsg;
    private ExceptionEnum(String errorCode,String errorMsg){
        this.errorCode=errorCode;
        this.errorMsg=errorMsg;
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
