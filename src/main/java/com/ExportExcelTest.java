package com;

import com.poi.EasyExcelAdapter;
import com.poi.SXSSFWorkbookBuilder;
import com.poi.WorkbookBuilder;
import com.utils.MemoryHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 测试两种不同的 Excel 导出方式的内存使用情况
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/19 17:36
 */
public class ExportExcelTest {
    public static void main(String[] args) {
        System.out.println("=======================================================================================================");
        System.out.println("before creating workbook...");
        MemoryHelper.printCurrentMemoryInfo();

        long startTime = System.currentTimeMillis();
        WorkbookBuilder workbookBuilder = new EasyExcelAdapter();
        Workbook workbook = workbookBuilder.createWorkbookWithLessMemory();
        long endTime = System.currentTimeMillis();

        System.out.println("after creating workbook["+ workbook.getClass() +"] [" + "time-consuming："+ (endTime - startTime) +" milliseconds]");
        MemoryHelper.printCurrentMemoryInfo();
        System.out.println("=======================================================================================================");


        System.out.println("before exporting to excel...");
        MemoryHelper.printCurrentMemoryInfo();

        startTime = System.currentTimeMillis();
        try(OutputStream out = new FileOutputStream("D:\\workplace\\test.xlsx")) {
            workbook.write(out);
            if (workbook instanceof SXSSFWorkbook){
                ((SXSSFWorkbook)workbook).dispose();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        endTime = System.currentTimeMillis();

        System.out.println("after exporting to excel...[" + "time-consuming："+ (endTime - startTime) +" milliseconds]");
        MemoryHelper.printCurrentMemoryInfo();
        System.out.println("=======================================================================================================");
    }



}
