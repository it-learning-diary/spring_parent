package com.elvis.easyexcel.model;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Demo implements Serializable {
    private static final long serialVersionUID = -920481620956257604L;
    @ExcelProperty(value = "姓名", index = 0)
    private String stringType;
    @ExcelProperty(value = "姓名2", index = 1)
    private Integer integerType;
    // 这里使用String类型接收才能格式化,如果使用Date类型则无法格式化
    @ExcelProperty(value = "姓名3", index = 2)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private String dateType;
    @ExcelProperty(value = "姓名4", index = 3)
    private Double doubleType;
    @ExcelProperty(value = "姓名5", index = 4)
    private Long longType;
    @ExcelProperty(value = "姓名6", index = 5)
    private Float floatType;
    @ExcelProperty(value = "姓名7", index = 6)
    private Boolean booleanType;
    @ExcelProperty(value = "姓名8", index = 7)
    private Short shortType;
}
