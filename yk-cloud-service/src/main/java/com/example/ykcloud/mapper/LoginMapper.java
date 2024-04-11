package com.example.ykcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ykcloud.entity.Emp;
import com.example.ykcloud.vo.EmpVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LoginMapper extends BaseMapper<Emp> {


    List<EmpVo> selectAll();


    void saveloginList(List<EmpVo> empVo);

    boolean deleteEmpList(Integer id);
}
