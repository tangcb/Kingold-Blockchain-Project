package com.kingold.educationblockchain.bean;

public class StudentTeacher {

    public int getKg_studentprofileid() {
        return kg_studentprofileid;
    }

    public void setKg_studentprofileid(int kg_studentprofileid) {
        this.kg_studentprofileid = kg_studentprofileid;
    }

    public int getKg_teacherinformationid() {
        return kg_teacherinformationid;
    }

    public void setKg_teacherinformationid(int kg_teacherinformationid) {
        this.kg_teacherinformationid = kg_teacherinformationid;
    }

    public int getKg_state() {
        return kg_state;
    }

    public void setKg_state(int kg_state) {
        this.kg_state = kg_state;
    }

    private int kg_studentprofileid;

    private int kg_teacherinformationid;

    private int kg_state;

    public StudentTeacher(){
    }
}
