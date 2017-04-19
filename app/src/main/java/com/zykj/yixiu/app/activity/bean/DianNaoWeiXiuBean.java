package com.zykj.yixiu.app.activity.bean;

/**
 * Created by zykj on 2017/4/19.
 */

public class DianNaoWeiXiuBean {

    /**
     * id : 4
     * category :
     * name : 苹果
     * pid :
     * type : 1
     * 电脑品牌
     */

    private int id;
    private String category;
    private String name;
    private String pid;
    private String type;

    @Override
    public String toString() {
        return "DianNaoWeiXiuBean{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
