package com.newbie.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbie.standard.domain.entity.StdInterpretation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文件解读信息表 Mapper接口
 *
 * @author Claude
 */
@Mapper
public interface StdInterpretationMapper extends BaseMapper<StdInterpretation> {

    /**
     * 根据资源ID查询解读信息列表
     *
     * @param resourceId 标准资源ID
     * @return 解读信息列表
     */
    List<StdInterpretation> selectByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据来源类型查询解读信息列表
     *
     * @param resourceId 标准资源ID
     * @param sourceType 来源类型:文件/网站
     * @return 解读信息列表
     */
    List<StdInterpretation> selectByResourceIdAndSourceType(@Param("resourceId") String resourceId, 
                                                           @Param("sourceType") String sourceType);

    /**
     * 查询官方解读信息列表
     *
     * @param resourceId 标准资源ID
     * @return 官方解读信息列表
     */
    List<StdInterpretation> selectOfficialByResourceId(@Param("resourceId") String resourceId);

    /**
     * 更新查看次数
     *
     * @param id 解读信息ID
     */
    void incrementViewCount(@Param("id") String id);

    /**
     * 统计资源的解读数量
     *
     * @param resourceId 标准资源ID
     * @return 解读数量
     */
    Long countByResourceId(@Param("resourceId") String resourceId);

    /**
     * 根据标题模糊查询解读信息
     *
     * @param title 解读标题关键字
     * @return 解读信息列表
     */
    List<StdInterpretation> selectByTitleLike(@Param("title") String title);
}