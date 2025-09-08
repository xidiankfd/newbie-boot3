package com.newbie.standard.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newbie.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 标准资源主表
 *
 * @author Claude
 * @TableName std_resource
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "std_resource")
@Data
public class StdResource extends BaseEntity {

    /**
     * 标准编号 如:GB/T 14396-2016
     */
    private String resourceCode;

    /**
     * 标准中文名称 如:疾病分类与代码
     */
    private String chineseName;

    /**
     * 标准英文名称 如:Disease Classification and Codes
     */
    private String englishName;

    /**
     * 标准状态:现行/废止/即将实施/被代替
     */
    private String standardStatus;

    /**
     * 标准类型:国家标准/行业标准/地方标准/团体标准等
     */
    private String standardType;

    /**
     * 标准性质:强制性/推荐性/指导性技术文件
     */
    private String standardNature;

    /**
     * 发布机构 如:国家卫生健康委员会
     */
    private String publishOrganization;

    /**
     * 发布日期
     */
    private LocalDate publishDate;

    /**
     * 实施日期
     */
    private LocalDate implementDate;

    /**
     * 废止日期
     */
    private LocalDate revokeDate;

    /**
     * 归口单位
     */
    private String competentUnit;

    /**
     * 中国标准分类号(CCS) 如:C50
     */
    private String ccsCode;

    /**
     * 国际标准分类号(ICS) 如:11.020
     */
    private String icsCode;

    /**
     * 替代标准
     */
    private String replaceStandard;

    /**
     * 被替代标准
     */
    private String replacedStandard;

    /**
     * 是否包含专利信息:false-否,true-是
     */
    private Boolean hasPatentInfo;

    /**
     * 附注
     */
    private String notes;

    /**
     * 适用范围/摘要
     */
    private String scopeSummary;

    /**
     * 关键词
     */
    private String keywords;

    /**
     * 标准领域:医疗/医保/医药/公卫/医养/其他
     */
    private String standardField;

    /**
     * 标准分类:分类与编码/术语/数据元/接口规范等
     */
    private String standardClassification;

    /**
     * 文件原文链接
     */
    private String originalFileUrl;

    /**
     * 数字化内容包链接
     */
    private String digitalContentUrl;
}