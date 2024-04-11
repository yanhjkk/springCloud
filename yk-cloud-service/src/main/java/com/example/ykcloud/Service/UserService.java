package com.example.ykcloud.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ykcloud.entity.User;

public interface UserService extends IService<User> {

    /**
     * 通过用户名获取唯一用户
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName);

//    /**
//     * 根据条件获取用户
//     * @param user
//     * @return
//     */
//    public List<User> getUserSelective(User user);
//
//    /**
//     * 通过主键获取用户
//     * @param userId
//     * @return
//     */
//    public User getUserByUserId(Integer userId);




    String getName(String userId, String passWord);
}
