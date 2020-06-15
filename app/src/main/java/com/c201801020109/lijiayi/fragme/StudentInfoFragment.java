package com.c201801020109.lijiayi.fragme;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.c201801020109.lijiayi.DB.Student;
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
    private EditText mIdEt, mNameEt, mAgeEt, mGenderEt, mPoliticsEt, mPlaceEt, mDepartmentEt;
    private Button mAddBtnEt, mUpdateBtnEt;
    private EditText mIdEtUpdate, mNameEtUpdate, mAgeEtUpdate, mGenderEtUpdate, mPoliticsEtUpdate, mPlaceEtUpdate, mDepartmentEtUpdate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_student_info, null);
        initView();
        onClick();
        return mView;
    }

    public void initView() {
        mContext = getActivity().getApplicationContext();
        mStudentList = StudentDBService.getInstance().queryAllStudents(mContext);
        mStudentListView = mView.findViewById(R.id.student_list);
        mAllStudentBtn = mView.findViewById(R.id.all_student_btn);
        mAddStudentBtn = mView.findViewById(R.id.add_student_btn);
        mOneStudentBtn = mView.findViewById(R.id.one_student_btn);
        mAllStudentFrameLayout = mView.findViewById(R.id.all_student);
        mAddStudentFrameLayout = mView.findViewById(R.id.add_student);
        mOneStudentFrameLayout = mView.findViewById(R.id.one_student);
        setListViewAdapter();
        mIdEt = mView.findViewById(R.id.id_et);
        mNameEt = mView.findViewById(R.id.name_et);
        mAgeEt = mView.findViewById(R.id.age_et);
        mGenderEt = mView.findViewById(R.id.gender_et);
        mPoliticsEt = mView.findViewById(R.id.politics_et);
        mPlaceEt = mView.findViewById(R.id.place_et);
        mDepartmentEt = mView.findViewById(R.id.department_et);

        mAddBtnEt = mView.findViewById(R.id.add_btn_et);

        mIdEtUpdate = mView.findViewById(R.id.id_et_update);
        mNameEtUpdate = mView.findViewById(R.id.name_et_update);
        mAgeEtUpdate = mView.findViewById(R.id.age_et_update);
        mGenderEtUpdate = mView.findViewById(R.id.gender_et_update);
        mPoliticsEtUpdate = mView.findViewById(R.id.politics_et_update);
        mPlaceEtUpdate = mView.findViewById(R.id.place_et_update);
        mDepartmentEtUpdate = mView.findViewById(R.id.department_et_update);

        mUpdateBtnEt = mView.findViewById(R.id.update_btn_et);
    }

    //添加一个学生，录入数据库
    public void addStudent() {
        String id = mIdEt.getText().toString();

        for (int i = 0; i < mStudentList.size(); i++) {
            if (id.equals(mStudentList.get(i).get("id"))) {
                Toast.makeText(mContext, "已经存在相同学号", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String name = mNameEt.getText().toString();
        String age = mAgeEt.getText().toString();
        String gender = mGenderEt.getText().toString();
        String politics = mPoliticsEt.getText().toString();
        String place = mPlaceEt.getText().toString();
        String department = mDepartmentEt.getText().toString();
        if (!id.equals("")
                && !name.equals("")
                && !age.equals("")
                && !gender.equals("")
                && !politics.equals("")
                && !place.equals("")
                && !department.equals("")) {
            StudentDBService.getInstance().addStudent(mContext, id, name, age, gender, politics, place, department);
            Toast.makeText(mContext, "添加学生成功！", Toast.LENGTH_SHORT).show();
            mIdEt.setText("");
            mNameEt.setText("");
            mAgeEt.setText("");
            mGenderEt.setText("");
            mPoliticsEt.setText("");
            mPlaceEt.setText("");
            mDepartmentEt.setText("");
        } else {
            Toast.makeText(mContext, "输入框内容不能有空", Toast.LENGTH_SHORT).show();
        }
    }

    public void setListViewAdapter() {
        mStudentList = StudentDBService.getInstance().queryAllStudents(mContext);
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                mContext,
                mStudentList,
                R.layout.student_info_item,
                new String[]{"id", "name", "age", "gender", "politics", "place", "department"},
                new int[]{R.id.id, R.id.name, R.id.age, R.id.gender, R.id.politics, R.id.place, R.id.department});
        mStudentListView.setAdapter(simpleAdapter);
    }

    public void onClick() {
        mAllStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAllStudentFrameLayout.setVisibility(View.VISIBLE);
                mAddStudentFrameLayout.setVisibility(View.GONE);
                mOneStudentFrameLayout.setVisibility(View.GONE);
                setListViewAdapter();
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

        mAddBtnEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
        mStudentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView textView = view.findViewById(R.id.id);
                String id = textView.getText().toString();
                ArrayList<HashMap<String, String>> studentList = StudentDBService.getInstance().queryStudentInfoByID(mContext, id);
                mIdEtUpdate.setText(studentList.get(0).get("id"));
                mNameEtUpdate.setText(studentList.get(0).get("name"));
                mAgeEtUpdate.setText(studentList.get(0).get("age"));
                mGenderEtUpdate.setText(studentList.get(0).get("gender"));
                mPoliticsEtUpdate.setText(studentList.get(0).get("politics"));
                mPlaceEtUpdate.setText(studentList.get(0).get("place"));
                mDepartmentEtUpdate.setText(studentList.get(0).get("department"));

                mAllStudentFrameLayout.setVisibility(View.GONE);
                mAddStudentFrameLayout.setVisibility(View.GONE);
                mOneStudentFrameLayout.setVisibility(View.VISIBLE);
                mOneStudentBtn.setVisibility(View.VISIBLE);
                mOneStudentBtn.setText(studentList.get(0).get("name"));
            }
        });
        mUpdateBtnEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = ((EditText) mView.findViewById(R.id.id_et_update)).getText().toString();
                String name = ((EditText) mView.findViewById(R.id.name_et_update)).getText().toString();
                String age = ((EditText) mView.findViewById(R.id.age_et_update)).getText().toString();
                String gender = ((EditText) mView.findViewById(R.id.gender_et_update)).getText().toString();
                String politics = ((EditText) mView.findViewById(R.id.politics_et_update)).getText().toString();
                String place = ((EditText) mView.findViewById(R.id.place_et_update)).getText().toString();
                String department = ((EditText) mView.findViewById(R.id.department_et_update)).getText().toString();
                StudentDBService.getInstance().updateStudentByID(mContext, id, name, age, gender, politics, place, department);
                Toast.makeText(mContext, "信息更新成功", Toast.LENGTH_SHORT).show();
            }
        });

        ((Button)mView.findViewById(R.id.delete_btn_et)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("确定删除这个学生信息吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击确定执行的事件
                        String id = ((EditText) mView.findViewById(R.id.id_et_update)).getText().toString();
                        StudentDBService.getInstance().deleteStudentByID(mContext, id);
                        setListViewAdapter();
                        Toast.makeText(mContext, "删除信息成功", Toast.LENGTH_SHORT).show();

                        mAllStudentFrameLayout.setVisibility(View.VISIBLE);
                        mAddStudentFrameLayout.setVisibility(View.GONE);
                        mOneStudentFrameLayout.setVisibility(View.GONE);
                        mOneStudentBtn.setVisibility(View.GONE);

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //点击取消执行的事件
                    }
                });
                builder.create().show();
            }
        });

    }
}
