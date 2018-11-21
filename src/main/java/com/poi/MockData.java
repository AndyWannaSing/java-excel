package com.poi;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟数据对象
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/20 15:11
 */
public class MockData {

    /**
     * 生成 6 万条记录
     *
     * @description:
     * @param
     * @return: java.util.List<java.util.List<java.lang.String>>
     * @author: Andy
     * @time: 2018/11/20 15:22
     */
    public static List<List<String>> getMockContentList() {
        List<List<String>> contentList = new ArrayList<>();
        for(int i = 0; i < 60000; i++){
            contentList.add(getMockCellListOfRow(i));
        }
        return contentList;
    }


    /**
     * 获得指定行的模拟数据
     *
     * @description:
     * @param index
     * @return: java.util.List<java.lang.String>
     * @author: Andy
     * @time: 2018/11/20 15:25
     */
    public static List<String> getMockCellListOfRow(int index) {
        List<String> cellList = new ArrayList<>();
        cellList.add("序号-" + index);
        cellList.add("班级-" + index);
        cellList.add("姓名-" + index);
        cellList.add("20");
        cellList.add("男");
        cellList.add("计算机");
        return cellList;
    }


    /**
     * 生成表头信息
     *
     * @description:
     * @param
     * @return: java.util.List<java.lang.String>
     * @author: Andy
     * @time: 2018/11/20 15:21
     */
    public static List<String> getMockHeadList() {
        List<String> headList = new ArrayList<>();
        headList.add("序号");
        headList.add("班级");
        headList.add("姓名");
        headList.add("年龄");
        headList.add("性别");
        headList.add("专业");
        return headList;
    }

    /**
     * 分 100 次获取 6 万条记录
     * 即每次获取 600 条记录
     *
     * index 从 0 开始
     *
     * @description:
     * @param index
     * @return: java.util.List<java.util.List<java.lang.String>>
     * @author: Andy
     * @time: 2018/11/20 15:31
     */
    public static List<List<String>> getMockSubContentList(int index) {
        List<List<String>> subContentList = new ArrayList<>();
        int startIndex = index*600;
        for(int i = 0; i < 600; i++){
            subContentList.add(getMockCellListOfRow(startIndex + i));
        }
        return subContentList;
    }

}
