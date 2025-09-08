package com.newbie.standard.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newbie.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标准资源引用关联表
 *
 * @author Claude
 * @TableName std_resource_reference
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "std_resource_reference")
@Data
public class StdResourceReference extends BaseEntity {

    /**
     * 标准资源ID
     */
    private String resourceId;

    /**
     * 标准资源名称 如:WS 370-2012《卫生信息基本数据集编制规范》
     */
    private String resourceName;

    /**
     * 引用文件ID
     */
    private String referenceId;

    /**
     * 文件名称 如:WS 363.1 卫生信息数据元目录 第1部分: 总则
     */
    private String referenceName;

    /**
     * 引用类型:引用/被引用
     */
    private String referenceType;

    /**
     * 引用文件编号
     */
    private String referenceCode;

    /**
     * 引用排序
     */
    private Integer referenceOrder;
}