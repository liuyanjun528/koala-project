package com.fnm.feynman.web.config;

import com.fnm.feynman.common.em.CommonCodeEnum;
import com.fnm.feynman.common.exception.CommonException;
import com.fnm.feynman.common.result.FastResponse;
import com.fnm.feynman.common.result.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 刘彦军
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

    /**
     * springSecurity token权限不足异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler({AccessDeniedException.class})
    public FastResponse<Void> handle(AccessDeniedException e) {
        log.error(CommonCodeEnum.AUTHORITY_ERROR.getMessage(), e);
        return ResponseUtils.create(CommonCodeEnum.AUTHORITY_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public FastResponse<Void> handleException(Exception e) {
        log.error(CommonCodeEnum.EXCEPTION.getMessage(), e);
        return ResponseUtils.create(CommonCodeEnum.EXCEPTION);
    }

    @ExceptionHandler({CommonException.class})
    public FastResponse<Void> commonHandleException(CommonException e) {
        log.warn(CommonCodeEnum.COMMON_EXCEPTION.getMessage(), e);
        return ResponseUtils.create(e.getCode(), e.getMessage(), e.getSubMessage());
    }
    @ExceptionHandler({DuplicateKeyException.class})
    public FastResponse<Void> handle(DuplicateKeyException e) {
        log.error(CommonCodeEnum.DB_KEY_DUPLICATE.getMessage(), e);
        return ResponseUtils.create(CommonCodeEnum.DB_KEY_DUPLICATE);
    }

}
