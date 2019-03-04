package com.mubly.comewaves.common.sharedpreference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.mubly.comewaves.common.CrossApp;

import java.util.Set;


/**
 * Created by android_ls on 2017/1/6.
 */
public class SPHelper {

    private SharedPreferences mSharedPreferences;

    private final int mMode;

    private final String mName;

    public SPHelper(String name, int mode) {
        mName = name;
        mMode = mode;
    }

    public SharedPreferences open() {
        if(mSharedPreferences == null) {
            mSharedPreferences = CrossApp.get().getApplicationContext().getSharedPreferences(mName, mMode);
        }
        return mSharedPreferences;
    }

    /**
     * 清空所有数据
     */
    public void clearAll() {
        open().edit().clear().apply();
    }

    public SharedPreference<Boolean> value(String key, Boolean defaultValue) {
        return new SharedPreference<Boolean>(this, key, defaultValue) {

            @Override
            protected Boolean read(SharedPreferences sp) {
                if (sp.contains(mKey)) {
                    return sp.getBoolean(mKey, false);
                }
                return mDefaultValue;
            }

            @Override
            protected void write(Editor editor, Boolean value) {
                if (value == null) {
                    throw new IllegalArgumentException("null cannot be written for <Boolean>");
                }
                editor.putBoolean(mKey, value);
            }
        };
    }

    public SharedPreference<Integer> value(String key, Integer defaultValue) {
        return new SharedPreference<Integer>(this, key, defaultValue) {

            @Override
            protected Integer read(SharedPreferences sp) {
                if (sp.contains(mKey)) {
                    return sp.getInt(mKey, 0);
                }
                return mDefaultValue;
            }

            @Override
            protected void write(Editor editor, Integer value) {
                if (value == null) {
                    throw new IllegalArgumentException("null cannot be written for <Integer>");
                }
                editor.putInt(mKey, value);
            }

        };
    }

    public SharedPreference<Long> value(String key, Long defaultValue) {
        return new SharedPreference<Long>(this, key, defaultValue) {

            @Override
            protected Long read(SharedPreferences sp) {
                if (sp.contains(mKey)) {
                    return sp.getLong(mKey, 0);
                }
                return mDefaultValue;
            }

            @Override
            protected void write(Editor editor, Long value) {
                if (value == null) {
                    throw new IllegalArgumentException("null cannot be written for <Long>");
                }
                editor.putLong(mKey, value);
            }
        };
    }

    public SharedPreference<String> value(String key, String defaultValue) {
        return new SharedPreference<String>(this, key, defaultValue) {

            @Override
            protected String read(SharedPreferences sp) {
                if (sp.contains(mKey)) {
                    return sp.getString(mKey, null);
                }
                return mDefaultValue;
            }

            @Override
            protected void write(Editor editor, String value) {
                editor.putString(mKey, value);
            }
        };
    }

    public SharedPreference<Set<String>> value(String key, Set<String> defaultValue) {
        return new SharedPreference<Set<String>>(this, key, defaultValue) {

            @Override
            protected Set<String> read(SharedPreferences sp) {
                if (sp.contains(mKey)) {
                    return sp.getStringSet(mKey, null);
                }
                return mDefaultValue;
            }

            @Override
            protected void write(Editor editor, Set<String> value) {
                editor.putStringSet(mKey, value);
            }
        };
    }

}
