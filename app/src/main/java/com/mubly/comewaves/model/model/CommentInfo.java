package com.mubly.comewaves.model.model;

import java.io.Serializable;
import java.util.List;

public class CommentInfo implements Serializable {


    /**
     * comment_id : 139
     * user_id : 5
     * created_time : 2019-03-18 15:34:04
     * like_num : 1
     * content : asdas
     * user_name : 且随疾风前行
     * user_head : http://abc.dingou.wang/bae87201903131735457029.jpg
     * type : 2
     * community_post_id : 325
     * combine : [{"img":"http://abc.dingou.wang/2704f201903181534059734.jpg"},{"img":"http://abc.dingou.wang/0e518201903181534051834.jpg"},{"img":"http://abc.dingou.wang/742052019031815340524.jpg"}]
     * combine_status : 2    1:文章 2:文章图片 3:视频
     * like_status : 0
     */

    private int comment_id;
    private int user_id;
    private String created_time;
    private int like_num;
    private String content;
    private String user_name;
    private String user_head;
    private int type;
    private int community_post_id;
    private int combine_status;
    private int like_status;
    private List<CombineBean> combine;
    private List<ReportCombine>report_combine;

    public List<ReportCombine> getReport_combine() {
        return report_combine;
    }

    public void setReport_combine(List<ReportCombine> report_combine) {
        this.report_combine = report_combine;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCommunity_post_id() {
        return community_post_id;
    }

    public void setCommunity_post_id(int community_post_id) {
        this.community_post_id = community_post_id;
    }

    public int getCombine_status() {
        return combine_status;
    }

    public void setCombine_status(int combine_status) {
        this.combine_status = combine_status;
    }

    public int getLike_status() {
        return like_status;
    }

    public void setLike_status(int like_status) {
        this.like_status = like_status;
    }

    public List<CombineBean> getCombine() {
        return combine;
    }

    public void setCombine(List<CombineBean> combine) {
        this.combine = combine;
    }

    public static class CombineBean implements Serializable{
        /**
         * img : http://abc.dingou.wang/2704f201903181534059734.jpg
         */

        private String img;
        private String video;

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public static class ReportCombine implements Serializable {


        /**
         * report_id : 45
         * user_id : 5
         * common_id : 86
         * form_user_id : 44
         * community_post_id : 231
         * content : ???
         * type : 1
         * like_num : 0
         * created_time : 2019-03-13 14:08:48
         * form_user : {"user_name":"Hong","user_head":"http://abc.dingou.wang/5fce2201903051620205536.jpg"}
         * user : {"user_name":"责任阿斯重大","user_head":"http://abc.dingou.wang/c8db720190313120947825.jpg"}
         * combine_status : 1
         * like_status : 0
         */

        private int report_id;
        private int user_id;
        private int common_id;
        private int form_user_id;
        private int community_post_id;
        private String content;
        private int type;
        private int like_num;
        private String created_time;
        private int combine_status;
        private int like_status;
        private User form_user;
        private User user;

        public User getForm_user() {
            return form_user;
        }

        public void setForm_user(User form_user) {
            this.form_user = form_user;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public int getReport_id() {
            return report_id;
        }

        public void setReport_id(int report_id) {
            this.report_id = report_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getCommon_id() {
            return common_id;
        }

        public void setCommon_id(int common_id) {
            this.common_id = common_id;
        }

        public int getForm_user_id() {
            return form_user_id;
        }

        public void setForm_user_id(int form_user_id) {
            this.form_user_id = form_user_id;
        }

        public int getCommunity_post_id() {
            return community_post_id;
        }

        public void setCommunity_post_id(int community_post_id) {
            this.community_post_id = community_post_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getCombine_status() {
            return combine_status;
        }

        public void setCombine_status(int combine_status) {
            this.combine_status = combine_status;
        }

        public int getLike_status() {
            return like_status;
        }

        public void setLike_status(int like_status) {
            this.like_status = like_status;
        }
    }

    public static class User implements Serializable{
        private String user_name;
        private String user_head;

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
    }
}
