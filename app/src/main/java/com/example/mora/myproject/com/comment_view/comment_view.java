package com.example.mora.myproject.com.comment_view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mora.myproject.Listview;
import com.example.mora.myproject.MainActivity;
import com.example.mora.myproject.R;
import com.example.mora.myproject.RegisterActivity;
import com.example.mora.myproject.com.Adapter.comment;
import com.example.mora.myproject.com.database.comment_db;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class comment_view extends android.support.v4.app.Fragment {
    RecyclerView comment_recyclerView;
    EditText comment;
    List<comment_db> mList;
    Button publish;
    comment comment_Adapter;
    String title;
    SwipeRefreshLayout mRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.comment_view, container, false);

        comment_recyclerView=(RecyclerView)view.findViewById(R.id.comment_recyclerview);
        publish=(Button) view.findViewById(R.id.publish);
        comment=(EditText) view.findViewById(R.id.comment_text);
        title=getActivity().getIntent().getStringExtra("sub_title");//从ListAdapter获取数据
        final SwipeRefreshLayout layout_swipe_refresh=(SwipeRefreshLayout)view.findViewById(R.id.layout_swipe_refresh);

        initview();
        comment_Adapter= new comment(getActivity(),title,mList);
        //实例化SharedPreferences
        final SharedPreferences username=getActivity().getSharedPreferences("user",0);
        comment_recyclerView.setAdapter(comment_Adapter);
        LinearLayoutManager linearLayout=new LinearLayoutManager(this.getActivity());
        comment_recyclerView.setLayoutManager(linearLayout);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                String comment_text=comment.getText().toString();
                String user=username.getString("account","");
                if(user.equals("")){
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    getActivity().startActivity(intent);
                    Toast.makeText(getActivity(),"登录后才能进行评论",Toast.LENGTH_LONG).show();
                }else {
                    if(comment_text.equals("")){
                        Toast.makeText(getActivity(),"评论不能为空",Toast.LENGTH_SHORT).show();
                    }else{
                        //从ListAdapter获取数据放入数据库中
                        comment_db comment_db=new comment_db();
                        comment_db.setUsername(user);
                        comment_db.setComment(comment_text);
                        comment_db.setTitle(title);
                        comment_db.save();
                        comment.setText("");
                        Toast.makeText(getActivity(),"发布成功，请下拉刷新",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        layout_swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initview();
                comment_Adapter= new comment(getActivity(),title,mList);
                comment_recyclerView.setAdapter(comment_Adapter);
                comment_Adapter.notifyDataSetChanged();
                layout_swipe_refresh.setRefreshing(false);
            }
        });
        return view;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void initview(){
//        从数据库获取数据
        mList= DataSupport.select("username","comment").where("title = ?",title).find(comment_db.class);
    }

}

