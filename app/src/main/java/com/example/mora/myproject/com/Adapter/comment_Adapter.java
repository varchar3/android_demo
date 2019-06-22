package com.example.mora.myproject.com.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mora.myproject.Game;
import com.example.mora.myproject.Headline;
import com.example.mora.myproject.Science;
import com.example.mora.myproject.com.comment_view.comment_view;
import com.example.mora.myproject.com.comment_view.news_view;

public class comment_Adapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"新闻","评论"};

    public comment_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new comment_view();
        } else  {
            return new news_view();
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }


}
