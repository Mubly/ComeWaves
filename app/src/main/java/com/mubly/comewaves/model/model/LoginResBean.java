package com.mubly.comewaves.model.model;

import java.io.Serializable;
import java.util.List;

public class LoginResBean implements Serializable {

    /**
     * user_name : 逆风的鱼
     * sex : 男
     * user_tel : 18639058709
     * user_head : http://abc.dingou.wang/ada5f201903161554509076.JPEG
     * ding_num : 28922818845
     * token : 6ZKJ5aSfMTU1MjcyMjg5MjQ1
     */

    private String user_name;
    private String sex;
    private String user_tel;
    private String user_head;
    private String ding_num;
    private String token;
    private List<CategoryVo>category;

    public List<CategoryVo> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryVo> category) {
        this.category = category;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getDing_num() {
        return ding_num;
    }

    public void setDing_num(String ding_num) {
        this.ding_num = ding_num;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
