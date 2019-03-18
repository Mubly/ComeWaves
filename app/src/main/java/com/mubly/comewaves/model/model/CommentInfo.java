package com.mubly.comewaves.model.model;

import java.io.Serializable;
import java.util.List;

public class CommentInfo implements Serializable {


    /**
     * report_id : 1
     * reply_num : 0
     * fabulous_num : 0
     * create_time : 2018-12-28 18:25:46
     * content : 111
     * image_url : http://abc.cuiyangblog.com/00f0f201811221733356080.jpg,http://abc.cuiyangblog.com/292f820181202110337622.jpg
     * user_name :
     * user_head :
     * reply : [{"reply_id":1,"fabulous_num":0,"create_time":"1970-01-01 08:00:00","content":"111-111","user_name":"","user_head":""},{"reply_id":2,"fabulous_num":0,"create_time":"1970-01-01 08:00:00","content":"111-222","image_url":null,"user_name":"","user_head":""},{"reply_id":3,"fabulous_num":0,"create_time":"1970-01-01 08:00:00","content":"111-333","video_url":"","user_name":"","user_head":""}]
     */

    private int report_id;
    private int reply_num;
    private int fabulous_num;
    private String create_time;
    private String content;
    private String image_url;
    private String user_name;
    private String user_head;
    private List<ReplyBean> reply;

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public int getReply_num() {
        return reply_num;
    }

    public void setReply_num(int reply_num) {
        this.reply_num = reply_num;
    }

    public int getFabulous_num() {
        return fabulous_num;
    }

    public void setFabulous_num(int fabulous_num) {
        this.fabulous_num = fabulous_num;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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

    public List<ReplyBean> getReply() {
        return reply;
    }

    public void setReply(List<ReplyBean> reply) {
        this.reply = reply;
    }

    public static class ReplyBean {
        /**
         * reply_id : 1
         * fabulous_num : 0
         * create_time : 1970-01-01 08:00:00
         * content : 111-111
         * user_name :
         * user_head :
         * image_url : null
         * video_url :
         */

        private int reply_id;
        private int fabulous_num;
        private String create_time;
        private String content;
        private String user_name;
        private String user_head;
        private Object image_url;
        private String video_url;

        public int getReply_id() {
            return reply_id;
        }

        public void setReply_id(int reply_id) {
            this.reply_id = reply_id;
        }

        public int getFabulous_num() {
            return fabulous_num;
        }

        public void setFabulous_num(int fabulous_num) {
            this.fabulous_num = fabulous_num;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
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

        public Object getImage_url() {
            return image_url;
        }

        public void setImage_url(Object image_url) {
            this.image_url = image_url;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }
    }
}
