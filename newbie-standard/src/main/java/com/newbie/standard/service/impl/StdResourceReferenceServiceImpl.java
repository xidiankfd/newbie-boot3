package com.newbie.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbie.standard.domain.entity.StdResourceReference;
import com.newbie.standard.mapper.StdResourceReferenceMapper;
import com.newbie.standard.service.StdResourceReferenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 标准资源引用关联表 Service实现
 *
 * @author Claude
 */
@Service
@RequiredArgsConstructor
public class StdResourceReferenceServiceImpl extends ServiceImpl<StdResourceReferenceMapper, StdResourceReference> 
        implements StdResourceReferenceService {

    private final StdResourceReferenceMapper stdResourceReferenceMapper;

    @Override
    public List<StdResourceReference> listByResourceId(String resourceId) {
        return stdResourceReferenceMapper.selectByResourceId(resourceId);
    }

    @Override
    public List<StdResourceReference> listByResourceIdAndType(String resourceId, String referenceType) {
        return stdResourceReferenceMapper.selectByResourceIdAndType(resourceId, referenceType);
    }

    @Override
    public List<StdResourceReference> listByReferenceId(String referenceId) {
        return stdResourceReferenceMapper.selectByReferenceId(referenceId);
    }

    @Override
    public List<StdResourceReference> listByResourceIdOrderBySort(String resourceId) {
        return stdResourceReferenceMapper.selectByResourceIdOrderBySort(resourceId);
    }

    @Override
    public boolean add(StdResourceReference reference) {
        return this.save(reference);
    }

    @Override
    public boolean update(StdResourceReference reference) {
        if (reference.getId() == null) {
            throw new RuntimeException("引用关系ID不能为空");
        }
        return this.updateById(reference);
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean deleteByResourceId(String resourceId) {
        LambdaQueryWrapper<StdResourceReference> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StdResourceReference::getResourceId, resourceId);
        return this.remove(queryWrapper);
    }

    @Override
    public Long countByResourceIdAndType(String resourceId, String referenceType) {
        return stdResourceReferenceMapper.countByResourceIdAndType(resourceId, referenceType);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchAdd(List<StdResourceReference> referenceList) {
        return this.saveBatch(referenceList);
    }
}