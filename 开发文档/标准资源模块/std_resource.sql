-- 标准资源主表 (std_resource)
-- 存储标准的基本信息
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