package com.newbie.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbie.standard.domain.entity.StdInterpretation;
import com.newbie.standard.mapper.StdInterpretationMapper;
import com.newbie.standard.service.StdInterpretationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件解读信息表 Service实现
 *
 * @author Claude
 */
@Service
@RequiredArgsConstructor
public class StdInterpretationServiceImpl extends ServiceImpl<StdInterpretationMapper, StdInterpretation> 
        implements StdInterpretationService {

    private final StdInterpretationMapper stdInterpretationMapper;

    @Override
    public List<StdInterpretation> listByResourceId(String resourceId) {
        return stdInterpretationMapper.selectByResourceId(resourceId);
    }

    @Override
    public List<StdInterpretation> listByResourceIdAndSourceType(String resourceId, String sourceType) {
        return stdInterpretationMapper.selectByResourceIdAndSourceType(resourceId, sourceType);
    }

    @Override
    public List<StdInterpretation> listOfficialByResourceId(String resourceId) {
        return stdInterpretationMapper.selectOfficialByResourceId(resourceId);
    }

    @Override
    public boolean add(StdInterpretation interpretation) {
        return this.save(interpretation);
    }

    @Override
    public boolean update(StdInterpretation interpretation) {
        if (interpretation.getId() == null) {
            throw new RuntimeException("解读信息ID不能为空");
        }
        return this.updateById(interpretation);
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean deleteByResourceId(String resourceId) {
        LambdaQueryWrapper<StdInterpretation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StdInterpretation::getResourceId, resourceId);
        return this.remove(queryWrapper);
    }

    @Override
    public boolean incrementViewCount(String id) {
        try {
            stdInterpretationMapper.incrementViewCount(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Long countByResourceId(String resourceId) {
        return stdInterpretationMapper.countByResourceId(resourceId);
    }

    @Override
    public List<StdInterpretation> listByTitleLike(String title) {
        return stdInterpretationMapper.selectByTitleLike(title);
    }
}