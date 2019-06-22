package com.example.mora.myproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mora.myproject.com.Adapter.ListAdapter;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mora on 2017/8/19.
 */
public class Science extends Fragment {

    @BindView(R.id.science_view)
    RecyclerView sciencelineView;
    Unbinder unbinder;
    List<Listview.userBean> userBeanList;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_science, container, false);
        unbinder = ButterKnife.bind(this, view);
        //调用sendRequsetWithOKHttp方法
        sendRequsetWithOKHttp();
        LinearLayoutManager linearLayout=new LinearLayoutManager(this.getActivity());
        sciencelineView.setLayoutManager(linearLayout);
        return view;
    }
    private void sendRequsetWithOKHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //耗时操作放在新线程
                    InputStreamReader isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("assets/" + "base.json"), "utf-8");
                    //从assets获取json文件
                    BufferedReader bfr = new BufferedReader(isr);//字节流转字符流
                    String line ;
                    StringBuilder stringBuilder = new StringBuilder();
                    while ((line = bfr.readLine())!=null){
                        stringBuilder.append(line);
                    }//将JSON数据转化为字符串
                    String responseData = new String(stringBuilder.toString());
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        }).start();
    }
    private  void showResponse(final String response){
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //实例化Listview类对象,把从json文件解析出的数据存入
                Listview resultBean = new Gson().fromJson(response, Listview.class);
                //把数据放入list数组里
                userBeanList = resultBean.getContent();
                //实例化适配器对象
                ListAdapter madapter=new ListAdapter(getActivity(),userBeanList);
                sciencelineView.setAdapter(madapter);
            }
        });
    }
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
