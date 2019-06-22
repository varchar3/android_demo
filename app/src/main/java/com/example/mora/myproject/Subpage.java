package com.example.mora.myproject;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mora.myproject.com.Adapter.comment_Adapter;


public class Subpage extends AppCompatActivity {
    private comment_Adapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private  String[]data={"1","2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subpage);
        initViews();
    }
    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        viewPager= (ViewPager) findViewById(R.id.viewpager_2);
        pagerAdapter= new comment_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        tabLayout = (TabLayout) findViewById(R.id.news_tabs);
        tabLayout.setupWithViewPager(viewPager);
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
    }

}
