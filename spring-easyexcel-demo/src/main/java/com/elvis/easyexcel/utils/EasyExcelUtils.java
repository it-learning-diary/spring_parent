package com.elvis.easyexcel.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.elvis.easyexcel.listener.ObjectListener;
import com.elvis.easyexcel.model.Demo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

@Slf4j
public class EasyExcelUtils {
    /**
     * @param in    读取的文件流
     * @param model 与excel文件数据对应的实体
     * @param type  通用的数据读取解析监听器
     * @return
     */
    public static List<Object> readExcelFile(InputStream in, Object model, ObjectListener type) {
        ExcelReader reader = null;
        try {
            reader = EasyExcel.read(in, model.getClass(), type).build();
            reader.readAll();
        } catch (Exception e) {
            log.error("读取excel文件错误：" + e.getMessage());
            return null;
        } finally {
            // 关闭流,读的时候会创建临时文件，不关闭到时磁盘会崩的
            if (reader != null) {
                reader.finish();
            }
        }
        return type.getReadData();
    }


    /**
     * 保存数据到excel文件
     *
     * @param data     数据(支持多个sheet写入,根据数据的个数写入对应个sheet,默认多个sheet写入的数据是同一个实体的)
     * @param savePath 保存的路径
     * @return 是否保存成功
     */
    public static Boolean writeExcelFileWithCommonEntity(List<List<Object>> data, String savePath) {
        if (CollectionUtils.isNotEmpty(data)) {
            ExcelWriter excelWriter = null;
            // 输出流放到try的小括号中，方法结束时会自动关闭流,这个是jdk1.8的新特性,对于经常忘记关流的小伙伴很友好哦
            try {
                // 获取到操作写入excel的操作对象,第二个参数是导出的excel文件的标题名对应的实体
                // 获取写入数据中的第一个元素的类类型
                excelWriter = EasyExcel.write(savePath).build();
                // 设置每个sheet的名称
                for (List<Object> objectList : data) {
                    Object item = objectList.get(0);
                    WriteSheet writeSheet = EasyExcel.writerSheet(1, "模板").head(item.getClass()).build();
                    excelWriter.write(objectList, writeSheet);
                }
            } catch (Exception e) {
                log.error("保存数据到excel错误:{}", e.getMessage());
                return false;
            } finally {
                if (excelWriter != null) {
                    excelWriter.finish();
                }
            }
        } else {
            return false;
        }
        return true;
    }


}
