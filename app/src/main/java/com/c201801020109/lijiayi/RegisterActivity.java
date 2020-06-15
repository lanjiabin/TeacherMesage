package com.c201801020109.lijiayi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

//注册
public class RegisterActivity extends AppCompatActivity {
    private EditText mRegisterName, mRegisterPass, mRegisterTeacherName;
    private Button mRegister;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    //初始化布局
    public void initView() {
        mContext = getApplicationContext();//得到上下文
        mRegisterName = findViewById(R.id.register_name);//注册用户名
        mRegisterPass = findViewById(R.id.register_pass);//注册密码
        mRegisterTeacherName = findViewById(R.id.register_teacher_name);//注册姓名
        mRegister = findViewById(R.id.register);//注册按钮
        //注册按钮点击事件
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTeacherInfo();
            }
        });
    }

    //注册信息的逻辑
    public void setTeacherInfo() {
        String registerName = mRegisterName.getText().toString();//获得用户输入的用户名
        String registerPass = mRegisterPass.getText().toString();//获得用户输入的密码
        String registerTeacherName = mRegisterTeacherName.getText().toString();//获得用户输入的姓名
        //输入框不能为空，做判断
        if (!registerName.equals("") && !registerPass.equals("") && !registerTeacherName.equals("")) {
            //通过SharedPreferences保存用户注册的信息 MODE_PRIVATE是指其他应用不能获得该文件
            //teacher_info是文件名
            SharedPreferences teacherInfo = getSharedPreferences("teacher_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = teacherInfo.edit();
            editor.putString("teacher_name", registerName);
            editor.putString("teacher_pass", registerPass);
            editor.putString("teacher_user_name", registerTeacherName);
            editor.putString("teacher_register_time", getLocalTime()); //注册时间
            editor.putString("teacher_login_time", "1888-08-08 08:08:08"); //登陆时间
            editor.apply();
            Toast.makeText(mContext, registerName + " 注册成功", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(mContext, "输入框内容不能有空", Toast.LENGTH_SHORT).show();
        }

    }

    //获得本系统的时间
    public String getLocalTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date.getTime());
        return time;
    }
}