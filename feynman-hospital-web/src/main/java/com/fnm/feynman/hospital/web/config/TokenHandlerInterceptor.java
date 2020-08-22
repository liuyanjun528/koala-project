package com.fnm.feynman.hospital.web.config;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fnm.feynman.web.entity.ZcWebUser;
import com.fnm.feynman.web.service.ZcWebUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * 定义一个Interceptor 非常简单方式也有几种，我这里简单列举两种 1、类 要实现Spring 的HandlerInterceptor 接口 2、类
 * 继承实现了HandlerInterceptor 接口的类，例如 已经提供的实现了HandlerInterceptor
 * 接口的抽象类HandlerInterceptorAdapter
 * @author 刘彦军 ！！！除了定义此类，还需要将自定义的拦截器，注册到WebMvcConfigurer中，拦截器才起作用
 * 之前我们在xml中配置拦截路径等，springboot我们需要继承WebMvcConfigurerAdapter类
 * 不过springBoot2.0以上 WebMvcConfigurerAdapter 方法过时， 有两种替代方案：
 * 1、继承WebMvcConfigurationSupport 2、实现WebMvcConfigurer
 * 但是继承WebMvcConfigurationSupport会让Spring-boot对mvc的自动配置失效。根据项目情况选择。
 * 现在大多数项目是前后端分离，并没有对静态资源有自动配置的需求所以继承WebMvcConfigurationSupport也未尝不可。
 */
@Slf4j
public class TokenHandlerInterceptor implements HandlerInterceptor {

    /**
     * 需要提前以@bean的方式注入拦截器，否则Mapper接口为null;
     * 原因是因为拦截器的加载在springContext之前，所以自动注入的mapper是null
     */
    @Autowired
    private ZcWebUserService zcWebUserService;

    /**
     * 最终拦截
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 后置拦截+统计总访问数
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //log.info("后置拦截--请求处理之后进行调用，但是在视图被渲染之前，即Controller方法调用之后");
    }

    /**
     * 前置拦截解析令牌
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication instanceof OAuth2Authentication) {
                OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
                Authentication userAuthentication = oAuth2Authentication.getUserAuthentication();
                //获取用户的account，这里就是UserDetails类 中返回的SecurityUser的name属性，这个name可以扩展
                String account = userAuthentication.getName();
                ZcWebUser webUser = zcWebUserService.getOne(new QueryWrapper<ZcWebUser>().eq("open_id",account));
                Collection<? extends GrantedAuthority> authorities = userAuthentication.getAuthorities();
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(webUser, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        return true;
    }
}
