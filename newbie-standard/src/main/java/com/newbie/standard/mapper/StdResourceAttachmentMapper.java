package com.newbie.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbie.standard.domain.entity.StdResourceAttachment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标准资源附件表 Mapper接口
 *
 * @author Claude
 */
@Mapper
public interface StdResourceAttachmentMapper extends BaseMapper<StdResourceAttachment> {

    /**
     * 根据资源ID查询附件列表
     *
     * @param resourceId 标准资源ID
     * @return 附件列表
     */
    List<StdResourceAttachment> selectByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据资源ID查询主文件
     *
     * @param resourceId 标准资源ID
     * @return 主文件附件
     */
    StdResourceAttachment selectMainFileByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据附件类型查询附件列表
     *
     * @param resourceId     标准资源ID
     * @param attachmentType 附件类型
     * @return 附件列表
     */
    List<StdResourceAttachment> selectByTypeAndResourceId(@Param("resourceId") String resourceId, 
                                                         @Param("attachmentType") String attachmentType);

    /**
     * 统计资源的附件数量
     *
     * @param resourceId 标准资源ID
     * @return 附件数量
     */
    Long countByResourceId(@Param("resourceId") String resourceId);
}