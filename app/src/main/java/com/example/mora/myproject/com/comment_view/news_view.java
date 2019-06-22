package com.example.mora.myproject.com.comment_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mora.myproject.R;
import com.example.mora.myproject.Subpage;

public class news_view extends Fragment {
    ImageView title_image,main_image_1,main_image_2;
    TextView sub_title,news,news_2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_layout, container, false);
        title_image =(ImageView) view.findViewById(R.id.sub_image);
        main_image_1 =(ImageView)view.findViewById(R.id.main_image_1);
        main_image_2 =(ImageView)view.findViewById(R.id.main_image_2);
        sub_title=(TextView)view.findViewById(R.id.sub_title);
        news=(TextView)view.findViewById(R.id.text_main_1);
        news_2=(TextView)view.findViewById(R.id.text_main_2);
        Glide.with(getActivity()).load(getActivity().getIntent().getStringExtra("title_image")).placeholder(R.mipmap.ic_launcher).into(title_image);
        Glide.with(getActivity()).load(getActivity().getIntent().getStringExtra("image_1")).into(main_image_1);
        Glide.with(getActivity()).load(getActivity().getIntent().getStringExtra("image_2")).into(main_image_2);
        sub_title.setText(getActivity().getIntent().getStringExtra("sub_title"));
        news.setText(getActivity().getIntent().getStringExtra("News"));
        news_2.setText(getActivity().getIntent().getStringExtra("News_2"));
        news_2.setText(getActivity().getIntent().getStringExtra("News_2"));
        return view;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
