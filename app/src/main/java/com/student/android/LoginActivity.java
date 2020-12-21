package com.student.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.c201801020109.android.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserNameET, mUserPassET;
    private Button mUserLogin;
    private TextView mUserForgetM, mUserRegister;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        onClick();
        autoLogin();
    }

    //布局初始化
    public void initView() {
        mContext = getApplicationContext();
        mUserNameET = findViewById(R.id.user_name);
        mUserPassET = findViewById(R.id.user_pass);
        mUserLogin = findViewById(R.id.user_login);
        mUserForgetM = findViewById(R.id.user_ForgetM);
        mUserRegister = findViewById(R.id.user_register);
    }

    //点击事件
    public void onClick() {
        mUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificationUser();
            }
        });
        mUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //验证用户名和密码
    public void verificationUser() {
        String userName = mUserNameET.getText().toString();//获得用户输入的用户名
        String userPass = mUserPassET.getText().toString();//获得用户输入的密码
        SharedPreferences share = getSharedPreferences("teacher_info", MODE_PRIVATE);
        String registerName = share.getString("teacher_name", "w"); //获得已经注册的用户
        String registerPass = share.getString("teacher_pass", "w"); //获得已经注册是密码
        if (userName.equals("") && userPass.equals("")) {
            Toast.makeText(mContext, "输入框内容不能有空", Toast.LENGTH_SHORT).show();
            return;
        }

        //判断用户名和密码相等则登陆成功
        if (userName.equals(registerName) && userPass.equals(registerPass)) {
            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, MainActivity.class);
            startActivity(intent);
            SharedPreferences teacherInfo = getSharedPreferences("teacher_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = teacherInfo.edit();
            editor.putString("teacher_login_time", getLocalTime()); //更新登陆时间
            editor.apply();
            finish();
        } else {
            Toast.makeText(mContext, "用户名或密码错误", Toast.LENGTH_SHORT).show();
        }


    }

    //七天内自动登录
    public void autoLogin() {
        SharedPreferences share = getSharedPreferences("teacher_info", MODE_PRIVATE);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String registerName = share.getString("teacher_name", "w");
        try {
            Date d1 = df.parse(getLocalTime()); //本地时间
            Date d2 = df.parse(share.getString("teacher_login_time", "w")); //登陆时间
            long diff = d1.getTime() - d2.getTime(); //相差时间
            long days = diff / (1000 * 60 * 60 * 24);
            if (days <= 7) { //不到七天，自动登陆，超过其他，则需要手动登陆
                Intent intent = new Intent(mContext, MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(mContext, registerName + "已经自动登录", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
        }
    }

    //获取本地时间
    public String getLocalTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date.getTime());
        return time;
    }
}