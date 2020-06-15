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

    public void initView() {
        mContext = getApplicationContext();
        mRegisterName = findViewById(R.id.register_name);
        mRegisterPass = findViewById(R.id.register_pass);
        mRegisterTeacherName = findViewById(R.id.register_teacher_name);
        mRegister = findViewById(R.id.register);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setTeacherInfo();
            }
        });
    }

    public void setTeacherInfo() {
        String registerName = mRegisterName.getText().toString();
        String registerPass = mRegisterPass.getText().toString();
        String registerTeacherName = mRegisterTeacherName.getText().toString();
        if (!registerName.equals("") && !registerPass.equals("") && !registerTeacherName.equals("")) {
            SharedPreferences teacherInfo = getSharedPreferences("teacher_info", MODE_PRIVATE);
            SharedPreferences.Editor editor = teacherInfo.edit();
            editor.putString("teacher_name", registerName);
            editor.putString("teacher_pass", registerPass);
            editor.putString("teacher_user_name", registerTeacherName);
            editor.putString("teacher_register_time", getLocalTime());
            editor.putString("teacher_login_time", "1888-08-08 08:08:08");
            editor.apply();
            Toast.makeText(mContext, registerName + " 注册成功", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(mContext, "输入框内容不能有空", Toast.LENGTH_SHORT).show();
        }

    }

    public String getLocalTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(date.getTime());
        return time;
    }
}