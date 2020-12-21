package com.student.android.fragme;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import com.c201801020109.android.R;
import com.student.android.DB.StudentDBService;

import java.util.ArrayList;
import java.util.HashMap;

//管理学生的Fragment
public class StudentInfoFragment extends Fragment {
    View mView;
    private ArrayList<HashMap<String, String>> mStudentList; //学生的全部信息
    private Context mContext; //上下文
    private ListView mStudentListView; //展示学生的ListView
    private Button mAllStudentBtn, mAddStudentBtn, mOneStudentBtn; //左边的三个按钮
    private FrameLayout mAllStudentFrameLayout, mAddStudentFrameLayout, mOneStudentFrameLayout; //左边的三个按钮对应的三个布局
    private EditText mIdEt, mNameEt, mAgeEt, mGenderEt, mPoliticsEt, mPlaceEt, mDepartmentEt; //添加学生信息
    private Button mAddBtnEt, mUpdateBtnEt; //添加学生按钮  更新学生按钮
    //更新学生信息的EditText
    private EditText mIdEtUpdate, mNameEtUpdate, mAgeEtUpdate, mGenderEtUpdate, mPoliticsEtUpdate, mPlaceEtUpdate, mDepartmentEtUpdate;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = View.inflate(getContext(), R.layout.fragment_student_info, null);
        initView();
        onClick();
        return mView;
    }

    //初始化布局
    public void initView() {
        mContext = getActivity().getApplicationContext();
        //查询全部学生信息
        mStudentList = StudentDBService.getInstance().queryAllStudents(mContext);
        mStudentListView = mView.findViewById(R.id.student_list);
        mAllStudentBtn = mView.findViewById(R.id.all_student_btn);
        mAddStudentBtn = mView.findViewById(R.id.add_student_btn);
        mOneStudentBtn = mView.findViewById(R.id.one_student_btn);
        mAllStudentFrameLayout = mView.findViewById(R.id.all_student);
        mAddStudentFrameLayout = mView.findViewById(R.id.add_student);
        mOneStudentFrameLayout = mView.findViewById(R.id.one_student);
        setListViewAdapter(); //显示学生信息到ListView上
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

    //添加一个学生，录入到数据库
    public void addStudent() {
        String id = mIdEt.getText().toString();

        //学号不能相同
        for (int i = 0; i < mStudentList.size(); i++) {
            if (id.equals(mStudentList.get(i).get("id"))) {
                Toast.makeText(mContext, "已经存在相同学号", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //得到用户输入的信息
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
            //添加信息后，清空输入框信息
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

    //显示数据到ListView上
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

    //所有的点击事件
    public void onClick() {
        //点击一个，就隐藏其它的布局
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

        //添加一个学生按钮
        mAddBtnEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
        //ListView 的item点击事件，把内容显示到另一个位置，并显示该内容
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
        //更新这个学生信息的点击事件
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

        //删除一个学生信息
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
