package com.example.ykcloud.Service.Impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.TableStyle;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.excel.write.style.column.SimpleColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ykcloud.Service.LoginService;
import com.example.ykcloud.constant.Common;
import com.example.ykcloud.easyExcel.DownLoadEmpData;
import com.example.ykcloud.entity.Emp;
import com.example.ykcloud.mapper.LoginMapper;
import com.example.ykcloud.redis.RedisDao;
import com.example.ykcloud.vo.EmpVo;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Emp> implements LoginService {

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private LoginMapper loginMapper;

    @Scheduled(cron = "0 */2 * * * ?")
    @Override
    public List<EmpVo> getList() {
//        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        //先查缓存
        List<EmpVo> collectList;
        List<EmpVo> redisList = redisDao.findEmpList();
        if (!ObjectUtils.isEmpty(redisList)) {
            collectList = redisList.stream().filter(emp -> !Common.DELETE_IDENTIFY.equals(emp.getDel())).collect(Collectors.toList());
        } else {
            List<EmpVo> list = loginMapper.selectAll();
            List<EmpVo> listResult = list.stream().filter(emp -> !Common.DELETE_IDENTIFY.equals(emp.getDel())).collect(Collectors.toList());
            if (list != null) {
                redisDao.save(list);
            }
            return listResult;
        }


        return collectList;
    }

    @Override
    public void insertLogin(List<EmpVo> empVo) {
        loginMapper.saveloginList(empVo);
        List<EmpVo> list = loginMapper.selectAll();
        if (!ObjectUtils.isEmpty(list)) {
            redisDao.save(list);
        }
    }

    @Override
    public Boolean deleteById(Integer id) {
//        boolean remove = this.remove(new QueryWrapper<Emp>().lambda()
//                .eq(Emp::getId, id));
        Emp emp = new Emp();
//        emp.setId(id);
//        emp.setDel(1);

        boolean b = loginMapper.deleteEmpList(id);
        //boolean b = this.updateById(emp);
        if (!b) {
            throw new RuntimeException("删除失败");
        }
        return true;
    }


    @Transactional
    @Override
    public boolean downLoadFile() throws IOException {
        List<EmpVo> list = this.getList();
        FileOutputStream outputStream = new FileOutputStream("D://Desktop/sa.xlsx");


        // 定义内容的单元格样式
        WriteCellStyle contentStyle = new WriteCellStyle();
        contentStyle.setFillPatternType(FillPatternType.SOLID_FOREGROUND);
        contentStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex()); // 背景颜色
        contentStyle.setWrapped(true); // 设置自动换行

        // 字体配置
        WriteFont contentFont = new WriteFont();
        contentFont.setFontHeightInPoints((short) 12); // 字体大小
        contentFont.setBold(true);
        contentStyle.setWriteFont(contentFont);

        // 设置内容水平居中
        contentStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //设置边框
        contentStyle.setBorderTop(BorderStyle.THIN);
        contentStyle.setBorderBottom(BorderStyle.THIN);
        contentStyle.setBorderLeft(BorderStyle.THIN);
        contentStyle.setBorderRight(BorderStyle.THIN);

        // 定义标题的单元格样式
        WriteCellStyle headerStyle = new WriteCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // 标题背景色

        // 标题字体配置
        WriteFont headerFont = new WriteFont();
        headerFont.setFontHeightInPoints((short) 15); // 标题字体大小
        headerStyle.setWriteFont(headerFont);

        // 设置标题边框
        headerStyle.setBorderTop(BorderStyle.THICK);
        headerStyle.setBorderBottom(BorderStyle.THICK);
        headerStyle.setBorderLeft(BorderStyle.THICK);
        headerStyle.setBorderRight(BorderStyle.THICK);


        // 通过 HorizontalCellStyleStrategy 应用样式到 Excel 写入器中
        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headerStyle, contentStyle);


        ExcelWriter excelWriter = EasyExcelFactory.write(outputStream)
                .registerWriteHandler(horizontalCellStyleStrategy) // 注册样式处理器
                .registerWriteHandler(new SimpleColumnWidthStyleStrategy(30)) // 设置列宽策略
                .build();
        WriteSheet sheet = EasyExcel.writerSheet()
                .sheetName("emp")
                .head(EmpVo.class)
//                .registerWriteHandler(horizontalCellStyleStrategy) // 注册样式处理器
                .build();
        excelWriter.write(list, sheet);
        excelWriter.finish();
        outputStream.close();
        return false;
    }


}
