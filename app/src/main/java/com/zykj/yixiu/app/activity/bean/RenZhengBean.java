package com.zykj.yixiu.app.activity.bean;

/**
 * Created by zykj on 2017/5/11.
 */

public class RenZhengBean {

    /**
     * idcard_image1 : idcard/bac82340-05e2-4307-a932-ab2382b46ea4.jpg
     * idcard_image2 : idcard/3f925c3c-9c3c-4dec-ac4c-8ae4b19347ed.jpg
     */

    private String idcard_image1;
    private String idcard_image2;

    @Override
    public String toString() {
        return "RenZhengBean{" +
                "idcard_image1='" + idcard_image1 + '\'' +
                ", idcard_image2='" + idcard_image2 + '\'' +
                '}';
    }

    public String getIdcard_image1() {
        return idcard_image1;
    }

    public void setIdcard_image1(String idcard_image1) {
        this.idcard_image1 = idcard_image1;
    }

    public String getIdcard_image2() {
        return idcard_image2;
    }

    public void setIdcard_image2(String idcard_image2) {
        this.idcard_image2 = idcard_image2;
    }
}
