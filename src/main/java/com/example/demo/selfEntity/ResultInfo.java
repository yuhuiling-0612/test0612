package com.example.demo.selfEntity;

public class ResultInfo {
    private Integer code;
    private String msg;
    private Object data;
    private Boolean isSuccess;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public Boolean getSuccess() {
        return isSuccess;
    }

    public ResultInfo() {
    }
    public ResultInfo(Integer code, String msg, Object data, Boolean success) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        isSuccess = success;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
