package com.easyexcel;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建 StudentInfo 相关的模拟数据
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/21 15:11
 */
public class StudentInfoMockData {

    /**
     * 生成 6 万条模拟数据
     *
     * @description:
     * @param
     * @return: java.util.List<easyexcel.StudentInfo>
     * @author: Andy
     * @time: 2018/11/21 15:13
     */
    public static List<StudentInfo> getContentList(){
        List<StudentInfo> contentList = new ArrayList<>();
        for(int i = 0; i < 60000; i++){
            contentList.add(getMockStudentInfo(i));
        }
        return contentList;
    }
    /**
     * 分 100 次获取 6 万条记录
     * 即每次获取 600 条记录
     *
     * index 从 0 开始
     *
     * @description:
     * @param index
     * @return: java.util.List<easyexcel.StudentInfo>
     * @author: Andy
     * @time: 2018/11/21 15:25
     */
    public static List<StudentInfo> getSubContentList(int index) {
        List<StudentInfo> subContentList = new ArrayList<>();
        int startIndex = index*600;
        for(int i = 0; i < 600; i++){
            subContentList.add(getMockStudentInfo(startIndex + i));
        }
        return subContentList;
    }


    /**
     * 生成第 i 个学生信息
     *
     * @description:
     * @param i
     * @return: easyexcel.StudentInfo
     * @author: Andy
     * @time: 2018/11/21 15:16
     */
    private static StudentInfo getMockStudentInfo(int i) {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setNumber("序号-" + i);
        studentInfo.setClassName("班级-" + i);
        studentInfo.setName("姓名-" + i);
        studentInfo.setAge("20");
        studentInfo.setGender("男");
        studentInfo.setMajor("计算机");
        return studentInfo;
    }
}
