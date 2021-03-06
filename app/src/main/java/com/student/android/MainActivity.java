package com.student.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.c201801020109.android.R;
import com.student.android.fragme.StudentInfoFragment;
import com.student.android.fragme.TeacherInfoFragment;


public class MainActivity extends AppCompatActivity {
    private Fragment mCurrentFragment;
    private Button mStudentBtn, mInfoBtn;
    private StudentInfoFragment mStudentInfoFragment;
    private TeacherInfoFragment mTeacherInfoFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClick();
        switchFragment(mStudentInfoFragment).commit();
    }

    //加载布局
    public void initView() {
        mCurrentFragment = new Fragment();
        mStudentBtn = findViewById(R.id.student);
        mInfoBtn = findViewById(R.id.info);
        //实例化两个Fragment
        mStudentInfoFragment = new StudentInfoFragment();
        mTeacherInfoFragment = new TeacherInfoFragment();

    }

    //点击事件
    public void onClick() {
        mStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(mStudentInfoFragment).commit();
            }
        });
        mInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(mTeacherInfoFragment).commit();
            }
        });

    }

    //切换Fragment的逻辑
    private FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (mCurrentFragment != null) {
                transaction.hide(mCurrentFragment);
            }
            transaction.add(R.id.fragment, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(mCurrentFragment)
                    .show(targetFragment);
        }
        mCurrentFragment = targetFragment;
        return transaction;
    }
}