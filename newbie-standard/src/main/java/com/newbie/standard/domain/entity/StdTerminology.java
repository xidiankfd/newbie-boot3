package com.newbie.standard.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newbie.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 术语定义表
 *
 * @author Claude
 * @TableName std_terminology
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "std_terminology")
@Data
public class StdTerminology extends BaseEntity {

    /**
     * 对应标准资源ID
     */
    private String resourceId;

    /**
     * 术语 如:基本数据集
     */
    private String termName;

    /**
     * 英文对应词 如:Basic dataset
     */
    private String englishTerm;

    /**
     * 同义词，多个用逗号分隔
     */
    private String synonyms;

    /**
     * 术语定义
     */
    private String definition;

    /**
     * 术语分类
     */
    private String termCategory;

    /**
     * 术语排序
     */
    private Integer termOrder;
}