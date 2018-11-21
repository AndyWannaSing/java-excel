package com.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.*;

import java.util.List;

/**
 * 抽象 Workbook 构建对象
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/19 17:59
 */
public abstract class WorkbookBuilder {

    /**
     * 创建 Workbook
     *
     * @description:
     * @param
     * @return: org.apache.poi.ss.usermodel.Workbook
     * @author: Andy
     * @time: 2018/11/20 14:56
     */
    public Workbook createWorkbook(){
        Workbook wb = getWorkbook();
        Sheet sheet = wb.createSheet();

        // 创建 Excel 表内容
        buildSheetHead(sheet, getHeadList(), getDefaultHeadCellStyle(wb));

        //创建 Excel 表内容
        buildSheetContent(sheet, getContentList(), getDefaultContentCellStyle(wb));

        return wb;
    }


    /**
     * 以更节省内存的方式创建 Workbook
     *
     * @description:
     * @param
     * @return: org.apache.poi.ss.usermodel.Workbook
     * @author: Andy
     * @time: 2018/11/20 14:57
     */
    public Workbook createWorkbookWithLessMemory(){
        Workbook wb = getWorkbook();
        Sheet sheet = wb.createSheet();

        // 创建 Excel 表内容
        buildSheetHead(sheet, getHeadList(), getDefaultHeadCellStyle(wb));

        //以更节省内存的方式创建表内容
        buildSheetContentWithLessMemory(sheet, getDefaultContentCellStyle(wb));

        return wb;
    }


    /**
     * 创建 Workbook
     *
     * @description:
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    protected abstract Workbook getWorkbook();

    /**
     * 获取 Excel 表内容列表
     *
     * @description:
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    protected abstract  List<List<String>> getContentList();

    /**
     * 获取 Excel 表头信息列表
     *
     * @description:
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    protected abstract List<String> getHeadList();


    /**
     * 创建 Excel 表头
     *
     * @description:
     * @param sheet
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    private void buildSheetHead(Sheet sheet, List<String> headList, CellStyle headCellStyle){
        createRow(sheet, headCellStyle, headList, 0);
    }

    /**
     *  创建表内容
     *
     * @description:
     * @param sheet
     * @param contentList
     * @param contentCellStyle
     * @return: void
     * @author: Andy
     * @time: 2018/11/20 10:38
     */
    private void buildSheetContent(Sheet sheet, List<List<String>> contentList, CellStyle contentCellStyle){
        if (!checkIfSheetHeadExist(sheet)) {
            return;
        }

        createRows(sheet, contentCellStyle, sheet.getLastRowNum(), contentList);
    }


    /**
     * 以更节省内存的方式创建表内容
     *
     * @description:
     * @param sheet
     * @param contentCellStyle
     * @return: void
     * @author: Andy
     * @time: 2018/11/20 11:43
     */
    private void buildSheetContentWithLessMemory(Sheet sheet, CellStyle contentCellStyle) {
        if (!checkIfSheetHeadExist(sheet)) {
            return;
        }

        int subContentListCount = subContentListCount();
        for (int index = 0; index < subContentListCount; index++) {
            List<List<String>> subContentList = getSubContentList(index);
            int lastRowNum = sheet.getLastRowNum();
            createRows(sheet, contentCellStyle, lastRowNum, subContentList);
        }

    }

    /**
     * 以更节省内存的方式创建表内容时，根据批次号获取数据列表的方法
     *
     *
     *
     * @param index
     * @return
     * @author: Andy
     */
    protected abstract List<List<String>> getSubContentList(int index);


    /**
     * 子列表数量。
     * 假设子列表数量为 n， 则在导出 Excel 表时，分 n 次从数据源获取数据，
     * 一批一批地把数据写入到 WorkBook 中。
     *
     * 这样做是为了以更节省内存的方式创建表内容
     *
     * 默认分批数量是 100。如果需要自定义，可以重写这个方法
     *
     * @description:
     * @param
     * @return: int
     * @author: Andy
     * @time: 2018/11/20 14:09
     */
    protected abstract int subContentListCount();

    /**
     * 根据 contentList 创建行内容
     *
     * @description:
     * @param sheet
     * @param contentCellStyle
     * @param lastRowNum
     * @param contentList
     * @return: void
     * @author: Andy
     * @time: 2018/11/20 14:03
     */
    private void createRows(Sheet sheet, CellStyle contentCellStyle, int lastRowNum, List<List<String>> contentList) {
        if (contentList == null || contentList.size() == 0) {
            return;
        }

        for (int rowIndex = 0; rowIndex < contentList.size(); rowIndex++){
            List<String> cellListOfRow = contentList.get(rowIndex);
            int currentRowIndex = lastRowNum + rowIndex + 1;
            createRow(sheet, contentCellStyle, cellListOfRow, currentRowIndex);
        }
    }

    /**
     * 判断表头是否存在
     *
     * @description:
     * @param sheet
     * @return: boolean
     * @author: Andy
     * @time: 2018/11/20 11:48
     */
    private boolean checkIfSheetHeadExist(Sheet sheet){
        if (sheet.getLastRowNum() == 0 && sheet.getPhysicalNumberOfRows() == 0){
            System.out.println("请先创建 Excel 表头！");
            return false;
        }
        return true;
    }

    /**
     * 创建 Excel 表的一行记录
     *
     * @description:
     * @param sheet
     * @param cellStyle
     * @param cellListOfRow
     * @param currentRowIndex
     * @return: void
     * @author: Andy
     * @time: 2018/11/20 11:02
     */
    private void createRow(Sheet sheet, CellStyle cellStyle, List<String> cellListOfRow, int currentRowIndex) {
        if (cellListOfRow == null || cellListOfRow.isEmpty()){
            return;
        }

        Row row = sheet.createRow(currentRowIndex);
        for (int i = 0; i < cellListOfRow.size(); i++){
            Cell cell = row.createCell(i);
            cell.setCellValue(cellListOfRow.get(i));
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     * 获取表头样式
     *
     * @description:
     * @param book
     * @return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     * @author: Andy
     * @time: 2018/11/19 18:06
     */
    private static CellStyle getDefaultHeadCellStyle(Workbook book) {
        Font headfont = book.createFont();
        headfont.setFontName("宋体");
        headfont.setFontHeightInPoints((short) 12);
        headfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        CellStyle headstyle = book.createCellStyle();
        headstyle.setFont(headfont);
        headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        headstyle.setLocked(true);
        headstyle.setWrapText(true);
        return headstyle;
    }

    /**
     * 设置内容的样式
     *
     * @description:
     * @param book
     * @return: org.apache.poi.hssf.usermodel.HSSFCellStyle
     * @author: Andy
     * @time: 2018/11/19 18:08
     */
    private static CellStyle getDefaultContentCellStyle(Workbook book) {
        Font font = book.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);
        CellStyle style = book.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(true);
        return style;
    }
}
