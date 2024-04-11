package com.example.ykcloud.controller;


import cn.hutool.json.JSONObject;
import com.example.ykcloud.Common.JsonResult;
import com.example.ykcloud.Service.LoginService;
import com.example.ykcloud.Service.UserService;
//import com.example.ykcloud.accountAop.TestContents;
import com.example.ykcloud.annotation.AnnotationTest;
import com.example.ykcloud.entity.User;
import com.example.ykcloud.utils.JwtUtils;
import com.example.ykcloud.utils.TokenUtils;
import com.example.ykcloud.vo.EmpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/login")
@Api(value = "登录管理", tags = "登录管理")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;


    @Autowired
    private JwtUtils jwtUtils;

    //@UserLoginToken
//    @AnnotationTest(type = "123", name = "name")
    @ApiOperation(value = "查询", httpMethod = "GET", notes = "查询")
    @GetMapping("/getList")
    public JsonResult getLoginList() {
        List<EmpVo> list = loginService.getList();

        return JsonResult.success(list);
    }

    //@TestContents
    @PostMapping("/CreateLogin")
    @ApiOperation(value = "创建登录", httpMethod = "POST", notes = "创建登录")
    public JsonResult getById(@RequestBody List<EmpVo> empVo) {
        loginService.insertLogin(empVo);
        return JsonResult.success(200, "保存成功", null);
    }

   //@AnnotationTest(type = "123", name = "name")
    @DeleteMapping("deleteById/{id}")
    @ApiOperation(value = "删除用户", httpMethod = "DELETE", notes = "删除用户")
    public JsonResult deleteLoginById(@PathVariable Integer id) {
        String result = loginService.deleteById(id).toString();
        return JsonResult.success(200, "删除成功", result);
    }


    @GetMapping("/downLoad")
    @ApiOperation(value = "下载数据", httpMethod = "GET", notes = "下载")
    public JsonResult download() throws IOException {
        boolean result = loginService.downLoadFile();
        if (!result) {

            return JsonResult.success(200, "下载成功", null);

        }
        return JsonResult.error("下载失败！");
    }


    @ResponseBody
    @PostMapping(value = "/loginInfo")
    @ApiOperation(value = "登录验证", httpMethod = "POST", notes = "登录验证")
    public JsonResult login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        user.setUserName(user.getUserName());
        User basic = userService.getUserByUserName(user.getUserName());
        if (basic == null) {
            JsonResult.error("用户名或密码错误");
        } else {
            //比对密码
            if (user.getPassWord().equals(basic.getPassWord())) {
                JSONObject subjectJson = new JSONObject();
                subjectJson.put("userId", 1);
                String token = TokenUtils.createToken(subjectJson);
                Cookie cookie = new Cookie("token", token);
                cookie.setPath("/");
                response.addCookie(cookie);
                return JsonResult.success(200, "token获取成功", token);

            } else {
                JsonResult.error("用户名或密码错误");
            }
        }
        return JsonResult.success();
    }

//    @PostMapping(value = "/login")
//    @ApiOperation(value = "登录校验", httpMethod = "POST", notes = "登录校验")
//    public JsonResult login(@RequestParam("userId") String userId, @RequestParam("passWord") String passWord) {
//        //判断用户登录信息是否验证成功
//        if (!"".equals(userId) && !"".equals(passWord)) {
//            String userName = userService.getName(userId, passWord);
//            if (StringUtils.isNotEmpty(userName)) {
//                //通过账号密码去数据库查数据  获取用户的信息
//
//
//                String token = jwtUtils.buildToken(userId, userName);
//                //R是封装的返回实体
//
//                return JsonResult.success(200, "获取成功", true, token);
//            }
//            return JsonResult.error("账号或密码为空！");
//        }
//
//        return JsonResult.error("账号或密码错误！");
//    }
}
