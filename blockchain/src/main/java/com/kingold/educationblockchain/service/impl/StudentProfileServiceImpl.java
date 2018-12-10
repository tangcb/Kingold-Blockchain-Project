package com.kingold.educationblockchain.service.impl;

import com.kingold.educationblockchain.bean.StudentProfile;
import com.kingold.educationblockchain.dao.StudentProfileMapper;
import com.kingold.educationblockchain.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentProfileServiceImpl implements StudentProfileService {

    @Autowired
    @Resource
    private StudentProfileMapper mStudentProfileMapper;

    /**
     * 根據id查詢學生信息
     */
    @Override
    public StudentProfile GetStudentProfileById(int id)
    {
        return mStudentProfileMapper.GetStudentProfileById(id);
    }

    /**
     * 根據学籍号或者学号查詢學生信息
     */
    @Override
    public List<StudentProfile> GetStudentProfileByNumber(String eduNumber, String stuNumber){
        return mStudentProfileMapper.GetStudentProfileByNumber(eduNumber,stuNumber);
    }

    /**
     * 學生信息新增
     */
    @Override
    public boolean AddStudentProfile(StudentProfile studentProfile)
    {
        boolean flag = false;
        try{
            mStudentProfileMapper.AddStudentProfile(studentProfile);
            flag = true;
        }catch(Exception e){
            System.out.println("學生信息新增失败!");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 學生信息更新
     */
    @Override
    public boolean UpdateStudentProfile(StudentProfile studentProfile)
    {
        boolean flag = false;
        try{
            mStudentProfileMapper.UpdateStudentProfile(studentProfile);
            flag = true;
        }catch(Exception e){
            System.out.println("學生信息更新失败!");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 學生信息刪除
     */
    @Override
    public boolean DeleteStudentProfile(int id)
    {
        boolean flag = false;
        try{
            mStudentProfileMapper.DeleteStudentProfile(id);
            flag = true;
        }catch(Exception e){
            System.out.println("學生信息刪除失败!");
            e.printStackTrace();
        }
        return flag;
    }
}
