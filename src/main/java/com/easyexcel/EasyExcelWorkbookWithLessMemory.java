package com.easyexcel;

import com.alibaba.excel.ExcelWriter;

import java.util.List;

/**
 * 以更少内存的方式创建 Workbook 并导出 Excel
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/21 15:31
 */
public class EasyExcelWorkbookWithLessMemory  extends AbstractEasyExcelWorkbook {
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
        for (int i = 0; i < 100; i++) {
            writer.write(getSubContentList(i), sheet);
        }

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
    private List<StudentInfo> getSubContentList(int i) {
        return StudentInfoMockData.getSubContentList(i);
    }
}
