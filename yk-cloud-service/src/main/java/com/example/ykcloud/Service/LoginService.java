package com.example.ykcloud.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ykcloud.entity.Emp;
import com.example.ykcloud.vo.EmpVo;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface LoginService extends IService<Emp> {

    List<EmpVo> getList();


    void insertLogin(List<EmpVo> empVo);

    Boolean deleteById(Integer id);

    boolean downLoadFile() throws IOException;

}
