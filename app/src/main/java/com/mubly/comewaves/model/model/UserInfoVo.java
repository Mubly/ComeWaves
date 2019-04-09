package com.mubly.comewaves.model.model;

import java.io.Serializable;

public class UserInfoVo implements Serializable {

    /**
     * user_head : http://abc.dingou.wang/ada5f201903161554509076.JPEG
     * user_name : 逆风的鱼
     * signature :
     * ding_num : 28922818845
     * background_img :
     * fans_num : 0
     * attention_num : 0
     */

    private String user_head;
    private String user_name;
    private String signature;
    private String ding_num;
    private String background_img;
    private int fans_num;
    private int attention_num;
    private String birthday;
    private String location;
    private String school;
    private String sex;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getDing_num() {
        return ding_num;
    }

    public void setDing_num(String ding_num) {
        this.ding_num = ding_num;
    }

    public String getBackground_img() {
        return background_img;
    }

    public void setBackground_img(String background_img) {
        this.background_img = background_img;
    }

    public int getFans_num() {
        return fans_num;
    }

    public void setFans_num(int fans_num) {
        this.fans_num = fans_num;
    }

    public int getAttention_num() {
        return attention_num;
    }

    public void setAttention_num(int attention_num) {
        this.attention_num = attention_num;
    }
}
