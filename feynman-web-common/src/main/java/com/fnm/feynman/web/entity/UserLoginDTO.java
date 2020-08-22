package com.fnm.feynman.web.entity;



/**
 * @author Sir_小三
 * @date 2020/2/5--20:39
 */
public class UserLoginDTO {

    private JWT jwt;

    private ZcSysUser zcSysUser;

    private ZcWebUser zcWebUser;

    public ZcWebUser getZcWebUser() {
        return zcWebUser;
    }

    public void setZcWebUser(ZcWebUser zcWebUser) {
        this.zcWebUser = zcWebUser;
    }

    public JWT getJwt() {
        return jwt;
    }

    public void setJwt(JWT jwt) {
        this.jwt = jwt;
    }

    public ZcSysUser getZcSysUser() {
        return zcSysUser;
    }

    public void setZcSysUser(ZcSysUser zcSysUser) {
        this.zcSysUser = zcSysUser;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "jwt=" + jwt +
                ", zcSysUser=" + zcSysUser +
                ", zcWebUser=" + zcWebUser +
                '}';
    }
}
