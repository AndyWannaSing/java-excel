package com.utils;

import java.util.Date;

/**
 * 获取内存信息的帮助类
 *
 * @description:
 * @author: Andy
 * @time: 2018/11/21 17:04
 */
public class MemoryHelper {
    /**
     * 打印当前的内存使用信息
     *
     * @description:
     * @param
     * @return: void
     * @author: Andy
     * @time: 2018/11/19 17:38
     */
    public static void printCurrentMemoryInfo() {
        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = Runtime.getRuntime().maxMemory();

        StringBuilder currentState = new StringBuilder();
        currentState.append("totalMemory: ").append(parseByteToMBStr(totalMemory))
                .append("freeMemory: ").append(parseByteToMBStr(freeMemory))
                .append("usedMemory: ").append(parseByteToMBStr(usedMemory))
                .append("maxMemory: ").append(parseByteToMBStr(maxMemory));

        System.out.println(new Date() + " —— Current State: " + currentState.toString());
    }

    /**
     * 把字节转换成千字节
     *
     * @description:
     * @param byteAmount 字节数
     * @return: long
     * @author: Andy
     * @time: 2018/11/19 17:48
     */
    private static String parseByteToKBStr(long byteAmount) {
        return (byteAmount / 1024) + " KB ";
    }

    /**
     * 把字节转换成MB
     *
     * @description:
     * @param byteAmount 字节数
     * @return: long
     * @author: Andy
     * @time: 2018/11/19 17:48
     */
    private static String parseByteToMBStr(long byteAmount) {
        return (byteAmount / 1024 / 1024) + " MB ";
    }
}
