package com.newbie.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newbie.standard.domain.entity.StdResourceAttachment;

import java.util.List;

/**
 * 标准资源附件表 Service接口
 *
 * @author Claude
 */
public interface StdResourceAttachmentService extends IService<StdResourceAttachment> {

    /**
     * 根据资源ID查询附件列表
     *
     * @param resourceId 标准资源ID
     * @return 附件列表
     */
    List<StdResourceAttachment> listByResourceId(String resourceId);

    /**
     * 根据资源ID查询主文件
     *
     * @param resourceId 标准资源ID
     * @return 主文件附件
     */
    StdResourceAttachment getMainFileByResourceId(String resourceId);

    /**
     * 根据附件类型查询附件列表
     *
     * @param resourceId     标准资源ID
     * @param attachmentType 附件类型
     * @return 附件列表
     */
    List<StdResourceAttachment> listByTypeAndResourceId(String resourceId, String attachmentType);

    /**
     * 新增标准资源附件
     *
     * @param attachment 附件对象
     * @return 是否成功
     */
    boolean add(StdResourceAttachment attachment);

    /**
     * 修改标准资源附件
     *
     * @param attachment 附件对象
     * @return 是否成功
     */
    boolean update(StdResourceAttachment attachment);

    /**
     * 批量删除附件
     *
     * @param ids ID列表
     * @return 是否成功
     */
    boolean deleteBatch(List<String> ids);

    /**
     * 根据资源ID删除所有附件
     *
     * @param resourceId 标准资源ID
     * @return 是否成功
     */
    boolean deleteByResourceId(String resourceId);

    /**
     * 统计资源的附件数量
     *
     * @param resourceId 标准资源ID
     * @return 附件数量
     */
    Long countByResourceId(String resourceId);
}