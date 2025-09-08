-- 术语定义表 (std_terminology)
-- 存储标准中的术语和定义
CREATE TABLE std_terminology (
    id                     BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    resource_id           BIGINT NOT NULL COMMENT '对应标准资源ID',
    term_name             VARCHAR(255) NOT NULL COMMENT '术语 如:基本数据集',
    english_term          VARCHAR(255) COMMENT '英文对应词 如:Basic dataset',
    synonyms              VARCHAR(500) COMMENT '同义词，多个用逗号分隔',
    definition            TEXT COMMENT '术语定义',
    term_category         VARCHAR(100) COMMENT '术语分类',
    term_order            INT DEFAULT 0 COMMENT '术语排序',
    
    -- 审计字段
    create_by      BIGINT      COMMENT '创建者ID',
    create_time    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by      BIGINT      COMMENT '更新者ID', 
    update_time    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        TINYINT(1)  DEFAULT 0 COMMENT '逻辑删除:0-未删除,1-已删除',
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE,
    INDEX idx_resource_id (resource_id),
    INDEX idx_term_name (term_name),
    INDEX idx_term_category (term_category),
    INDEX idx_term_order (term_order),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='术语定义表';