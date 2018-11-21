package com.easyexcel;

import com.alibaba.excel.ExcelWriter;

import java.util.List;

/**
 * 创建 Workbook 并导出 Excel
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/21 14:47
 */
public class EasyExcelWorkbook extends AbstractEasyExcelWorkbook {

    /**
     * 给 sheet 写入内容
     *
     * @param writer
     * @param sheet
     * @author: Andy
     * @time: 2018/11/21 15:29
     */
    @Override
    protected void writeSheet(ExcelWriter writer, com.alibaba.excel.metadata.Sheet sheet) {
        writer.write(getContentList(), sheet);
    }

    /**
     * 填充 Excel 表的内容
     *
     * @description:
     * @param
     * @return: java.util.List<easyexcel.StudentInfo>
     * @author: Andy
     * @time: 2018/11/21 15:29
     */
    private List<StudentInfo> getContentList() {
        return StudentInfoMockData.getContentList();
    }
}
