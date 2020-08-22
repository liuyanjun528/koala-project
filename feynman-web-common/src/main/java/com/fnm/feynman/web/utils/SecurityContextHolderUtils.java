package com.fnm.feynman.web.utils;
import com.fnm.feynman.web.entity.ZcWebUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Sir_小三
 * @date 2020/3/26--23:27
 */
public class SecurityContextHolderUtils {
    /**
     * 获取小程序用户
     * @return
     */
    public static ZcWebUser getWebUser() {
        ZcWebUser webUser = (ZcWebUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return webUser;
    }

}
