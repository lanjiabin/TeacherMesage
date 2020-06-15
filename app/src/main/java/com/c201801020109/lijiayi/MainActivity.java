package com.c201801020109.lijiayi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.c201801020109.lijiayi.fragme.StudentInfoFragment;
import com.c201801020109.lijiayi.fragme.TeacherInfoFragment;

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
    }

    public void initView() {
        mCurrentFragment = new Fragment();
        mStudentBtn = findViewById(R.id.student);
        mInfoBtn = findViewById(R.id.info);
        mStudentInfoFragment = new StudentInfoFragment();
        mTeacherInfoFragment = new TeacherInfoFragment();

    }

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