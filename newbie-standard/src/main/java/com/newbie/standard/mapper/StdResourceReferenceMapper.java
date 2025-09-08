package com.newbie.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbie.standard.domain.entity.StdResourceReference;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标准资源引用关联表 Mapper接口
 *
 * @author Claude
 */
@Mapper
public interface StdResourceReferenceMapper extends BaseMapper<StdResourceReference> {

    /**
     * 根据资源ID查询引用关系列表
     *
     * @param resourceId 标准资源ID
     * @return 引用关系列表
     */
    List<StdResourceReference> selectByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据引用类型查询引用关系列表
     *
     * @param resourceId    标准资源ID
     * @param referenceType 引用类型:引用/被引用
     * @return 引用关系列表
     */
    List<StdResourceReference> selectByResourceIdAndType(@Param("resourceId") String resourceId, 
                                                        @Param("referenceType") String referenceType);

    /**
     * 查询引用了指定资源的其他标准
     *
     * @param referenceId 被引用资源ID
     * @return 引用关系列表
     */
    List<StdResourceReference> selectByReferenceId(@Param("referenceId") String referenceId);

    /**
     * 根据资源ID和排序查询引用关系列表
     *
     * @param resourceId 标准资源ID
     * @return 引用关系列表(按排序字段升序)
     */
    List<StdResourceReference> selectByResourceIdOrderBySort(@Param("resourceId") String resourceId);

    /**
     * 统计资源的引用数量
     *
     * @param resourceId    标准资源ID
     * @param referenceType 引用类型:引用/被引用
     * @return 引用数量
     */
    Long countByResourceIdAndType(@Param("resourceId") String resourceId, 
                                  @Param("referenceType") String referenceType);
}