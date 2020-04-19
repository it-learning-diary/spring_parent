package com.elvis;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.elvis.easyexcel.listener.ObjectListener;
import com.elvis.easyexcel.model.Demo;
import com.elvis.easyexcel.utils.EasyExcelUtils;
import org.json.JSONArray;
import org.junit.Test;

import javax.jws.Oneway;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class demo {
    @Test
    public void demo1() throws Exception{
        // 读取文件
        System.out.println("开始读取文件------------------------------------");
        String fileName = "E:\\newpath\\excelutils\\build\\classes\\小明.xlsx";
        InputStream in = new FileInputStream(new File(fileName));
        List<Object> objects = EasyExcelUtils.readExcelFile(in, new Demo(), new ObjectListener());
        JSONArray array = new JSONArray(objects);
        System.out.println(array);
        System.out.println("--------------------------------------------------------------");
        System.out.println("开始保存文件------------------------------------");
        String savePath = "E:\\newpath\\excelutils\\build\\classes\\保存文件测试.xlsx";
        List<List<Object>> data = new ArrayList<>();
        List<Object> item = new ArrayList<>();
        Demo abc = new Demo("abc",12,"2020-12-12 19:10:10",12.2,12l,12f,false,Short.parseShort("12"));
        item.add(abc);
        data.add(item);
        EasyExcelUtils.writeExcelFileWithCommonEntity(data,savePath);
    }


}
