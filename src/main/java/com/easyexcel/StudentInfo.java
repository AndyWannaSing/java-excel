package com.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

public class StudentInfo extends BaseRowModel {
    @ExcelProperty(value ="序号" ,index = 0)
    private String number;

    @ExcelProperty(value ="班级" ,index = 1)
    private String className;

    @ExcelProperty(value ="姓名" ,index = 2)
    private String name;

    @ExcelProperty(value ="年龄" ,index = 3)
    private String age;

    @ExcelProperty(value ="性别" ,index = 4)
    private String gender;

    @ExcelProperty(value ="专业" ,index = 5)
    private String major;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}