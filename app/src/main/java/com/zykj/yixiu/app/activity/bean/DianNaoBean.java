package com.zykj.yixiu.app.activity.bean;

import java.io.Serializable;

/**
 * 电脑维修数据存储类
 * Created by zykj on 2017/5/4.
 */

public class DianNaoBean implements Serializable{
    String pinpai,leixing,xinghao,guzhang,guzhangmiaoshu,fileimg;

    @Override
    public String toString() {
        return "DianNaoBean{" +
                "pinpai='" + pinpai + '\'' +
                ", leixing='" + leixing + '\'' +
                ", xinghao='" + xinghao + '\'' +
                ", guzhang='" + guzhang + '\'' +
                ", guzhangmiaoshu='" + guzhangmiaoshu + '\'' +
                ", fileimg='" + fileimg + '\'' +
                '}';
    }

    public String getPinpai() {
        return pinpai;
    }

    public void setPinpai(String pinpai) {
        this.pinpai = pinpai;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public String getXinghao() {
        return xinghao;
    }

    public void setXinghao(String xinghao) {
        this.xinghao = xinghao;
    }

    public String getGuzhang() {
        return guzhang;
    }

    public void setGuzhang(String guzhang) {
        this.guzhang = guzhang;
    }

    public String getGuzhangmiaoshu() {
        return guzhangmiaoshu;
    }

    public void setGuzhangmiaoshu(String guzhangmiaoshu) {
        this.guzhangmiaoshu = guzhangmiaoshu;
    }

    public String getFileimg() {
        return fileimg;
    }

    public void setFileimg(String fileimg) {
        this.fileimg = fileimg;
    }
}
