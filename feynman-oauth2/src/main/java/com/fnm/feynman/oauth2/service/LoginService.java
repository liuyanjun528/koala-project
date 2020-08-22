package com.fnm.feynman.oauth2.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fnm.feynman.common.em.CommonCodeEnum;
import com.fnm.feynman.common.exception.CommonException;
import com.fnm.feynman.common.result.ExceptionUtils;
import com.fnm.feynman.common.utils.BPwdEncoderUtil;
import com.fnm.feynman.oauth2.constant.ZcConst;
import com.fnm.feynman.web.entity.JWT;
import com.fnm.feynman.web.entity.UserLoginDTO;
import com.fnm.feynman.web.entity.ZcSysUser;
import com.fnm.feynman.web.entity.ZcWebUser;
import com.fnm.feynman.web.mapper.ZcWebUserMapper;
import com.fnm.feynman.web.service.ZcSysUserService;
import com.fnm.feynman.web.service.ZcWebUserService;
import com.fnm.feynman.web.utils.HttpClientUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;


/**
 * @author Sir_小三
 * @date 2020/4/28--10:08
 */
@Service
public class LoginService {
    @Resource
    private ZcSysUserService zcSysUserService;
    @Resource
    private ZcWebUserService zcWebUserService;

    public UserLoginDTO login(String account, String password,String loginType) throws CommonException, IOException {
        if(loginType.equals(ZcConst.ADMIN_LOGIN_TYPE)){
            ZcSysUser sysUser = zcSysUserService.getOne(new QueryWrapper<ZcSysUser>().eq("account", account));
            if (sysUser==null) {
                throw ExceptionUtils.create(CommonCodeEnum.ACCOUNT_ERROR);
            }
            if (!BPwdEncoderUtil.matches(password, sysUser.getPassword())) {
                throw ExceptionUtils.create(CommonCodeEnum.PASSWORD_ERROR);
            }
            UserLoginDTO userLoginDTO = getUserLoginDTO(account, password, loginType);
            userLoginDTO.setZcSysUser(sysUser);
            return userLoginDTO;
        }
        if(loginType.equals(ZcConst.APP_LOGIN_TYPE)){
            ZcWebUser webUser = zcWebUserService.getOne(new QueryWrapper<ZcWebUser>().eq("open_id", account));
            if (webUser==null) {
                throw ExceptionUtils.create(CommonCodeEnum.ACCOUNT_ERROR);
            }
            if (!BPwdEncoderUtil.matches(password, webUser.getPassword())) {
                throw ExceptionUtils.create(CommonCodeEnum.PASSWORD_ERROR);
            }
            //获取jwt
            UserLoginDTO userLoginDTO = getUserLoginDTO(account, password, loginType);
            userLoginDTO.setZcWebUser(webUser);
            return userLoginDTO;
        }
        throw ExceptionUtils.create(CommonCodeEnum.LOGIN_TYPE_ERROR);
    }

    private UserLoginDTO getUserLoginDTO(String account, String password,String loginType)throws CommonException,IOException{
        JWT jwt = HttpClientUtils.httpPost(account, password,loginType);
        if (jwt == null) {
            throw ExceptionUtils.create(CommonCodeEnum.GET_TOKEN_ERROR);
        }
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setJwt(jwt);
        return userLoginDTO;
    }
}
