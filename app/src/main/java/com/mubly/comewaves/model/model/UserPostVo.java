package com.mubly.comewaves.model.model;

import java.io.Serializable;

public class UserPostVo implements Serializable {

    /**
     * status : 2
     * post_id : 178
     * collection : http://abc.dingou.wang/99475201903051954141657.png
     */

    private int status;
    private int post_id;
    private String collection;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
