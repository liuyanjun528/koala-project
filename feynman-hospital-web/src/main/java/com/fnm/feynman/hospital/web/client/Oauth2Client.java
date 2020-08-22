package com.fnm.feynman.hospital.web.client;
import com.fnm.feynman.common.result.FastResponse;
import com.fnm.feynman.web.entity.LoginReq;
import com.fnm.feynman.web.entity.UserLoginDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yanjun.liu
 * @date 2020/8/21--19:01
 */
@FeignClient("feynman-oauth2")
public interface Oauth2Client {
    /**
     * 远程feignClient调用登陆
     * @param req
     * @return
     */
    @PostMapping("/oauth2/login")
    FastResponse<UserLoginDTO> login(@RequestBody LoginReq req);
}
