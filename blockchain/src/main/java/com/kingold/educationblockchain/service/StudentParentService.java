package com.kingold.educationblockchain.service;

import com.kingold.educationblockchain.bean.StudentParent;

import java.util.List;

public interface StudentParentService {

    /**
     * 根据家長信息id查询
     */
    List<StudentParent> FindStudentParentByParentId(int parentId);

    /**
     * 根据学生id查询
     */
    List<StudentParent> FindStudentParentByStudentId(int studentId);

    /**
     * 根据学生id,家長信息id查询
     */
    StudentParent FindStudentParent(int parentId, int studentId);

    /**
     * 學生家長關係新增
     */
    boolean AddStudentParent(StudentParent studentParent);

    /**
     * 學生家長關係刪除
     */
    boolean DeleteStudentParent(int parentId, int studentId);
}
