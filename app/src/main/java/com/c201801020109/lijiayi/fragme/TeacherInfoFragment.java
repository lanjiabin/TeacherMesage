package com.c201801020109.lijiayi.fragme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.c201801020109.lijiayi.LoginActivity;
import com.c201801020109.lijiayi.R;

import static android.content.Context.MODE_PRIVATE;

//显示管理者个人信息
public class TeacherInfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_teacher_info, null);
        TextView idTV = view.findViewById(R.id.id);
        TextView nameTV = view.findViewById(R.id.name);
        TextView registerTimeTV = view.findViewById(R.id.time);
        TextView loginTimeTV = view.findViewById(R.id.login_time);

        final Context context = getActivity().getApplicationContext();
        SharedPreferences share = context.getSharedPreferences("teacher_info", MODE_PRIVATE);
        String teacher_name = share.getString("teacher_name", "w");
        String teacher_pass = share.getString("teacher_user_name", "w");
        String teacher_register_time = share.getString("teacher_register_time", "w");
        String teacher_login_time = share.getString("teacher_login_time", "w");

        idTV.setText(teacher_name);
        nameTV.setText(teacher_pass);
        registerTimeTV.setText(teacher_register_time);
        loginTimeTV.setText(teacher_login_time);

        Button unLogin = view.findViewById(R.id.unLogin);
        unLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences teacherInfo = context.getSharedPreferences("teacher_info", MODE_PRIVATE);
                SharedPreferences.Editor editor = teacherInfo.edit();
                editor.putString("teacher_login_time", "1888-08-08 08:08:08");
                editor.apply();
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
