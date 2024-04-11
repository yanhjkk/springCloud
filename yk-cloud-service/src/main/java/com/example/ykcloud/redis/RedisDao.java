package com.example.ykcloud.redis;

import com.example.ykcloud.entity.Emp;
import com.example.ykcloud.vo.EmpVo;

import java.util.List;

public interface RedisDao {

    List<EmpVo> findEmpList();


    void save(List<EmpVo> list);
}
