package com.mubly.comewaves.view.costomview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class TagInputEditView extends AppCompatEditText {
    // 默认,话题文本高亮颜色
    private static final int FOREGROUND_COLOR = Color.parseColor("#FF8C00");
    private int mForegroundColor = FOREGROUND_COLOR;// 话题文本高亮颜色
    private String startKey = "#";
    private String endKey = " ";

    public void setKey(String key) {
        startKey = key;
        endKey = key;
    }

    public void setStartKey(String starKey) {
        this.startKey = startKey;
    }

    public void setEndKey(String endKey) {
        this.endKey = endKey;
    }

    public void setmForegroundColor(int mForegroundColor) {
        this.mForegroundColor = mForegroundColor;
    }

    private List<String> tagList = new ArrayList<String>();// object集合

    public TagInputEditView(Context context) {
        this(context, null);
    }

    public TagInputEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化设置
        initView();
    }

    private void initView() {
        /**
         * 输入框内容变化监听<br/>
         * 1.当文字内容产生变化的时候实时更新UI
         */
        this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文字改变刷新UI
                refreshEditTextUI(s.toString());
            }
        });
    }

    /**
     * EditText内容修改之后刷新UI
     *
     * @param content 输入框内容
     */
    private void refreshEditTextUI(String content) {

        /**
         * 内容变化时操作<br/>
         * 1.查找匹配所有话题内容 <br/>
         * 2.设置话题内容特殊颜色
         */

        if (TextUtils.isEmpty(content)) {
            tagList.clear();
            return;
        }

        /**
         * 提取/刷新话题中的标签
         */
        refreshTagList(content);

        if (tagList.size() == 0)
            return;


        /**
         * 重新设置span
         */
        Editable editable = getText();
        int findPosition = 0;
        for (int i = 0; i < tagList.size(); i++) {
            final String objectText = tagList.get(i);
            findPosition = content.indexOf(objectText);// 获取文本开始下标

            if (findPosition != -1) {// 设置话题内容前景色高亮

                ForegroundColorSpan colorSpan = new ForegroundColorSpan(
                        mForegroundColor);
                editable.setSpan(colorSpan, findPosition, findPosition
                                + objectText.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        }

    }

    private void refreshTagList(String content) {
        tagList.clear();
        if (startKey.equals(endKey)) {//开始与结束的key是一样的

        } else {//开始与结束的key是不一样的
            noEquals(content);
        }

    }

    private void noEquals(String content) {
        String[] contentSplit = content.split(startKey);
        for (int i = 1; i < contentSplit.length; i++) {
            String continKeyValue = contentSplit[i];
            if (continKeyValue.endsWith(endKey)) {
                String tagKey = startKey + continKeyValue.substring(0, continKeyValue.indexOf(endKey)) + endKey;
                tagList.add(tagKey);
            }
//            else {//暂时不要
//                tagList.add(startKey+continKeyValue+endKey);
//            }

        }
    }

    /**
     * 获取object列表数据
     */
    public List<String> getTagList() {

        return tagList;
    }

    //计算字符串中含有多少个指定的元素
    public int keyCount(String st, String M) {
        int count = 0;
        while (st.indexOf(M) >= 0) {
            st = st.substring(st.indexOf(M) + M.length());
            count++;
        }
        return count;
    }
}
