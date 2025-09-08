-- 标准资源引用关联表 (std_resource_reference)
-- 存储标准之间的引用关系
CREATE TABLE std_resource_reference (
    id                     BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    resource_id           BIGINT NOT NULL COMMENT '标准资源ID',
    resource_name         VARCHAR(255) NOT NULL COMMENT '标准资源名称 如:WS 370-2012《卫生信息基本数据集编制规范》',
    reference_id          BIGINT COMMENT '引用文件ID',
    reference_name        VARCHAR(255) NOT NULL COMMENT '文件名称 如:WS 363.1 卫生信息数据元目录 第1部分: 总则',
    reference_type        VARCHAR(20) NOT NULL COMMENT '引用类型:引用/被引用',
    reference_code        VARCHAR(64) COMMENT '引用文件编号',
    reference_order       INT DEFAULT 0 COMMENT '引用排序',
    
    -- 审计字段
    create_by      BIGINT      COMMENT '创建者ID',
    create_time    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by      BIGINT      COMMENT '更新者ID', 
    update_time    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        TINYINT(1)  DEFAULT 0 COMMENT '逻辑删除:0-未删除,1-已删除',
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE,
    FOREIGN KEY (reference_id) REFERENCES std_resource(id) ON DELETE SET NULL,
    INDEX idx_resource_id (resource_id),
    INDEX idx_reference_id (reference_id),
    INDEX idx_reference_type (reference_type),
    INDEX idx_reference_order (reference_order),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标准资源引用关联表';