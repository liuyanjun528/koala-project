package com.fnm.feynman.oauth2.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fnm.feynman.oauth2.constant.ZcConst;
import com.fnm.feynman.web.entity.*;
import com.fnm.feynman.web.service.ZcSysRoleService;
import com.fnm.feynman.web.service.ZcSysRoleUserService;
import com.fnm.feynman.web.service.ZcSysUserService;
import com.fnm.feynman.web.service.ZcWebUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sir_小三
 * @date 2020/1/30--12:54
 * 角色表实现GrantedAuthority  类，重写getAuthority方法，返回角色，并且在UserServiceDetail的实现类中，用户如果登陆成功
 * 通过用户id，查询用户角色关联表中数据，然后在查询到具体的角色，设置到
 * UserDetails的权限集合中Set<GrantedAuthority> authorities;
 * spring-security 提供了一个user，也可以间接继承此类，通过构造函数设置进去权限集合
 * 权限集合中可以是任意字符串，有重写getAuthority() 返回数据决定
 * @author 刘彦军 2020-02-02 21:38:10 269
 * 这里，密码模式请求jwt令牌的时候，会调用验证用户信息，并生成令牌,令牌中包含用户的信息，以及角色信息
 */
@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService  {

    @Resource
    private ZcSysRoleUserService zcSysRoleUserService;
    @Resource
    private ZcWebUserService zcWebUserService;
    @Resource
    private ZcSysRoleService zcSysRoleService;
    @Resource
    private ZcSysUserService zcSysUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

   public UserDetails loadUserByUsername(String account, String loginType) throws UsernameNotFoundException{
       //角色集合
       List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if(ZcConst.ADMIN_LOGIN_TYPE.equals(loginType)){
            ZcSysUser sysUser = zcSysUserService.getOne(new QueryWrapper<ZcSysUser>().eq("account", account));
            if(ZcConst.IS_DELETED.equals(sysUser.getIsDeleted())){
                throw new UsernameNotFoundException("此用户已被禁用");
            }
            //查询用户角色,管理端需要
            List<ZcSysRoleUser> list = zcSysRoleUserService.list( new QueryWrapper<ZcSysRoleUser>().eq("user_id", sysUser.getSysId()));
            for (int i = 0; i < list.size(); i++) {
                ZcSysRole role = zcSysRoleService.getById(list.get(i).getRoleId());
                grantedAuthorities.add(role);
            }
           log.info("UserDetailsService:管理端登陆，查询用户成功，交由oauth2进行下一步密码认证");
            SecurityUser securityUser = new SecurityUser(account, sysUser.getPassword(), grantedAuthorities);
            return securityUser;
        }
        if(ZcConst.APP_LOGIN_TYPE.equals(loginType)){
            ZcWebUser webUser = zcWebUserService.getOne(new QueryWrapper<ZcWebUser>().eq("open_id", account));
            if(ZcConst.IS_DELETED.equals(webUser.getIsDeleted())){
                throw new UsernameNotFoundException("此用户已被删除");
            }
            SecurityUser securityUser = new SecurityUser(account, webUser.getPassword(), grantedAuthorities);
            log.info("UserDetailsService:web用户端登陆，查询用户成功，交由oauth2进行下一步密码认证");
            return securityUser;
        }
       throw new UsernameNotFoundException("没有匹配的登陆类型:loginType");
    }
}
