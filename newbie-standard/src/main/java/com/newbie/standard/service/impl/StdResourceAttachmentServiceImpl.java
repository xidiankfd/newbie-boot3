package com.newbie.standard.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbie.standard.domain.entity.StdResourceAttachment;
import com.newbie.standard.mapper.StdResourceAttachmentMapper;
import com.newbie.standard.service.StdResourceAttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标准资源附件表 Service实现
 *
 * @author Claude
 */
@Service
@RequiredArgsConstructor
public class StdResourceAttachmentServiceImpl extends ServiceImpl<StdResourceAttachmentMapper, StdResourceAttachment> 
        implements StdResourceAttachmentService {

    private final StdResourceAttachmentMapper stdResourceAttachmentMapper;

    @Override
    public List<StdResourceAttachment> listByResourceId(String resourceId) {
        return stdResourceAttachmentMapper.selectByResourceId(resourceId);
    }

    @Override
    public StdResourceAttachment getMainFileByResourceId(String resourceId) {
        return stdResourceAttachmentMapper.selectMainFileByResourceId(resourceId);
    }

    @Override
    public List<StdResourceAttachment> listByTypeAndResourceId(String resourceId, String attachmentType) {
        return stdResourceAttachmentMapper.selectByTypeAndResourceId(resourceId, attachmentType);
    }

    @Override
    public boolean add(StdResourceAttachment attachment) {
        return this.save(attachment);
    }

    @Override
    public boolean update(StdResourceAttachment attachment) {
        if (attachment.getId() == null) {
            throw new RuntimeException("附件ID不能为空");
        }
        return this.updateById(attachment);
    }

    @Override
    public boolean deleteBatch(List<String> ids) {
        return this.removeByIds(ids);
    }

    @Override
    public boolean deleteByResourceId(String resourceId) {
        LambdaQueryWrapper<StdResourceAttachment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StdResourceAttachment::getResourceId, resourceId);
        return this.remove(queryWrapper);
    }

    @Override
    public Long countByResourceId(String resourceId) {
        return stdResourceAttachmentMapper.countByResourceId(resourceId);
    }
}