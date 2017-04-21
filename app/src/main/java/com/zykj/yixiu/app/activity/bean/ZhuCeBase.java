package com.zykj.yixiu.app.activity.bean;

/**
 * Created by Administrator on 2017/4/21.
 */

public class ZhuCeBase {


    /**
     * message : 注册成功
     * resp_code : 0
     * data : 4aea6484-d844-4a40-8b57-87aeaba8df59
     */

    private String message;
    private String resp_code;
    private String data;

    @Override
    public String toString() {
        return "ZhuCeBase{" +
                "message='" + message + '\'' +
                ", resp_code='" + resp_code + '\'' +
                ", data='" + data + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
