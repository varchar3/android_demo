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
import com.bumptech.glide.Glide;
import com.example.mora.myproject.Listview;
import com.example.mora.myproject.R;
import com.example.mora.myproject.Subpage;

import java.util.List;

/**
 * Created by mora on 2017/8/19.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> implements View.OnClickListener {
    private List <Listview.userBean>mFruitList;
    private Context mContext;

    private OnItemClickListener mOnItemClickListener=null;
    public static interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        CardView cardView;
        public ViewHolder(View view) {
            super(view);
            fruitImage =(ImageView)view.findViewById(R.id.view_image);
            fruitName =(TextView)view.findViewById(R.id.view_name);
            cardView =(CardView) view.findViewById(R.id.card);
        }
    }
    public ListAdapter(Context context,List<Listview.userBean> list){
        mFruitList = list;
        mContext =context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onClick(View v) {
        if(mOnItemClickListener!=null){
            //使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //实例化Listview.userBean类对象
        final  Listview.userBean listview = mFruitList.get(position);
        holder.fruitName.setText(listview.getTitle());
        holder.itemView.setTag(position);
        //把URL转化成图片放入imageview中
        Glide.with(mContext).load(listview.getPhoto()).placeholder(R.mipmap.ic_launcher).into(holder.fruitImage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把数据传给Subpage类
                Intent intent=new Intent(mContext, Subpage.class);
                intent.putExtra("title_image",listview.getPhoto());
                intent.putExtra("sub_title",listview.getSubhead());
                intent.putExtra("News",listview.getNews());
                intent.putExtra("image_1",listview.getImage_1());
                intent.putExtra("News_2",listview.getNews_2());
                intent.putExtra("image_2",listview.getImage_2());
                mContext.startActivity(intent);
            }
        });

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }
    @Override
    public int getItemCount(){
        return mFruitList.size();
    }

}


