package com.newbie.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbie.standard.domain.entity.StdTerminology;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 术语定义表 Mapper接口
 *
 * @author Claude
 */
@Mapper
public interface StdTerminologyMapper extends BaseMapper<StdTerminology> {

    /**
     * 根据资源ID查询术语列表
     *
     * @param resourceId 标准资源ID
     * @return 术语列表
     */
    List<StdTerminology> selectByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据术语分类查询术语列表
     *
     * @param resourceId    标准资源ID
     * @param termCategory  术语分类
     * @return 术语列表
     */
    List<StdTerminology> selectByResourceIdAndCategory(@Param("resourceId") String resourceId, 
                                                       @Param("termCategory") String termCategory);

    /**
     * 根据术语名称查询术语
     *
     * @param termName 术语名称
     * @return 术语列表
     */
    List<StdTerminology> selectByTermName(@Param("termName") String termName);

    /**
     * 根据资源ID和排序查询术语列表
     *
     * @param resourceId 标准资源ID
     * @return 术语列表(按排序字段升序)
     */
    List<StdTerminology> selectByResourceIdOrderBySort(@Param("resourceId") String resourceId);

    /**
     * 获取术语分类列表
     *
     * @param resourceId 标准资源ID
     * @return 术语分类列表
     */
    List<String> selectCategoriesByResourceId(@Param("resourceId") String resourceId);
}