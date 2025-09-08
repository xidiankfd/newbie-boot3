package com.newbie.standard.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbie.standard.domain.entity.StdResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 标准资源主表 Mapper接口
 *
 * @author Claude
 */
@Mapper
public interface StdResourceMapper extends BaseMapper<StdResource> {

    /**
     * 根据标准编号查询标准资源
     *
     * @param resourceCode 标准编号
     * @return 标准资源
     */
    StdResource selectByResourceCode(@Param("resourceCode") String resourceCode);

    /**
     * 根据标准状态查询标准资源列表
     *
     * @param standardStatus 标准状态
     * @return 标准资源列表
     */
    List<StdResource> selectByStatus(@Param("standardStatus") String standardStatus);

    /**
     * 根据标准领域查询标准资源列表
     *
     * @param standardField 标准领域
     * @return 标准资源列表
     */
    List<StdResource> selectByField(@Param("standardField") String standardField);

    /**
     * 根据标准分类查询标准资源列表
     *
     * @param standardClassification 标准分类
     * @return 标准资源列表
     */
    List<StdResource> selectByClassification(@Param("standardClassification") String standardClassification);

    /**
     * 多条件搜索标准资源
     *
     * @param stdResource 查询条件
     * @return 标准资源列表
     */
    List<StdResource> selectByConditions(@Param("resource") StdResource stdResource);
}