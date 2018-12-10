package com.kingold.educationblockchain.dao;


import com.kingold.educationblockchain.bean.Electronicscertificate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ElectronicscertificateMapper {

    /**
     * 根據id查詢证书信息
     */
    @Select("select kg_electronicscertificateid,kg_certificateno,kg_studentprofileid,kg_studentname,kg_sex,to_char(kg_certificatedate,'yyyy-mm-dd') kg_certificatedate,kg_classname,kg_certitype,kg_schoolname,kg_teachername,to_char(kg_starttime,'yyyy-mm-dd') kg_starttime,to_char(kg_endtime,'yyyy-mm-dd') kg_endtime,kg_classstype,kg_explain,kg_name,kg_state from KG_ELECTRONICSCERTIFICATE where kg_electronicscertificateid = #{id} and kg_state = 0")
    Electronicscertificate GetCertificateById(int id);

    /**
     * 根據crmid查詢某个学生的所有证书信息
     */
    @Select("select kg_electronicscertificateid,kg_certificateno,kg_studentprofileid,kg_studentname,kg_sex,to_char(kg_certificatedate,'yyyy-mm-dd') kg_certificatedate,kg_classname,kg_certitype,kg_schoolname,kg_teachername,to_char(kg_starttime,'yyyy-mm-dd') kg_starttime,to_char(kg_endtime,'yyyy-mm-dd') kg_endtime,kg_classstype,kg_explain,kg_name,kg_state from KG_ELECTRONICSCERTIFICATE where kg_studentprofileid=#{studentId} and kg_state = 0")
    List<Electronicscertificate> GetCertificatesByStudentId(int studentId);

    /**
     * 新增电子证书数据
     */
    @Insert("insert into KG_ELECTRONICSCERTIFICATE(kg_certificateno,kg_studentprofileid,kg_studentname,kg_sex,kg_certificatedate,kg_classname,kg_certitype,kg_schoolname,kg_teachername,kg_starttime,kg_endtime,kg_classstype,kg_explain,kg_name,kg_state) values (#{kg_certificateno},#{kg_studentprofileid},#{kg_studentname},#{kg_sex},to_date(#{kg_certificatedate},'yyyy-mm-dd'),#{kg_classname},#{kg_certitype},#{kg_schoolname},#{kg_teachername},to_date(#{kg_starttime},'yyyy-mm-dd'),to_date(#{kg_endtime},'yyyy-mm-dd'),#{kg_classstype},#{kg_explain},#{kg_name},#{kg_state})")
    void AddCertificate(Electronicscertificate electronicscertificate);

    /**
     * 更新电子证书数据
     */
    @Update("update KG_ELECTRONICSCERTIFICATE set kg_certificateno=#{kg_certificateno},kg_studentprofileid=#{kg_studentprofileid},kg_studentname=#{kg_studentname},kg_sex=#{kg_sex},kg_certificatedate=to_date(#{kg_certificatedate},'yyyy-mm-dd'),kg_classname=#{kg_classname},kg_certitype=#{kg_certitype},kg_schoolname=#{kg_schoolname},kg_teachername=#{kg_teachername},kg_starttime=to_date(#{kg_starttime},'yyyy-mm-dd'),kg_endtime=to_date(#{kg_endtime},'yyyy-mm-dd'),kg_classstype=#{kg_classstype},kg_explain=#{kg_explain},kg_name=#{kg_name} where kg_electronicscertificateid=#{kg_electronicscertificateid} and kg_state=0)")
    void UpdateCertificate(Electronicscertificate certificates);

    /**
     * 刪除电子证书数据
     */
    @Update("update KG_ELECTRONICSCERTIFICATE set kg_state=1 where kg_electronicscertificateid=#{id}")
    void DeleteCertificate(int id);
}