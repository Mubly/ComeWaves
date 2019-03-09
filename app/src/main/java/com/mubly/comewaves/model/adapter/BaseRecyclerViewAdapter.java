package com.mubly.comewaves.model.adapter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spanned;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mubly.comewaves.R;
import com.mubly.comewaves.common.utils.CommUtil;
import com.mubly.comewaves.common.utils.ImagelodersUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.VH> {

    private List<T> mDatas = new ArrayList<T>();

    public BaseRecyclerViewAdapter(List<T> data) {
        this.mDatas = data;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    public abstract int getLayoutId(int viewType);

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        convert(vh, mDatas.get(position), position);
    }

    public abstract void convert(VH holder, T data, int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);\
        return getItemViewTypes(position, mDatas);
    }

    public abstract int getItemViewTypes(int poition, List<T> data);

    public static class VH extends RecyclerView.ViewHolder {
        private SparseArray<View> mViews;
        private View mConvertView;

        private VH(View v) {
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }

        public static VH get(ViewGroup parent, int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public void setText(int id, String value) {
            TextView view = getView(id);
            view.setText(value);
        }

        public void setTextColor(int id, int value) {
            TextView view = getView(id);
            view.setTextColor(value);
        }


        public void setText(Context context, int id, int value) {
            TextView view = getView(id);
            view.setText(CommUtil.getStringToStringXml(context, value));
        }

        public void setTextSpanned(Context context, int id, Spanned value) {
            TextView view = getView(id);
            view.setText(value);
        }

        public void setEtText(Context context, int id, int value) {
            EditText view = getView(id);
            view.setText(CommUtil.getStringToStringXml(context, value));
        }

        public void setEtHintText(Context context, int id, int value) {
            EditText view = getView(id);
            view.setHint(CommUtil.getStringToStringXml(context, value));
        }

        public void setImage(int id, int value) {
            ImageView view = getView(id);
            view.setImageResource(value);
        }

        public void setNetImage(Context context, int id, String value) {
            ImageView view = getView(id);
            ImagelodersUtils.glideLoadImage(context, value, view, 10, R.mipmap.ic_launcher);
        }

        public void setViewShowHide(int id, boolean isShowHide) {
            View view = getView(id);
            if (view == null) return;
            view.setVisibility(isShowHide ? View.VISIBLE : View.GONE);
        }

        public void setImageBg(int id, int bgId) {
            View view = getView(id);
            view.setBackgroundResource(bgId);
        }

        public void setImageBgColor(int id, int bgId) {
            View view = getView(id);
            view.setBackgroundColor(bgId);
        }

        public void setViewEnable(int id, boolean isEnable) {
            View view = getView(id);
            view.setEnabled(isEnable);
        }

        public void setConvertViewEnable(boolean isEnable) {
            View view = getConvertView();
            view.setEnabled(isEnable);
        }


        public void setTextViewLeftDrawable(Context context, int id, int mipmapId) {
            TextView view = getView(id);
            CommUtil.setLeftCompoundDrawables(context, view, mipmapId);
        }

        public View getConvertView() {
            return mConvertView;
        }

        public View getChildView(int viewId) {
            return getView(viewId);
        }

        public EditText getEditText(int id) {
            return getView(id);
        }
    }

    public void onItemDataChange(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(mDatas, fromPosition, toPosition);
    }

    public void onItemdelete(int position) {
        mDatas.remove(position);
    }
}

