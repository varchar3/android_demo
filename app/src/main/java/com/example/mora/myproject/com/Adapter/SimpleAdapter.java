package com.example.mora.myproject.com.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mora.myproject.Game;
import com.example.mora.myproject.Headline;
import com.example.mora.myproject.Science;

/**
 * Created by mora on 2017/8/19.
 */

public class SimpleAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"头条","游戏","科技"};

    public SimpleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    //fragment切换跳转
    public Fragment getItem(int position) {
        if (position == 1) {
            return new Game();
        } else if (position == 2) {
            return new Science();
        }
        return new Headline();
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