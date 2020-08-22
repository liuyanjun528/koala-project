package com.fnm.feynman.oauth2.controller;

import com.fnm.feynman.common.exception.CommonException;
import com.fnm.feynman.common.result.FastResponse;
import com.fnm.feynman.common.result.ResponseUtils;
import com.fnm.feynman.common.result.ValidatorUtils;
import com.fnm.feynman.oauth2.service.LoginService;
import com.fnm.feynman.web.entity.LoginReq;
import com.fnm.feynman.web.entity.UserLoginDTO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.IOException;
/**
 * @author Sir_小三
 * @date 2020/4/28--10:02
 */
@RestController
@RequestMapping("/oauth2")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public FastResponse<UserLoginDTO> login(@RequestBody LoginReq req) throws CommonException, IOException {
        ValidatorUtils.validate(req.getUserName(), "账号");
        ValidatorUtils.validate(req.getPassword(), "密码");
        ValidatorUtils.validate(req.getLoginType(),"登陆渠道类型");
        UserLoginDTO login = loginService.login(req.getUserName(), req.getPassword(),req.getLoginType());
        return ResponseUtils.success(login);
    }
}
