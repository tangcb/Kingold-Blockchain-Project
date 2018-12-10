package com.kingold.educationblockchain.service;

import com.kingold.educationblockchain.bean.StudentTeacher;

import java.util.List;

public interface StudentTeacherService {

    /**
     * 根据教師信息id查询
     */
    List<StudentTeacher> FindStudentTeacherByTeacherId(int teacherId);

    /**
     * 根据学生id查询
     */
    List<StudentTeacher> FindStudentTeacherByStudentId(int studentId);

    /**
     * 根据学生id,教師信息id查询
     */
    List<StudentTeacher> FindStudentTeacherByPage(int teacherId, int currentPage,int pageSize);

    /**
     * 根据学生id,教師信息id查询
     */
    StudentTeacher FindStudentTeacher(int teacherId, int studentId);

    /**
     * 學生教師關係新增
     */
    boolean AddStudentTeacher(StudentTeacher studentTeacher);

    /**
     * 狀態更新
     */
    boolean DeleteStudentTeacher(int teacherId, int studentId);
}
