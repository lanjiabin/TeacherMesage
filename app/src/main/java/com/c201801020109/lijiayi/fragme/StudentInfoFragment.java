package com.c201801020109.lijiayi.fragme;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.c201801020109.lijiayi.DB.StudentDBService;
import com.c201801020109.lijiayi.R;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentInfoFragment extends Fragment {
    View mView;
    private ArrayList<HashMap<String, String>> mStudentList;
    private Context mContext;
    private ListView mStudentListView;
    private Button mAllStudentBtn, mAddStudentBtn, mOneStudentBtn;
    private FrameLayout mAllStudentFrameLayout, mAddStudentFrameLayout, mOneStudentFrameLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_student_info, null);
        initView();
        onClick();
        return mView;
    }
    public void initView(){
        mContext=getActivity().getApplicationContext();
        mStudentList = StudentDBService.getInstance().queryAllStudents(mContext);
        mStudentListView=mView.findViewById(R.id.student_list);
        mAllStudentBtn = mView.findViewById(R.id.all_student_btn);
        mAddStudentBtn = mView.findViewById(R.id.add_student_btn);
        mOneStudentBtn = mView.findViewById(R.id.one_student_btn);
        mAllStudentFrameLayout = mView.findViewById(R.id.all_student);
        mAddStudentFrameLayout = mView.findViewById(R.id.add_student);
        mOneStudentFrameLayout = mView.findViewById(R.id.one_student);
        setListViewAdapter();
    }

    public void setListViewAdapter() {
        String[] studentValue = new String[mStudentList.size()];
        for (int i = 0; i < mStudentList.size(); i++) {
            String playlistName = mStudentList.get(i).get("playlistname");
            studentValue[i] = playlistName;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                mContext,
                R.layout.student_info_item,
                R.id.student_id,
                studentValue);
        mStudentListView.setAdapter(adapter);
    }
    public void onClick(){
        mAllStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAllStudentFrameLayout.setVisibility(View.VISIBLE);
                mAddStudentFrameLayout.setVisibility(View.GONE);
                mOneStudentFrameLayout.setVisibility(View.GONE);
            }
        });
        mAddStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAllStudentFrameLayout.setVisibility(View.GONE);
                mAddStudentFrameLayout.setVisibility(View.VISIBLE);
                mOneStudentFrameLayout.setVisibility(View.GONE);
            }
        });
        mOneStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAllStudentFrameLayout.setVisibility(View.GONE);
                mAddStudentFrameLayout.setVisibility(View.GONE);
                mOneStudentFrameLayout.setVisibility(View.VISIBLE);
            }
        });
    }
}
