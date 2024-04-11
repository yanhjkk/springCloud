package com.example.ykcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ykcloud.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EmailMapper extends BaseMapper<User> {
    String getEmailByName(String name);


    String getName(String recipient);

}
