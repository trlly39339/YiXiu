package com.zykj.yixiu.app.activity.bean;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

/**
 * 手机维修数据存储类
 * Created by zykj on 2017/5/4.
 */

public class PhoneBean implements Serializable{
    String tvPinpai,tvXinghao,tvGuzhang,evGuzhangMiaoshu,file;

    @Override
    public String toString() {
        return "PhoneBean{" +
                "tvPinpai='" + tvPinpai + '\'' +
                ", tvXinghao='" + tvXinghao + '\'' +
                ", tvGuzhang='" + tvGuzhang + '\'' +
                ", evGuzhangMiaoshu='" + evGuzhangMiaoshu + '\'' +
                ", file='" + file + '\'' +
                '}';
    }

    public String getTvPinpai() {
        return tvPinpai;
    }

    public void setTvPinpai(String tvPinpai) {
        this.tvPinpai = tvPinpai;
    }

    public String getTvXinghao() {
        return tvXinghao;
    }

    public void setTvXinghao(String tvXinghao) {
        this.tvXinghao = tvXinghao;
    }

    public String getTvGuzhang() {
        return tvGuzhang;
    }

    public void setTvGuzhang(String tvGuzhang) {
        this.tvGuzhang = tvGuzhang;
    }
    public String getEvGuzhangMiaoshu() {
        return evGuzhangMiaoshu;
    }

    public void setEvGuzhangMiaoshu(String evGuzhangMiaoshu) {
        this.evGuzhangMiaoshu = evGuzhangMiaoshu;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    //    intent.putExtra("tvPinpai",tvPinpai.getText().toString().trim());
//    intent.putExtra("tvXinghao",tvXinghao.getText().toString().trim());
//    intent.putExtra("tvGuzhang",tvGuzhang.getText().toString().trim());
//    intent.putExtra("evGuzhangMiaoshu",evGuzhangMiaoshu.getText().toString().trim());
//    intent.putExtra("file",photoPath);
//    intent.putExtra("LeiXing","1");
}
