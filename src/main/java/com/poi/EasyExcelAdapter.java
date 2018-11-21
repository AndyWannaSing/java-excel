package com.poi;

import com.easyexcel.EasyExcelWorkbook;
import com.easyexcel.EasyExcelWorkbookWithLessMemory;
import com.poi.WorkbookBuilder;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 为使用 AbstractEasyExcelWorkbook 而创建的适配器
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/21 14:43
 */
public class EasyExcelAdapter extends WorkbookBuilder {

    @Override
    public Workbook createWorkbook(){
        return new EasyExcelWorkbook();
    }

    @Override
    public Workbook createWorkbookWithLessMemory(){
        return new EasyExcelWorkbookWithLessMemory();
    }

    /**
     * 创建 Workbook
     *
     * @description:
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    @Override
    protected Workbook getWorkbook() {
        return null;
    }

    /**
     * 获取 Excel 表内容列表
     *
     * @description:
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    @Override
    protected List<List<String>> getContentList() {
        return null;
    }

    /**
     * 获取 Excel 表头信息列表
     *
     * @description:
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 18:16
     */
    @Override
    protected List<String> getHeadList() {
        return null;
    }

    /**
     * 以更节省内存的方式创建表内容时，根据批次号获取数据列表的方法
     *
     * @param index
     * @return
     * @author: Andy
     */
    @Override
    protected List<List<String>> getSubContentList(int index) {
        return null;
    }

    /**
     * 子列表数量。
     * 假设子列表数量为 n， 则在导出 Excel 表时，分 n 次从数据源获取数据，
     * 一批一批地把数据写入到 WorkBook 中。
     * <p>
     * 这样做是为了以更节省内存的方式创建表内容
     * <p>
     * 默认分批数量是 100。如果需要自定义，可以重写这个方法
     *
     * @description:
     * @return: int
     * @author: Andy
     * @time: 2018/11/20 14:09
     */
    @Override
    protected int subContentListCount() {
        return 0;
    }
}
