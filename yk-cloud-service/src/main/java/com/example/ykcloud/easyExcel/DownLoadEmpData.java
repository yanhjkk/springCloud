package com.example.ykcloud.easyExcel;


import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownLoadEmpData {

    @ExcelProperty("主键id")
    private String id;
    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("密码")
    private  String deg;

    @ExcelProperty("密码")
    private  String salary;

    @ExcelProperty("部门编号")
    private  String dept;

    @ExcelProperty("是否删除")
    private String del;

    @ExcelProperty("电子邮件")
    private String email;
}
