package com.newbie.standard.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newbie.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 文件解读信息表
 *
 * @author Claude
 * @TableName std_interpretation
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "std_interpretation")
@Data
public class StdInterpretation extends BaseEntity {

    /**
     * 对应标准资源ID
     */
    private String resourceId;

    /**
     * 来源类型:文件/网站
     */
    private String sourceType;

    /**
     * 解读标题 如:《疾病控制基本数据集第13部分：职业病危害因素监测》标准解读
     */
    private String sourceTitle;

    /**
     * 文件下载链接或官方网址
     */
    private String sourceUrl;

    /**
     * 解读内容描述
     */
    private String sourceDescription;

    /**
     * 上传日期
     */
    private LocalDate uploadDate;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 文件格式:pdf/doc/html等
     */
    private String fileFormat;

    /**
     * 是否官方解读:false-否,true-是
     */
    private Boolean isOfficial;

    /**
     * 查看次数
     */
    private Integer viewCount;
}