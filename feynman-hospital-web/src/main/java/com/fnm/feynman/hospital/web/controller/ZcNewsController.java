package com.fnm.feynman.hospital.web.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fnm.feynman.common.page.FastPaging;
import com.fnm.feynman.common.result.FastResponse;
import com.fnm.feynman.common.result.ResponseUtils;
import com.fnm.feynman.hospital.web.entity.ZcNews;
import com.fnm.feynman.hospital.web.service.ZcNewsService;
import com.fnm.feynman.hospital.web.utils.SecurityContextHolderUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 消息表 前端控制器
 * </p>
 *
 * @author Sir_小三
 * @since 2020-08-21
 */
@RestController
@RequestMapping("/news")
public class ZcNewsController {

    @Resource
    private ZcNewsService zcNewsService;

    @GetMapping
    @ApiOperation("获取资讯列表")
    public FastResponse<Page> getInfo(FastPaging fastPaging) {
        Page<ZcNews> page = new Page<>();
        page.setCurrent(fastPaging.getPage());
        page.setSize(fastPaging.getPageSize());
        Page<ZcNews> result = zcNewsService.page(page, new QueryWrapper<>());
        return ResponseUtils.success(result);
    }



}
