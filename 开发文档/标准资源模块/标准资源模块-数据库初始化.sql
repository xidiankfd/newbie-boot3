-- ==========================================
-- 标准资源模块 - 数据库初始化脚本
-- 创建时间: 2025-09-05
-- 说明: 包含标准资源管理所需的全部数据表
-- ==========================================

-- 1. 标准资源主表 (std_resource)
-- 存储标准的基本信息
DROP TABLE IF EXISTS std_resource;
CREATE TABLE std_resource (
    id                          BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '主键ID',
    resource_code              VARCHAR(64) NOT NULL UNIQUE COMMENT '标准编号 如:GB/T 14396-2016',
    chinese_name               VARCHAR(255) NOT NULL COMMENT '标准中文名称 如:疾病分类与代码',
    english_name               VARCHAR(255) COMMENT '标准英文名称 如:Disease Classification and Codes',
    standard_status            VARCHAR(20) NOT NULL COMMENT '标准状态:现行/废止/即将实施/被代替',
    standard_type              VARCHAR(50) NOT NULL COMMENT '标准类型:国家标准/行业标准/地方标准/团体标准等',
    standard_nature            VARCHAR(20) NOT NULL COMMENT '标准性质:强制性/推荐性/指导性技术文件',
    publish_organization       VARCHAR(255) NOT NULL COMMENT '发布机构 如:国家卫生健康委员会',
    publish_date               DATE COMMENT '发布日期',
    implement_date             DATE COMMENT '实施日期',
    revoke_date                DATE COMMENT '废止日期',
    competent_unit             VARCHAR(255) COMMENT '归口单位',
    ccs_code                   VARCHAR(20) COMMENT '中国标准分类号(CCS) 如:C50',
    ics_code                   VARCHAR(20) COMMENT '国际标准分类号(ICS) 如:11.020',
    replace_standard           VARCHAR(512) COMMENT '替代标准',
    replaced_standard          VARCHAR(512) COMMENT '被替代标准',
    has_patent_info            TINYINT(1) DEFAULT 0 COMMENT '是否包含专利信息:0-否,1-是',
    notes                      TEXT COMMENT '附注',
    scope_summary              TEXT NOT NULL COMMENT '适用范围/摘要',
    keywords                   VARCHAR(255) COMMENT '关键词',
    standard_field             VARCHAR(64) NOT NULL COMMENT '标准领域:医疗/医保/医药/公卫/医养/其他',
    standard_classification    VARCHAR(64) NOT NULL COMMENT '标准分类:分类与编码/术语/数据元/接口规范等',
    original_file_url          VARCHAR(512) COMMENT '文件原文链接',
    digital_content_url        VARCHAR(512) COMMENT '数字化内容包链接',
    
    -- 审计字段
    create_by      BIGINT      COMMENT '创建者ID',
    create_time    DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_by      BIGINT      COMMENT '更新者ID', 
    update_time    DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted        TINYINT(1)  DEFAULT 0 COMMENT '逻辑删除:0-未删除,1-已删除',
    
    INDEX idx_resource_code (resource_code),
    INDEX idx_standard_status (standard_status),
    INDEX idx_standard_type (standard_type),
    INDEX idx_standard_field (standard_field),
    INDEX idx_create_time (create_time),
    INDEX idx_deleted (deleted)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标准资源主表';

-- 2. 标准资源附件表 (std_resource_attachment)
-- 存储标准的数字化内容文件和附件信息
DROP TABLE IF EXISTS std_resource_attachment;
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

-- 3. 术语定义表 (std_terminology)
-- 存储标准中的术语和定义
DROP TABLE IF EXISTS std_terminology;
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

-- 4. 标准资源引用关联表 (std_resource_reference)
-- 存储标准之间的引用关系
DROP TABLE IF EXISTS std_resource_reference;
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

-- 5. 文件解读信息表 (std_interpretation)
-- 存储标准的解读文档信息
DROP TABLE IF EXISTS std_interpretation;
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

-- ==========================================
-- 数据库初始化完成
-- 包含5个主要数据表:
-- 1. std_resource - 标准资源主表
-- 2. std_resource_attachment - 标准资源附件表
-- 3. std_terminology - 术语定义表  
-- 4. std_resource_reference - 标准资源引用关联表
-- 5. std_interpretation - 文件解读信息表
-- ==========================================