package com.example.mora.myproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mora.myproject.com.database.user;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    CheckBox remember,automatic;
    private SharedPreferences pref,pref_1,user;
    private SharedPreferences.Editor editor,editor_1,editor_2;
    Button Add = null, Exit = null, Login = null;
    EditText username, key;
    String check_password,check_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        remember=(CheckBox)findViewById(R.id.remember);
        automatic= (CheckBox)findViewById(R.id.automatic);
        Add = (Button) findViewById(R.id.Adduser);
        Exit = (Button) findViewById(R.id.exit);
        Login = (Button) findViewById(R.id.login);
        username = (EditText) findViewById(R.id.username);
        key = (EditText) findViewById(R.id.key);
        //实例化SharedPreferences对象
        pref = getSharedPreferences("Automatic",0);
        user = getSharedPreferences("user",0);
        pref_1=PreferenceManager.getDefaultSharedPreferences(this);

        boolean isRemember =pref.getBoolean("remember_password",false);
        boolean isAutomatic=pref_1.getBoolean("automatic",false);
        //判断是否记住账号密码
        if(isRemember){
            if (isAutomatic){
                String account = pref.getString("account","");
                String password = pref.getString("password","");
                username.setText(account);
                key.setText(password);
                remember.setChecked(true);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, homepage.class);
                MainActivity.this.startActivity(intent);
                finish();
            }else {
                String account = pref.getString("account","");
                String password = pref.getString("password","");
                username.setText(account);
                key.setText(password);
                remember.setChecked(true);
            }

        }
        //登录事件
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account=username.getText().toString();
                String password=key.getText().toString();

                List<user> users= DataSupport.where("username = ?",account).find(user.class);//在数据库取username

                for(user user :users){
                    check_password = user.getPassword();
                    check_account = user.getUsername();
                }
                //判断是否存在该账户
                if(check_account != null){
                    if (password.equals(check_password)) {
                        editor=pref.edit();
                        editor_1=pref_1.edit();
                        editor_2=user.edit();
                        if(remember.isChecked()){
                            if (automatic.isChecked()){//若“记住密码”被选中，把数据存入editor(存放账户信息的SharedPreferences文本)
                                editor.putBoolean("remember_password",true);
                                editor.putString("account",account);
                                editor.putString("password",password);
                                editor_1.putBoolean("automatic",true);
                            }else {
                                editor.putBoolean("remember_password",true);
                                editor.putString("account",account);
                                editor.putString("password",password);
                                editor_1.putBoolean("automatic",false);
                            }

                        }else {
                            if (automatic.isChecked()){
                                editor_1.putBoolean("automatic",true);
                                editor.clear();
                            }else {
                                editor_1.putBoolean("automatic",false);
                                editor.clear();
                            }
                        }
                        editor_2.putString("account",account);
                        editor_2.apply();
                        editor_1.apply();
                        editor.apply();
                        Intent intent=new Intent("0000");
                        sendBroadcast(intent);
                        finish();
                    }else {
                        Toast.makeText(MainActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"该用户不存在",Toast.LENGTH_SHORT).show();
                }


            }
        });
        //跳转至注册页面
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
        //退出应用
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}

