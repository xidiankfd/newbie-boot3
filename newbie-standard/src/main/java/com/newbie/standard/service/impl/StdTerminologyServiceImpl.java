package com.newbie.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbie.standard.domain.entity.StdTerminology;
import com.newbie.standard.mapper.StdTerminologyMapper;
import com.newbie.standard.service.StdTerminologyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 术语定义表 Service实现
 *
 * @author Claude
 */
@Service
@RequiredArgsConstructor
public class StdTerminologyServiceImpl extends ServiceImpl<StdTerminologyMapper, StdTerminology> 
        implements StdTerminologyService {

    private final StdTerminologyMapper stdTerminologyMapper;

    @Override
    public List<StdTerminology> listByResourceId(String resourceId) {
        return stdTerminologyMapper.selectByResourceId(resourceId);
    }

    @Override
    public List<StdTerminology> listByResourceIdAndCategory(String resourceId, String termCategory) {
        return stdTerminologyMapper.selectByResourceIdAndCategory(resourceId, termCategory);
    }

    @Override
    public List<StdTerminology> listByTermName(String termName) {
        return stdTerminologyMapper.selectByTermName(termName);
    }

    @Override
    public List<StdTerminology> listByResourceIdOrderBySort(String resourceId) {
        return stdTerminologyMapper.selectByResourceIdOrderBySort(resourceId);
    }

    @Override
    public List<String> getCategoriesByResourceId(String resourceId) {
        return stdTerminologyMapper.selectCategoriesByResourceId(resourceId);
    }

    @Override
    public boolean add(StdTerminology terminology) {
        return this.save(terminology);
    }

    @Override
    public boolean update(StdTerminology terminology) {
        if (terminology.getId() == null) {
            throw new RuntimeException("术语ID不能为空");
        }
        return this.updateById(terminology);
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean deleteByResourceId(String resourceId) {
        LambdaQueryWrapper<StdTerminology> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StdTerminology::getResourceId, resourceId);
        return this.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchImport(List<StdTerminology> terminologyList) {
        return this.saveBatch(terminologyList);
    }
}