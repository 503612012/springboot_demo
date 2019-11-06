package com.oven.controller;

import com.oven.constants.AppConst;
import com.oven.utils.JwtUtils;
import com.oven.utils.ResultInfo;
import com.oven.vo.User;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试控制器
 *
 * @author Oven
 */
@RestController
@RequestMapping("jwt")
public class JwtController {

    @PostMapping("login")
    public ResultInfo login(@RequestBody User user) {
        ResultInfo<String> result = new ResultInfo<>();
        if ("Oven".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            Map<String, Object> map = new HashMap<>();
            map.put("userName", "Oven");
            String token = JwtUtils.generateToken(map);
            result.setData(token);
        } else {
            result.setCode(301);
            result.setData("用户名或密码错误");
        }
        return result;
    }

    @GetMapping("test")
    public String test(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute(AppConst.USER_INFO_KEY);
        if (null != claims) {
            return (String) claims.get("userName");
        } else {
            return "System Error!";
        }
    }
}