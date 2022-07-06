package com.southwind.phone_store_demo.controller;

import cn.hutool.core.util.IdUtil;
import com.southwind.phone_store_demo.entity.User;
import com.southwind.phone_store_demo.entity.LoginProperties;
import com.southwind.phone_store_demo.enums.LoginCodeEnum;
import com.southwind.phone_store_demo.service.UserService;
import com.southwind.phone_store_demo.util.ResultVOUtil;
import com.southwind.phone_store_demo.vo.ResultVO;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;
import com.southwind.phone_store_demo.util.RedisUtil;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginProperties loginProperties;



    @PostMapping("/register")
    public ResultVO register(@RequestBody User user) {
        try {
            System.out.println(user);
            userService.register(user);
            return ResultVOUtil.success(null);
        } catch (Exception e) {
            return ResultVOUtil.error(null);
        }
    }


    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        //获取当前的用户 每个shiro拦截到的请求，都会根据session id创建Subject,清除当前线程的绑定，然后重新绑定的线程中，之后执行过滤器。
        //所以我们再SecurityUtils.getSubject()中获取的一直是当前用户的信息。
        Subject subject = SecurityUtils.getSubject();
        String code =user.getCode();
        System.out.println(code);
            //封装用户的登录数据
        String uuid =user.getUuid();
        System.out.println(uuid);
        String captchaValue =(String) RedisUtil.get(uuid);
        System.out.println(captchaValue);
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        subject.login(token);
        Session session = subject.getSession();
        session.setAttribute("user", (User) subject.getPrincipal());//用来设置session值的，sessionName是名称，object是你要保存的对象。

        if(code.equals(captchaValue)) {
            System.out.println("登入成功");
            return ResultVOUtil.success(null);
        }else {
        System.out.println("登入失败");
        return ResultVOUtil.error(null);}

    }

    // 注销方法
    @GetMapping("/logout")
    public ResultVO logout() {
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        System.out.println("退出成功");
        return ResultVOUtil.success(null);
    }
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @GetMapping("/code")
    public Object getCode(){
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = "code-key-"+ IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if(captcha.getCharType()-1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")){
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        RedisUtil.setMinutes(uuid,captchaValue,loginProperties.getLoginCode().getExpiration());
        // 验证码信息
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img",captcha.toBase64());
            put("uuid",uuid);
        }};

        return imgResult;
    }
}