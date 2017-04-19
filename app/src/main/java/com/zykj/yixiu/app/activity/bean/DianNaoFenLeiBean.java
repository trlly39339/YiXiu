package com.zykj.yixiu.app.activity.bean;

/**
 * Created by zykj on 2017/4/19.
 */

public class DianNaoFenLeiBean {

    /**
     * id : 12
     * name : iMac 16,2
     * 电脑分类、型号
     */

    private int id;
    private String name;

    @Override
    public String toString() {
        return "DianNaoFenLeiBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
