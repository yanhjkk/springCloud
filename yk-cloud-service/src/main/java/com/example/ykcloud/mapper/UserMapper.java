package com.example.ykcloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ykcloud.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

        List<User> getUserSelective(User user);

        User getUserByPrimary(Integer userId);

         User getUserByName(String userName);

    String getUserName(String userId, String passWord);
}
