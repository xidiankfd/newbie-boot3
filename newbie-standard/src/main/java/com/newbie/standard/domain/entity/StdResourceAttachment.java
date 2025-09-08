package com.newbie.standard.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.newbie.common.domain.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 标准资源附件表
 *
 * @author Claude
 * @TableName std_resource_attachment
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "std_resource_attachment")
@Data
public class StdResourceAttachment extends BaseEntity {

    /**
     * 对应标准资源ID
     */
    private String resourceId;

    /**
     * 数字化内容文件名 如:GBT14396-2016疾病分类与代码.pdf
     */
    private String attachmentName;

    /**
     * 附件类型:PDF文档/图片/表格/压缩包等
     */
    private String attachmentType;

    /**
     * 数字化内容大小 如:54.36 MB
     */
    private String fileSize;

    /**
     * 文件大小(字节)
     */
    private Long fileSizeBytes;

    /**
     * 数字化内容上传日期
     */
    private LocalDate uploadDate;

    /**
     * 数字化内容下载链接
     */
    private String downloadUrl;

    /**
     * 文件存储路径
     */
    private String filePath;

    /**
     * 文件格式:pdf/jpg/png/xlsx/zip等
     */
    private String fileFormat;

    /**
     * 是否主文件:false-否,true-是
     */
    private Boolean isMainFile;
}