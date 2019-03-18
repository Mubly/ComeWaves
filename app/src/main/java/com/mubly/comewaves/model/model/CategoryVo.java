package com.mubly.comewaves.model.model;

import com.mubly.comewaves.common.base.BaseModel;

public class CategoryVo extends BaseModel {
    private int cate_id;
    private String cate_name;

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }
}
