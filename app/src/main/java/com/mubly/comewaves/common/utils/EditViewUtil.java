package com.mubly.comewaves.common.utils;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

import com.mubly.comewaves.common.Constant;
import com.mubly.comewaves.model.interfaces.CallBack;


/**
 * Created by PC on 2018/12/15.
 */

public class EditViewUtil {
    public static void EditDatachangeLister(EditText editText, final CallBack callBack) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null == s) {
                    callBack.callBack("");
                } else {
                    callBack.callBack(s.toString());
                }
            }
        });
    }

    public static boolean passwordCheck(String password, int left, int right) {
        if (password.isEmpty()) {
            return false;
        }
        return password.matches("([0-9]+[a-zA-Z]+|[a-zA-Z]+[0-9]+)[0-9a-zA-Z]*") && password.length() > left && password.length() < right;
    }

    public static boolean checkCode(String codeStr) {
        if (TextUtils.isEmpty(codeStr)) {
            return false;
        }
        return codeStr.length()== Constant.CODELENgTH;
    }
}
