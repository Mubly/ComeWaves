package com.mubly.comewaves.model.model;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class HomeBean implements Serializable {

    /**
     * first_url : http://abc.cuiyangblog.com/cbf7f201812271333406350.mp4?vframe/png/offset/1
     * post_id : 1
     * post_desc : demo
     * post_info : demo
     * video_url : http://abc.cuiyangblog.com/cbf7f201812271333406350.mp4
     * fabulous_num : 0
     * report_num : 0
     * share_num : 0
     * collection_num : 0
     * location : 上海申窑艺术中心
     * weft : 31.2504300000
     * through : 121.3360500000
     * add_time : 2018-12-28 18:25:46
     * user_id : 5
     * user_name : null
     * user_head : null
     * cate_name : 男装
     * cate_id : 1
     */

    private String first_url;
    private int post_id;
    private String post_desc;//图片的第一张或视频的第一帧
    private String post_info;
    private String video_url;
    private int fabulous_num;
    private int report_num;
    private int share_num;
    private int collection_num;
    private String location;
    private int status;
    private int is_push;
    private int video_status;
    private int img_status;
    private int like_status;//0:取消点赞1；点赞
    private int collect_status;
    private String weft;
    private String through;
    private String add_time;
    private int user_id;
    private String user_name;
    private String user_head;
    private String cate_name;
    private int cate_id;
    public int width;
    public int height;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_push() {
        return is_push;
    }

    public void setIs_push(int is_push) {
        this.is_push = is_push;
    }

    public int getVideo_status() {
        return video_status;
    }

    public void setVideo_status(int video_status) {
        this.video_status = video_status;
    }

    public int getImg_status() {
        return img_status;
    }

    public void setImg_status(int img_status) {
        this.img_status = img_status;
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

    public String getFirst_url() {
        return first_url;
    }

    public void setFirst_url(String first_url) {
        this.first_url = first_url;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public String getPost_info() {
        return post_info;
    }

    public void setPost_info(String post_info) {
        this.post_info = post_info;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public int getFabulous_num() {
        return fabulous_num;
    }

    public void setFabulous_num(int fabulous_num) {
        this.fabulous_num = fabulous_num;
    }

    public int getReport_num() {
        return report_num;
    }

    public void setReport_num(int report_num) {
        this.report_num = report_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public int getCollection_num() {
        return collection_num;
    }

    public void setCollection_num(int collection_num) {
        this.collection_num = collection_num;
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

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

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

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }
}
