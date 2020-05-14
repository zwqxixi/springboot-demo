package com.itstudent.springbootdemo.constant;

/**
 * @Project: springbootdemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/8/9 15:33
 * @Description:
 */
public class ResultVo<T> {
    private boolean success=true;
    private String resultCode="0";
    private String errorMessage=null;
    private T data;

    public ResultVo(){

    }
    public ResultVo(String resultCode,String errorMessage){
        this.setSuccess(false);
        this.setResultCode(resultCode);
        this.setErrorMessage(errorMessage);
    }
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
}
