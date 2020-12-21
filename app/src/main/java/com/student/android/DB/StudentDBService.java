package com.student.android.DB;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

//数据库的具体操作，有mysql语句，采用单例模式
public class StudentDBService {
    private static StudentDBService studentDBService = null;

    private StudentDBService() {
    }

    public static StudentDBService getInstance() {
        if (studentDBService == null) {
            studentDBService = new StudentDBService();
        }
        return studentDBService;
    }

    /**
     * 1.查询所有学生信息
     **/
    public ArrayList<HashMap<String, String>> queryAllStudents(Context context) {
        MusicDBManager musicDBManager = new MusicDBManager(context);
        DBHelper helper = new DBHelper(context);
        String sql = "SELECT * FROM student";
        ArrayList<HashMap<String, String>> queryResult = musicDBManager.querySQLite(sql, null);
        return queryResult;
    }

    /**
     * 2.根据id查询学生
     **/
    public ArrayList<HashMap<String, String>> queryStudentInfoByID(Context context, String studentId) {
        MusicDBManager musicDBManager = new MusicDBManager(context);
        DBHelper helper = new DBHelper(context);
        String sql = "SELECT * FROM student WHERE id=?";
        ArrayList<HashMap<String, String>> queryResult = musicDBManager.querySQLite(sql, new String[]{studentId});
        return queryResult;
    }


    /**
     * 3.更新一个学生信息
     **/
    public void updateStudentByID(Context context,
                                  String id,
                                  String name,
                                  String age,
                                  String gender,
                                  String politics,
                                  String place,
                                  String department) {
        MusicDBManager musicDBManager = new MusicDBManager(context);
        DBHelper helper = new DBHelper(context);
        String sql = "UPDATE student SET name=?,age=?,gender=?,politics=?,place=?,department=? WHERE id=?";
        musicDBManager.updateSQLite(sql, new String[]{
                name,
                age,
                gender,
                politics,
                place,
                department,
                id});
    }


    /**
     * 4.添加一个学生
     **/
    public void addStudent(Context context,
                           String id,
                           String name,
                           String age,
                           String gender,
                           String politics,
                           String place,
                           String department) {
        String sql = "INSERT INTO student(id,name,age,gender,politics,place,department) VALUES(?,?,?,?,?,?,?)";
        MusicDBManager musicDBManager = new MusicDBManager(context);
        DBHelper helper = new DBHelper(context);

        musicDBManager.updateSQLite(sql, new String[]{
                id,
                name,
                age,
                gender,
                politics,
                place,
                department});
    }

    /**
     * 5.删除一个学生
     **/
    public void deleteStudentByID(Context context, String id) {
        MusicDBManager musicDBManager = new MusicDBManager(context);
        DBHelper helper = new DBHelper(context);
        String sql = "delete from student where id=?";
        musicDBManager.updateSQLite(sql, new String[]{id});
    }

}
