package com.mubly.comewaves.model.model;

import java.io.Serializable;
import java.util.List;

public class CommentInfo implements Serializable {
    public String usrName;
    public String avtar;
    public String creatTime;
    public String coment;
    public List<CommenInInfo> commenInInfo;

    public static class CommenInInfo {

    }

}
