package com.mubly.comewaves.model.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mubly.comewaves.R;
import com.mubly.comewaves.view.costomview.SwipeMenuLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {
    private List<String> list;

    public RecAdapter() {
        list = new ArrayList<>();
        list.add("ueyfwoi");
        list.add("ueyfwoi");
        list.add("ueyfwoi");
        list.add("ueyfwoi");
        list.add("ueyfwoi");

        list.add("ueyfwoi");

        list.add("ueyfwoi");


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec_view, null);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(holder.iv_avatar.getContext()).load(R.drawable.ishad_1).into(holder.iv_avatar);
        holder.tv_title.setText(position+"嗯哼");
        holder.tv_subTitle.setText(position+"标题");

        holder.contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(holder.itemView.getContext(), "点击成功", Toast.LENGTH_SHORT).show();
            }
        });
        holder.view_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.tv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.remove(position);
                list.add(0, "3480985");
                notifyDataSetChanged();


            }
        });
        holder.view_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.swipeMenuLayout.collapseSmooth();
                Toast.makeText(holder.itemView.getContext(), "编辑", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_subTitle;
        TextView view_del;
        TextView view_edit;
        TextView tv_top;
        ImageView iv_avatar;
        SwipeMenuLayout swipeMenuLayout;
        View contentView;

        private MyViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.item_tv_title);
            tv_subTitle = (TextView) itemView.findViewById(R.id.item_tv_subTitle);
            tv_top = (TextView) itemView.findViewById(R.id.item_tv_top);
            contentView = itemView.findViewById(R.id.item_content);

            view_del = (TextView) itemView.findViewById(R.id.item_tv_del);
            view_edit = (TextView) itemView.findViewById(R.id.item_tv_edit);
            swipeMenuLayout = (SwipeMenuLayout) itemView.findViewById(R.id.item_layout_swip);
            iv_avatar = (ImageView) itemView.findViewById(R.id.item_avatar);
        }
    }
}
