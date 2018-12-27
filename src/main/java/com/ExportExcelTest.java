package com;

import com.poi.HSSFWorkbookBuilder;
import com.poi.SXSSFWorkbookBuilder;
import com.poi.WorkbookBuilder;
import com.utils.MemoryHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试两种不同的 Excel 导出方式的内存使用情况
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/19 17:36
 */
public class ExportExcelTest {
    /**
     * 就绪状态的屏障
     */
    private static CyclicBarrier cyclicBarrierReady;
    /**
     * 结束状态的屏障
     */
    private static CyclicBarrier cyclicBarrierFinished;

    public static void main(String[] args) {
        int count = 10;
        Runnable memoryPrinter = new Runnable() {
            @Override
            public void run() {
                MemoryHelper.printCurrentMemoryInfo();
            }
        };
        cyclicBarrierReady = new CyclicBarrier(count, memoryPrinter);
        cyclicBarrierFinished = new CyclicBarrier(count, memoryPrinter);

        for (int i = 0; i < count; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //设置屏障让所有线程处于就绪状态
                        String threadName = Thread.currentThread().getName();
                        cyclicBarrierReady.await();
                        System.out.println(new Date()+ "——" + threadName + ":Start exporting...");

                        WorkbookBuilder workbookBuilder = new SXSSFWorkbookBuilder();
                        Workbook workbook = workbookBuilder.createWorkbookWithLessMemory();
                        String fileName = Thread.currentThread().getName() + ".xlsx";
                        try (OutputStream out = new FileOutputStream("D:\\workplace\\exportExcelTest\\" + fileName)) {
                            workbook.write(out);
                            if (workbook instanceof SXSSFWorkbook) {
                                ((SXSSFWorkbook) workbook).dispose();
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(new Date()+ "——" + threadName + ":Finished!");
                        //设置屏障让所有线程处于结束状态
                        cyclicBarrierFinished.await();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }

                }
            }, "exportingThread-" + i)
                    .start();
        }
    }

}
