-- 标准资源附件表 (std_resource_attachment)
-- 存储标准的数字化内容文件和附件信息
CREATE TABLE std_resource_attachment (
    id                     BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    resource_id           BIGINT NOT NULL COMMENT '对应标准资源ID',
    attachment_name       VARCHAR(255) NOT NULL COMMENT '数字化内容文件名 如:GBT14396-2016疾病分类与代码.pdf',
    attachment_type       VARCHAR(50) COMMENT '附件类型:PDF文档/图片/表格/压缩包等',
    file_size             VARCHAR(20) COMMENT '数字化内容大小 如:54.36 MB',
    file_size_bytes       BIGINT COMMENT '文件大小(字节)',
    upload_date           DATE COMMENT '数字化内容上传日期',
    download_url          VARCHAR(512) COMMENT '数字化内容下载链接',
    file_path             VARCHAR(512) COMMENT '文件存储路径',
    file_format           VARCHAR(20) COMMENT '文件格式:pdf/jpg/png/xlsx/zip等',
    is_main_file          TINYINT(1) DEFAULT 0 COMMENT '是否主文件:0-否,1-是',
    
    -- 审计字段
    create_by      BIGINT      COMMENT '创建者ID',
    create_time    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by      BIGINT      COMMENT '更新者ID', 
    update_time    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        TINYINT(1)  DEFAULT 0 COMMENT '逻辑删除:0-未删除,1-已删除',
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE,
    INDEX idx_resource_id (resource_id),
    INDEX idx_attachment_type (attachment_type),
    INDEX idx_upload_date (upload_date),
    INDEX idx_is_main_file (is_main_file),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标准资源附件表';