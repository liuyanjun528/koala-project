package com.fnm.feynman.web.entity;

import lombok.Data;

/**
 * @author yanjun.liu
 * @date 2020/8/21--11:01
 */
@Data
public class LoginReq {
    private String userName;
    private String password;
    private String loginType;
}
