package com.mubly.comewaves.model.model;

import com.mubly.comewaves.common.base.BaseModel;

public class SearchVideoVo extends BaseModel {

    /**
     * user_name : 休息休息
     * user_head : http://abc.dingou.wang/d3431201903151746069146.png
     * post_id : 279
     * fabulous_num : 0
     * collection_num : 0
     * report_num : 0
     * add_time : 2019-03-16 14:46:03
     * location : 上海市嘉定区华江公路靠近华江路立交桥
     * weft : 31.250535481770832
     * through : 121.33507052951389
     * first_url : http://abc.dingou.wang/d3f2a201903161446033805.png
     * video_url : http://abc.dingou.wang/005ce201903161446033318.mp4
     * like_status : 0
     * collect_status : 0
     */

    private String user_name;
    private String user_head;
    private int post_id;
    private int fabulous_num;
    private int collection_num;
    private int report_num;
    private String add_time;
    private String location;
    private String weft;
    private String through;
    private String first_url;
    private String video_url;
    private int like_status;
    private int collect_status;

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

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getFabulous_num() {
        return fabulous_num;
    }

    public void setFabulous_num(int fabulous_num) {
        this.fabulous_num = fabulous_num;
    }

    public int getCollection_num() {
        return collection_num;
    }

    public void setCollection_num(int collection_num) {
        this.collection_num = collection_num;
    }

    public int getReport_num() {
        return report_num;
    }

    public void setReport_num(int report_num) {
        this.report_num = report_num;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeft() {
        return weft;
    }

    public void setWeft(String weft) {
        this.weft = weft;
    }

    public String getThrough() {
        return through;
    }

    public void setThrough(String through) {
        this.through = through;
    }

    public String getFirst_url() {
        return first_url;
    }

    public void setFirst_url(String first_url) {
        this.first_url = first_url;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getLike_status() {
        return like_status;
    }

    public void setLike_status(int like_status) {
        this.like_status = like_status;
    }

    public int getCollect_status() {
        return collect_status;
    }

    public void setCollect_status(int collect_status) {
        this.collect_status = collect_status;
    }
}
