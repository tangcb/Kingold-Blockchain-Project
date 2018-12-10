package com.kingold.educationblockchain.controller;

import com.google.gson.Gson;
import com.kingold.educationblockchain.bean.*;
import com.kingold.educationblockchain.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentProfileController {
    @Autowired
    private StudentProfileService mStudentProfileService;
    @Autowired
    private ParentInformationService mParentInfomationService;
    @Autowired
    private TeacherInformationService mTeacherInfomationService;
    @Autowired
    private StudentParentService mStudentParentService;
    @Autowired
    private StudentTeacherService mStudentTeacherService;
    private Gson gson;

    @RequestMapping(value = "/studentprofile", method = RequestMethod.GET)
    public String GetStudentProfileById(@RequestParam(value = "id", required = true) int id) {
        gson = new Gson();
        StudentProfile studentProfile = mStudentProfileService.GetStudentProfileById(id);
        return gson.toJson(studentProfile);
    }

    @RequestMapping(value = "/studentinfo", method = RequestMethod.GET)
    public String GetStudentProfile(@RequestParam(value = "phonenumber", required = true)String phonenumber, @RequestParam(value = "role", required = true)int role) {
        gson = new Gson();
        StudentProfile studentProfile;
        //role 為1，代表家長，為2，代表教師
        if (role == 1) {
            ParentInformation parentInformation = mParentInfomationService.FindParentInformationByPhone(phonenumber);
            if (parentInformation == null) {
                return gson.toJson(new StudentProfile());
            } else {
                System.out.println(parentInformation.getKg_parentinformationid());
                List<StudentParent> studentParents = mStudentParentService.FindStudentParentByParentId(parentInformation.getKg_parentinformationid());
                if (studentParents.size() <= 0) {
                    return gson.toJson(new StudentProfile());
                } else {
                    if (studentParents.size() == 1) {
                        studentProfile = mStudentProfileService.GetStudentProfileById(studentParents.get(0).getKg_studentprofileid());
                        return gson.toJson(studentProfile);
                    } else {
                        List<StudentProfile> StudentProfileList = new ArrayList<>();
                        for (StudentParent sp : studentParents) {
                            StudentProfileList.add(mStudentProfileService.GetStudentProfileById(sp.getKg_studentprofileid()));
                        }
                        return gson.toJson(StudentProfileList);
                    }
                }
            }
        } else {
            //教師身份
            TeacherInformation teacherInformation = mTeacherInfomationService.FindTeacherInformationByPhone(phonenumber);
            if (teacherInformation == null) {
                return gson.toJson(new StudentProfile());
            } else {
                // 分页
                // List<StudentTeacher> studentTeachers = mStudentTeacherService.FindStudentTeacherByPage(teacherInformation.getKg_teacherinformationid(),1,10);
                // 不分页
                List<StudentTeacher> studentTeachers = mStudentTeacherService.FindStudentTeacherByTeacherId(teacherInformation.getKg_teacherinformationid());
                return GetStudentList(studentTeachers);
            }
        }
    }

    @RequestMapping(value = "/studentinfolist", method = RequestMethod.GET)
    public String GetStudentProfilebyPage(@RequestParam(value = "phonenumber", required = true)String phonenumber, @RequestParam(value = "currentPage", required = true)int currentPage, @RequestParam(value = "pageSize", required = true)int pageSize){
        gson = new Gson();
        StudentProfile studentProfile;
        if(phonenumber.trim().length() == 0){
            return gson.toJson(new StudentProfile());
        }
        TeacherInformation teacherInformation = mTeacherInfomationService.FindTeacherInformationByPhone(phonenumber);
        if (teacherInformation == null) {
            return gson.toJson(new StudentProfile());
        } else {
            List<StudentTeacher> studentTeachers = mStudentTeacherService.FindStudentTeacherByPage(teacherInformation.getKg_teacherinformationid(),currentPage,pageSize);
            return GetStudentList(studentTeachers);
        }
    }

    @RequestMapping(value = "/addstudentprofile", method = RequestMethod.POST)
    public String AddStudentProfile(@RequestBody StudentProfile studentProfile) {
        gson = new Gson();
        boolean flag = mStudentProfileService.AddStudentProfile(studentProfile);
        return gson.toJson(flag);
    }

    @RequestMapping(value = "/updatestudentprofile", method = RequestMethod.PUT)
    public String UpdateStudentProfile(@RequestBody StudentProfile studentProfile) {
        gson = new Gson();
        boolean flag = mStudentProfileService.UpdateStudentProfile(studentProfile);
        return gson.toJson(flag);
    }

    @RequestMapping(value = "/deletestudentprofile", method = RequestMethod.DELETE)
    public String DeleteStudentProfile(@RequestParam(value = "id", required = true) int id) {
        gson = new Gson();
        boolean flag = mStudentProfileService.DeleteStudentProfile(id);
        return gson.toJson(flag);
    }

    public String GetStudentList(List<StudentTeacher> list){
        gson = new Gson();
        if (list.size() <= 0) {
            return gson.toJson(new StudentProfile());
        } else {
            List<StudentInfo> StudentInfoList = new ArrayList<>();
            for (StudentTeacher st : list) {
                StudentProfile  profile = mStudentProfileService.GetStudentProfileById(st.getKg_studentprofileid());
                StudentInfo info = new StudentInfo();
                info.setKg_studentprofileid(profile.getKg_studentprofileid());
                info.setKg_classname(profile.getKg_classname());
                info.setKg_fullname(profile.getKg_fullname());
                info.setKg_studentnumber(profile.getKg_studentnumber());
                StudentInfoList.add(info);
            }
            return gson.toJson(StudentInfoList);
        }
    }
}