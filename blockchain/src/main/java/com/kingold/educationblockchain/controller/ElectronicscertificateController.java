package com.kingold.educationblockchain.controller;

import com.google.gson.Gson;
import com.kingold.educationblockchain.bean.Electronicscertificate;
import com.kingold.educationblockchain.service.ElectronicscertificateService;
import com.kingold.educationblockchain.service.StudentProfileService;
import com.kingold.educationblockchain.util.RetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.kingold.educationblockchain.util.ResultResponse.makeErrRsp;
import static com.kingold.educationblockchain.util.ResultResponse.makeOKRsp;

@Controller
@RequestMapping("/api")
public class ElectronicscertificateController {
    @Autowired
    private ElectronicscertificateService mElectronicscertificateService;

    @Autowired
    private StudentProfileService mStudentProfileService;

    @RequestMapping(value = "/issuecertificate", method = RequestMethod.POST)
    public RetResult IssueCertificate(@RequestBody Electronicscertificate electronicscertificate) {
        Gson gson = new Gson();
        boolean flag = false;
        try{
            if(electronicscertificate.getKg_studentprofileid() > 0){
                if(mStudentProfileService.GetStudentProfileById(electronicscertificate.getKg_studentprofileid()) != null){
                    System.out.println(electronicscertificate.getKg_studentprofileid());
                    flag = mElectronicscertificateService.AddCertificate(electronicscertificate);
                }
            }
            if(flag){
                return makeOKRsp();
            }else{
                return makeErrRsp("添加证书信息失败");
            }
        }catch (Exception ex){
            return makeErrRsp(ex.getMessage());
        }
    }
}