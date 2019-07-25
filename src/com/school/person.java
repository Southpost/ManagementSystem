package com.school;

import java.io.Serializable;

class person implements Serializable {

    private String name;       //姓名
    private String number;     //编号
    private char sex;          //性别
    public Date birthday;       //出生年月
    private String speciality;  //专业
    private String researchTopic;   //课题
    private String academicTitle;   //职称
    private int type;
    //类别

    public person(String name, String number, char sex, int y,int m,int d,String speciality, String researchTopic, String academicTitle, int type) {
        this.name = name;
        this.number = number;
        this.sex = sex;
        birthday=new Date();     //访问之前要初始化这个对象
        birthday.Set(y,m,d);   //Todo
        this.speciality = speciality;
        this.researchTopic = researchTopic;
        this.academicTitle = academicTitle;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public char getSex() {
        return sex;
    }

    public Date getBirth() {        //TODO
        return birthday;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public String getAcademicTopic() {
        return academicTitle;
    }

    public int getType() {
        return type;
    }

}
