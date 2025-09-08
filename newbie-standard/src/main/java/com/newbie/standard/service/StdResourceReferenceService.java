package com.newbie.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newbie.standard.domain.entity.StdResourceReference;

import java.util.List;

/**
 * 标准资源引用关联表 Service接口
 *
 * @author Claude
 */
public interface StdResourceReferenceService extends IService<StdResourceReference> {

    /**
     * 根据资源ID查询引用关系列表
     *
     * @param resourceId 标准资源ID
     * @return 引用关系列表
     */
    List<StdResourceReference> listByResourceId(String resourceId);

    /**
     * 根据引用类型查询引用关系列表
     *
     * @param resourceId    标准资源ID
     * @param referenceType 引用类型:引用/被引用
     * @return 引用关系列表
     */
    List<StdResourceReference> listByResourceIdAndType(String resourceId, String referenceType);

    /**
     * 查询引用了指定资源的其他标准
     *
     * @param referenceId 被引用资源ID
     * @return 引用关系列表
     */
    List<StdResourceReference> listByReferenceId(String referenceId);

    /**
     * 根据资源ID和排序查询引用关系列表
     *
     * @param resourceId 标准资源ID
     * @return 引用关系列表(按排序字段升序)
     */
    List<StdResourceReference> listByResourceIdOrderBySort(String resourceId);

    /**
     * 新增引用关系
     *
     * @param reference 引用关系对象
     * @return 是否成功
     */
    boolean add(StdResourceReference reference);

    /**
     * 修改引用关系
     *
     * @param reference 引用关系对象
     * @return 是否成功
     */
    boolean update(StdResourceReference reference);

    /**
     * 批量删除引用关系
     *
     * @param ids ID列表
     * @return 是否成功
     */
    boolean deleteBatch(List<String> ids);

    /**
     * 根据资源ID删除所有引用关系
     *
     * @param resourceId 标准资源ID
     * @return 是否成功
     */
    boolean deleteByResourceId(String resourceId);

    /**
     * 统计资源的引用数量
     *
     * @param resourceId    标准资源ID
     * @param referenceType 引用类型:引用/被引用
     * @return 引用数量
     */
    Long countByResourceIdAndType(String resourceId, String referenceType);

    /**
     * 批量新增引用关系
     *
     * @param referenceList 引用关系列表
     * @return 是否成功
     */
    boolean batchAdd(List<StdResourceReference> referenceList);
}