package com.example.mora.myproject;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mora.myproject.com.Adapter.SimpleAdapter;

public class homepage extends AppCompatActivity {

    private SimpleAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private  String[]data={"1","2","3"};
    private DrawerLayout mDrawer;
    private BroadcastReceiver broadcastReceiver;
    TextView name;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //调用menu中菜单
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.help:
                Toast.makeText(this,"you clicked Help",Toast.LENGTH_SHORT).show();
                break;
            case R.id.imformation:
                Toast.makeText(this,"you clicked Information",Toast.LENGTH_SHORT).show();
                break;
            case R.id.upload:
                Toast.makeText(this,"you clicked Upload",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initViews();

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer =(DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navView=(NavigationView)findViewById(R.id.view);//侧滑菜单
        View view=navView.inflateHeaderView(R.layout.header);
        name=(TextView)view.findViewById(R.id.name);

        aa();
        //侧滑菜单点击显示事件
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.detail);//设置按钮图片
        }

        navView.setCheckedItem(R.id.PersonageInformation);
//        侧滑菜单点击事件
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                //“登录/注销”点击事件
                if(id ==R.id.change){
                    String username;
                    SharedPreferences sp = getSharedPreferences("Automatic",0);//实例化SharedPreferences
                    SharedPreferences.Editor editor = sp.edit();
                    username=sp.getString("account","");
                    //判断是否登录
                    if(username.equals("")){
                        Toast.makeText(homepage.this,"还未登陆",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(homepage.this, MainActivity.class);
                        homepage.this.startActivity(intent);
                    }else{
                        editor.clear();
                        editor.commit();
                        Toast.makeText(homepage.this,"注销成功",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(homepage.this, MainActivity.class);
                        homepage.this.startActivity(intent);
                    }
                }
                return true;
            }
        });
    }
    private void aa(){
        final SharedPreferences username=getSharedPreferences("user",0);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("0000");
        broadcastReceiver=new BroadcastReceiver(){
            public void onReceive(Context context, Intent intent) {
                name.setText(username.getString("account",""));
            }
        };
        registerReceiver(broadcastReceiver,intentFilter);
    }
    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter= new SimpleAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
    }
}
