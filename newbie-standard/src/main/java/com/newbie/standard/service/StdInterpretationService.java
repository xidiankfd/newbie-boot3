package com.newbie.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newbie.standard.domain.entity.StdInterpretation;

import java.util.List;

/**
 * 文件解读信息表 Service接口
 *
 * @author Claude
 */
public interface StdInterpretationService extends IService<StdInterpretation> {

    /**
     * 根据资源ID查询解读信息列表
     *
     * @param resourceId 标准资源ID
     * @return 解读信息列表
     */
    List<StdInterpretation> listByResourceId(String resourceId);

    /**
     * 根据来源类型查询解读信息列表
     *
     * @param resourceId 标准资源ID
     * @param sourceType 来源类型:文件/网站
     * @return 解读信息列表
     */
    List<StdInterpretation> listByResourceIdAndSourceType(String resourceId, String sourceType);

    /**
     * 查询官方解读信息列表
     *
     * @param resourceId 标准资源ID
     * @return 官方解读信息列表
     */
    List<StdInterpretation> listOfficialByResourceId(String resourceId);

    /**
     * 新增解读信息
     *
     * @param interpretation 解读信息对象
     * @return 是否成功
     */
    boolean add(StdInterpretation interpretation);

    /**
     * 修改解读信息
     *
     * @param interpretation 解读信息对象
     * @return 是否成功
     */
    boolean update(StdInterpretation interpretation);

    /**
     * 批量删除解读信息
     *
     * @param ids ID列表
     * @return 是否成功
     */
    boolean deleteBatch(List<String> ids);

    /**
     * 根据资源ID删除所有解读信息
     *
     * @param resourceId 标准资源ID
     * @return 是否成功
     */
    boolean deleteByResourceId(String resourceId);

    /**
     * 更新查看次数
     *
     * @param id 解读信息ID
     * @return 是否成功
     */
    boolean incrementViewCount(String id);

    /**
     * 统计资源的解读数量
     *
     * @param resourceId 标准资源ID
     * @return 解读数量
     */
    Long countByResourceId(String resourceId);

    /**
     * 根据标题模糊查询解读信息
     *
     * @param title 解读标题关键字
     * @return 解读信息列表
     */
    List<StdInterpretation> listByTitleLike(String title);
}