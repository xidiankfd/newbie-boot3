-- 文件解读信息表 (std_interpretation)
-- 存储标准的解读文档信息
CREATE TABLE std_interpretation (
    id                     BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    resource_id           BIGINT NOT NULL COMMENT '对应标准资源ID',
    source_type           VARCHAR(20) NOT NULL COMMENT '来源类型:文件/网站',
    source_title          VARCHAR(255) NOT NULL COMMENT '解读标题 如:《疾病控制基本数据集第13部分：职业病危害因素监测》标准解读',
    source_url            VARCHAR(512) COMMENT '文件下载链接或官方网址',
    source_description    TEXT COMMENT '解读内容描述',
    upload_date           DATE COMMENT '上传日期',
    file_size             VARCHAR(20) COMMENT '文件大小',
    file_format           VARCHAR(20) COMMENT '文件格式:pdf/doc/html等',
    is_official           TINYINT(1) DEFAULT 0 COMMENT '是否官方解读:0-否,1-是',
    view_count            INT DEFAULT 0 COMMENT '查看次数',
    
    -- 审计字段
    create_by      BIGINT      COMMENT '创建者ID',
    create_time    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by      BIGINT      COMMENT '更新者ID', 
    update_time    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        TINYINT(1)  DEFAULT 0 COMMENT '逻辑删除:0-未删除,1-已删除',
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE,
    INDEX idx_resource_id (resource_id),
    INDEX idx_source_type (source_type),
    INDEX idx_upload_date (upload_date),
    INDEX idx_is_official (is_official),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件解读信息表';