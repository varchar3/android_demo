package com.example.mora.myproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mora.myproject.com.database.user;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    Button Back = null;
    @BindView(R.id.add_user)
    TextView addUser;
    @BindView(R.id.user_password)
    TextView userPassword;
    @BindView(R.id.password_again)
    TextView passwordAgain;
    @BindView(R.id.username_text)
    EditText usernameText;
    @BindView(R.id.password_text)
    EditText passwordText;
    @BindView(R.id.again_text)
    EditText againText;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.reset)
    Button reset;
    String user_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Back = (Button) findViewById(R.id.Back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @OnClick(R.id.login)
    //注册事件
    public void onViewClicked() {
        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String passwords = againText.getText().toString();
        Connector.getDatabase();    //创建数据库
        //判断两次输入的密码是否一致
        if(password.equals(passwords)){
            //在数据库表中找username判断是否已被存在
            List <user> users=DataSupport.select("username").where("username = ?",username).find(user.class);
            for(user user :users){
                user_check =user.getUsername();
            }
            if(user_check == null){
                user user=new user();
                user.setUsername(username);
                user.setPassword(password);
                user.save();//向数据库表插入数据
                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(RegisterActivity.this,"该用户名已存在",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
        }

    }
}
