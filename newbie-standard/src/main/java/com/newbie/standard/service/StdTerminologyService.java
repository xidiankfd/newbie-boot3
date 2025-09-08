package com.newbie.standard.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newbie.standard.domain.entity.StdTerminology;

import java.util.List;

/**
 * 术语定义表 Service接口
 *
 * @author Claude
 */
public interface StdTerminologyService extends IService<StdTerminology> {

    /**
     * 根据资源ID查询术语列表
     *
     * @param resourceId 标准资源ID
     * @return 术语列表
     */
    List<StdTerminology> listByResourceId(String resourceId);

    /**
     * 根据术语分类查询术语列表
     *
     * @param resourceId   标准资源ID
     * @param termCategory 术语分类
     * @return 术语列表
     */
    List<StdTerminology> listByResourceIdAndCategory(String resourceId, String termCategory);

    /**
     * 根据术语名称查询术语
     *
     * @param termName 术语名称
     * @return 术语列表
     */
    List<StdTerminology> listByTermName(String termName);

    /**
     * 根据资源ID和排序查询术语列表
     *
     * @param resourceId 标准资源ID
     * @return 术语列表(按排序字段升序)
     */
    List<StdTerminology> listByResourceIdOrderBySort(String resourceId);

    /**
     * 获取术语分类列表
     *
     * @param resourceId 标准资源ID
     * @return 术语分类列表
     */
    List<String> getCategoriesByResourceId(String resourceId);

    /**
     * 新增术语
     *
     * @param terminology 术语对象
     * @return 是否成功
     */
    boolean add(StdTerminology terminology);

    /**
     * 修改术语
     *
     * @param terminology 术语对象
     * @return 是否成功
     */
    boolean update(StdTerminology terminology);

    /**
     * 批量删除术语
     *
     * @param ids ID列表
     * @return 是否成功
     */
    boolean deleteBatch(List<String> ids);

    /**
     * 根据资源ID删除所有术语
     *
     * @param resourceId 标准资源ID
     * @return 是否成功
     */
    boolean deleteByResourceId(String resourceId);

    /**
     * 批量导入术语
     *
     * @param terminologyList 术语列表
     * @return 是否成功
     */
    boolean batchImport(List<StdTerminology> terminologyList);
}