package com.mubly.comewaves.model.model;

import java.io.Serializable;

public class VisitorInfo implements Serializable {

    /**
     * user_name : 责任阿斯重大
     * user_head : http://abc.dingou.wang/bae87201903131735457029.jpg
     * content : aksdkal asdasdasd
     * like_num : 2
     * created_time : 2019-03-12 14:49:43
     * comment_id : 65
     * like_status : 0
     */

    private String user_name;
    private String user_head;
    private String content;
    private int like_num;
    private String created_time;
    private int comment_id;
    private int like_status;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getLike_status() {
        return like_status;
    }

    public void setLike_status(int like_status) {
        this.like_status = like_status;
    }
}
