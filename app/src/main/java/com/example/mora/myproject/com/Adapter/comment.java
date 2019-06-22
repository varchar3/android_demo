package com.example.mora.myproject.com.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mora.myproject.Listview;
import com.example.mora.myproject.R;
import com.example.mora.myproject.RegisterActivity;
import com.example.mora.myproject.Subpage;
import com.example.mora.myproject.com.database.comment_db;

import org.litepal.crud.DataSupport;

import java.util.List;

public class comment extends RecyclerView.Adapter<comment.ViewHolder>{
    private List <comment_db>mList;
    private Context mContext;
    private String title;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,floor,comment_text;

        public ViewHolder(View view) {
            super(view);
            username =(TextView)view.findViewById(R.id.username);
            floor =(TextView)view.findViewById(R.id.floor);
            comment_text =(TextView)view.findViewById(R.id.comment_text);
        }
    }
    public comment(Context context,String title,List<comment_db> list){
        mContext =context;
        mList=list;
        mList=list;
        this.title=title;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final comment_db list=mList.get(position);
        holder.floor.setText(position+1+"楼");
        holder.username.setText(list.getUsername());
        holder.comment_text.setText(list.getComment());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
