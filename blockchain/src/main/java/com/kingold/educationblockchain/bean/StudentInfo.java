package com.kingold.educationblockchain.bean;

public class StudentInfo {
    public int getKg_studentprofileid() {
        return kg_studentprofileid;
    }

    public void setKg_studentprofileid(int kg_studentprofileid) {
        this.kg_studentprofileid = kg_studentprofileid;
    }

    public String getKg_classname() {
        return kg_classname;
    }

    public void setKg_classname(String kg_classname) {
        this.kg_classname = kg_classname;
    }

    public String getKg_studentnumber() {
        return kg_studentnumber;
    }

    public void setKg_studentnumber(String kg_studentnumber) {
        this.kg_studentnumber = kg_studentnumber;
    }

    public String getKg_fullname() {
        return kg_fullname;
    }

    public void setKg_fullname(String kg_fullname) {
        this.kg_fullname = kg_fullname;
    }

    private int kg_studentprofileid;

    private String kg_classname;

    private String kg_studentnumber;

    private String kg_fullname;

    public StudentInfo(){
    }
}
