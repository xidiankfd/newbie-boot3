package com.newbie.standard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.newbie.standard.domain.entity.StdResource;

import java.util.List;

/**
 * 标准资源主表 Service接口
 *
 * @author Claude
 */
public interface StdResourceService extends IService<StdResource> {

    /**
     * 分页查询标准资源列表
     *
     * @param page        分页参数
     * @param stdResource 查询条件
     * @return 分页结果
     */
    Page<StdResource> selectPage(Page<StdResource> page, StdResource stdResource);

    /**
     * 根据标准编号查询标准资源
     *
     * @param resourceCode 标准编号
     * @return 标准资源
     */
    StdResource getByResourceCode(String resourceCode);

    /**
     * 根据标准状态查询标准资源列表
     *
     * @param standardStatus 标准状态
     * @return 标准资源列表
     */
    List<StdResource> listByStatus(String standardStatus);

    /**
     * 根据标准领域查询标准资源列表
     *
     * @param standardField 标准领域
     * @return 标准资源列表
     */
    List<StdResource> listByField(String standardField);

    /**
     * 根据标准分类查询标准资源列表
     *
     * @param standardClassification 标准分类
     * @return 标准资源列表
     */
    List<StdResource> listByClassification(String standardClassification);

    /**
     * 多条件搜索标准资源
     *
     * @param stdResource 查询条件
     * @return 标准资源列表
     */
    List<StdResource> listByConditions(StdResource stdResource);

    /**
     * 新增标准资源
     *
     * @param stdResource 标准资源对象
     * @return 是否成功
     */
    boolean add(StdResource stdResource);

    /**
     * 修改标准资源
     *
     * @param stdResource 标准资源对象
     * @return 是否成功
     */
    boolean update(StdResource stdResource);

    /**
     * 批量删除标准资源
     *
     * @param ids ID列表
     * @return 是否成功
     */
    boolean deleteBatch(List<String> ids);

    /**
     * 获取标准状态枚举值
     *
     * @return 标准状态列表
     */
    List<String> getStatusOptions();

    /**
     * 获取标准领域枚举值
     *
     * @return 标准领域列表
     */
    List<String> getFieldOptions();
}