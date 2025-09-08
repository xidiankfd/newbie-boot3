package com.newbie.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbie.standard.domain.entity.StdResource;
import com.newbie.standard.mapper.StdResourceMapper;
import com.newbie.standard.service.StdResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 标准资源主表 Service实现
 *
 * @author Claude
 */
@Service
@RequiredArgsConstructor
public class StdResourceServiceImpl extends ServiceImpl<StdResourceMapper, StdResource> implements StdResourceService {

    private final StdResourceMapper stdResourceMapper;

    @Override
    public Page<StdResource> selectPage(Page<StdResource> page, StdResource stdResource) {
        LambdaQueryWrapper<StdResource> queryWrapper = new LambdaQueryWrapper<>();
        
        // 构建查询条件
        queryWrapper.like(StringUtils.hasText(stdResource.getResourceCode()), 
                         StdResource::getResourceCode, stdResource.getResourceCode())
                   .like(StringUtils.hasText(stdResource.getChineseName()), 
                         StdResource::getChineseName, stdResource.getChineseName())
                   .eq(StringUtils.hasText(stdResource.getStandardStatus()), 
                       StdResource::getStandardStatus, stdResource.getStandardStatus())
                   .eq(StringUtils.hasText(stdResource.getStandardType()), 
                       StdResource::getStandardType, stdResource.getStandardType())
                   .eq(StringUtils.hasText(stdResource.getStandardField()), 
                       StdResource::getStandardField, stdResource.getStandardField())
                   .like(StringUtils.hasText(stdResource.getKeywords()), 
                         StdResource::getKeywords, stdResource.getKeywords())
                   .orderByDesc(StdResource::getCreateTime);

        return this.page(page, queryWrapper);
    }

    @Override
    public StdResource getByResourceCode(String resourceCode) {
        return stdResourceMapper.selectByResourceCode(resourceCode);
    }

    @Override
    public List<StdResource> listByStatus(String standardStatus) {
        return stdResourceMapper.selectByStatus(standardStatus);
    }

    @Override
    public List<StdResource> listByField(String standardField) {
        return stdResourceMapper.selectByField(standardField);
    }

    @Override
    public List<StdResource> listByClassification(String standardClassification) {
        return stdResourceMapper.selectByClassification(standardClassification);
    }

    @Override
    public List<StdResource> listByConditions(StdResource stdResource) {
        return stdResourceMapper.selectByConditions(stdResource);
    }

    @Override
    public boolean add(StdResource stdResource) {
        // 检查标准编号唯一性
        StdResource existing = getByResourceCode(stdResource.getResourceCode());
        if (existing != null) {
            throw new RuntimeException("标准编号已存在：" + stdResource.getResourceCode());
        }
        return this.save(stdResource);
    }

    @Override
    public boolean update(StdResource stdResource) {
        if (stdResource.getId() == null) {
            throw new RuntimeException("标准资源ID不能为空");
        }
        
        // 检查标准编号唯一性(排除自身)
        LambdaQueryWrapper<StdResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StdResource::getResourceCode, stdResource.getResourceCode())
                   .ne(StdResource::getId, stdResource.getId());
        
        StdResource existing = this.getOne(queryWrapper);
        if (existing != null) {
            throw new RuntimeException("标准编号已存在：" + stdResource.getResourceCode());
        }
        
        return this.updateById(stdResource);
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public List<String> getStatusOptions() {
        return Arrays.asList("现行", "废止", "即将实施", "被代替");
    }

    @Override
    public List<String> getFieldOptions() {
        return Arrays.asList("医疗", "医保", "医药", "公卫", "医养", "其他");
    }
}