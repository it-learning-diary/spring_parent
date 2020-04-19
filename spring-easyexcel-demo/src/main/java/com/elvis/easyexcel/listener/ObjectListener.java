package com.elvis.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ObjectListener extends AnalysisEventListener<Object> {
    // 读取到的数据
    private List<Object> readData = new ArrayList<>();

    /**
     *  解析数据进入的方法
     * @param o 本次读到的数据
     * @param analysisContext
     */
    @Override
    public void invoke(Object o, AnalysisContext analysisContext) {
        JSONObject jsonObject = new JSONObject(o);
        log.info("读取到的数据:{}", jsonObject.toString());
        if(Objects.nonNull(o)){
            readData.add(o);
        }
    }
    /**
     * 所有数据解析完成了 都会来调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成了 都会来调用");
    }

    /**
     * 在转换异常 获取其他异常下会调用本接口。抛出异常则停止读取。如果这里不抛出异常则 继续读取下一行。
     * 如果你的程序在读取解析时即使有异常也不想后面的解析失败的,在此处打出解析错误日志即可
     * 如果你的程序只有解析过程出错就解析解析的话,这在此处手动抛出异常即可
     * @param exception
     * @param context
     * @throws Exception
     */
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex());
        }
    }
    // 反馈解析完成的数据
    public List<Object> getReadData(){
        return readData;
    }
}
