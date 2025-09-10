-- ==========================================
-- 标准资源模块 - PostgreSQL数据库初始化脚本
-- 创建时间: 2025-09-05
-- 说明: 包含标准资源管理所需的全部数据表(PostgreSQL版本)
-- ==========================================

-- 1. 标准资源主表 (std_resource)
-- 存储标准的基本信息
DROP TABLE IF EXISTS std_resource CASCADE;
CREATE TABLE std_resource (
    id                          BIGSERIAL PRIMARY KEY,
    resource_code              VARCHAR(64) NOT NULL UNIQUE,
    chinese_name               VARCHAR(255) NOT NULL,
    english_name               VARCHAR(255),
    standard_status            VARCHAR(20) NOT NULL,
    standard_type              VARCHAR(50) NOT NULL,
    standard_nature            VARCHAR(20) NOT NULL,
    publish_organization       VARCHAR(255) NOT NULL,
    publish_date               DATE,
    implement_date             DATE,
    revoke_date                DATE,
    competent_unit             VARCHAR(255),
    ccs_code                   VARCHAR(20),
    ics_code                   VARCHAR(20),
    replace_standard           VARCHAR(512),
    replaced_standard          VARCHAR(512),
    has_patent_info            BOOLEAN DEFAULT FALSE,
    notes                      TEXT,
    scope_summary              TEXT NOT NULL,
    keywords                   VARCHAR(255),
    standard_field             VARCHAR(64) NOT NULL,
    standard_classification    VARCHAR(64) NOT NULL,
    original_file_url          VARCHAR(512),
    digital_content_url        VARCHAR(512),
    
    -- 审计字段
    create_by      BIGINT,
    create_time    TIMESTAMP DEFAULT NOW(),
    update_by      BIGINT,
    update_time    TIMESTAMP DEFAULT NOW(),
    deleted        BOOLEAN DEFAULT FALSE
);

-- 标准资源主表注释
COMMENT ON TABLE std_resource IS '标准资源主表';
COMMENT ON COLUMN std_resource.id IS '主键ID';
COMMENT ON COLUMN std_resource.resource_code IS '标准编号 如:GB/T 14396-2016';
COMMENT ON COLUMN std_resource.chinese_name IS '标准中文名称 如:疾病分类与代码';
COMMENT ON COLUMN std_resource.english_name IS '标准英文名称 如:Disease Classification and Codes';
COMMENT ON COLUMN std_resource.standard_status IS '标准状态:现行/废止/即将实施/被代替';
COMMENT ON COLUMN std_resource.standard_type IS '标准类型:国家标准/行业标准/地方标准/团体标准等';
COMMENT ON COLUMN std_resource.standard_nature IS '标准性质:强制性/推荐性/指导性技术文件';
COMMENT ON COLUMN std_resource.publish_organization IS '发布机构 如:国家卫生健康委员会';
COMMENT ON COLUMN std_resource.has_patent_info IS '是否包含专利信息:false-否,true-是';
COMMENT ON COLUMN std_resource.scope_summary IS '适用范围/摘要';
COMMENT ON COLUMN std_resource.standard_field IS '标准领域:医疗/医保/医药/公卫/医养/其他';
COMMENT ON COLUMN std_resource.standard_classification IS '标准分类:分类与编码/术语/数据元/接口规范等';
COMMENT ON COLUMN std_resource.deleted IS '逻辑删除:false-未删除,true-已删除';

-- 2. 标准资源附件表 (std_resource_attachment)
-- 存储标准的数字化内容文件和附件信息
DROP TABLE IF EXISTS std_resource_attachment CASCADE;
CREATE TABLE std_resource_attachment (
    id                     BIGSERIAL PRIMARY KEY,
    resource_id           BIGINT NOT NULL,
    attachment_name       VARCHAR(255) NOT NULL,
    attachment_type       VARCHAR(50),
    file_size             VARCHAR(20),
    file_size_bytes       BIGINT,
    upload_date           DATE,
    download_url          VARCHAR(512),
    file_path             VARCHAR(512),
    file_format           VARCHAR(20),
    is_main_file          BOOLEAN DEFAULT FALSE,
    
    -- 审计字段
    create_by      BIGINT,
    create_time    TIMESTAMP DEFAULT NOW(),
    update_by      BIGINT,
    update_time    TIMESTAMP DEFAULT NOW(),
    deleted        BOOLEAN DEFAULT FALSE,
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE
);

-- 标准资源附件表注释
COMMENT ON TABLE std_resource_attachment IS '标准资源附件表';
COMMENT ON COLUMN std_resource_attachment.resource_id IS '对应标准资源ID';
COMMENT ON COLUMN std_resource_attachment.attachment_name IS '数字化内容文件名 如:GBT14396-2016疾病分类与代码.pdf';
COMMENT ON COLUMN std_resource_attachment.is_main_file IS '是否主文件:false-否,true-是';
COMMENT ON COLUMN std_resource_attachment.deleted IS '逻辑删除:false-未删除,true-已删除';

-- 3. 术语定义表 (std_terminology)
-- 存储标准中的术语和定义
DROP TABLE IF EXISTS std_terminology CASCADE;
CREATE TABLE std_terminology (
    id                     BIGSERIAL PRIMARY KEY,
    resource_id           BIGINT NOT NULL,
    term_name             VARCHAR(255) NOT NULL,
    english_term          VARCHAR(255),
    synonyms              VARCHAR(500),
    definition            TEXT,
    term_category         VARCHAR(100),
    term_order            INTEGER DEFAULT 0,
    
    -- 审计字段
    create_by      BIGINT,
    create_time    TIMESTAMP DEFAULT NOW(),
    update_by      BIGINT,
    update_time    TIMESTAMP DEFAULT NOW(),
    deleted        BOOLEAN DEFAULT FALSE,
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE
);

-- 术语定义表注释
COMMENT ON TABLE std_terminology IS '术语定义表';
COMMENT ON COLUMN std_terminology.resource_id IS '对应标准资源ID';
COMMENT ON COLUMN std_terminology.term_name IS '术语 如:基本数据集';
COMMENT ON COLUMN std_terminology.english_term IS '英文对应词 如:Basic dataset';
COMMENT ON COLUMN std_terminology.synonyms IS '同义词，多个用逗号分隔';
COMMENT ON COLUMN std_terminology.deleted IS '逻辑删除:false-未删除,true-已删除';

-- 4. 标准资源引用关联表 (std_resource_reference)
-- 存储标准之间的引用关系
DROP TABLE IF EXISTS std_resource_reference CASCADE;
CREATE TABLE std_resource_reference (
    id                     BIGSERIAL PRIMARY KEY,
    resource_id           BIGINT NOT NULL,
    resource_name         VARCHAR(255) NOT NULL,
    reference_id          BIGINT,
    reference_name        VARCHAR(255) NOT NULL,
    reference_type        VARCHAR(20) NOT NULL,
    reference_code        VARCHAR(64),
    reference_order       INTEGER DEFAULT 0,
    
    -- 审计字段
    create_by      BIGINT,
    create_time    TIMESTAMP DEFAULT NOW(),
    update_by      BIGINT,
    update_time    TIMESTAMP DEFAULT NOW(),
    deleted        BOOLEAN DEFAULT FALSE,
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE,
    FOREIGN KEY (reference_id) REFERENCES std_resource(id) ON DELETE SET NULL
);

-- 标准资源引用关联表注释
COMMENT ON TABLE std_resource_reference IS '标准资源引用关联表';
COMMENT ON COLUMN std_resource_reference.resource_id IS '标准资源ID';
COMMENT ON COLUMN std_resource_reference.resource_name IS '标准资源名称 如:WS 370-2012《卫生信息基本数据集编制规范》';
COMMENT ON COLUMN std_resource_reference.reference_name IS '文件名称 如:WS 363.1 卫生信息数据元目录 第1部分: 总则';
COMMENT ON COLUMN std_resource_reference.reference_type IS '引用类型:引用/被引用';
COMMENT ON COLUMN std_resource_reference.deleted IS '逻辑删除:false-未删除,true-已删除';

-- 5. 文件解读信息表 (std_interpretation)
-- 存储标准的解读文档信息
DROP TABLE IF EXISTS std_interpretation CASCADE;
CREATE TABLE std_interpretation (
    id                     BIGSERIAL PRIMARY KEY,
    resource_id           BIGINT NOT NULL,
    source_type           VARCHAR(20) NOT NULL,
    source_title          VARCHAR(255) NOT NULL,
    source_url            VARCHAR(512),
    source_description    TEXT,
    upload_date           DATE,
    file_size             VARCHAR(20),
    file_format           VARCHAR(20),
    is_official           BOOLEAN DEFAULT FALSE,
    view_count            INTEGER DEFAULT 0,
    
    -- 审计字段
    create_by      BIGINT,
    create_time    TIMESTAMP DEFAULT NOW(),
    update_by      BIGINT,
    update_time    TIMESTAMP DEFAULT NOW(),
    deleted        BOOLEAN DEFAULT FALSE,
    
    FOREIGN KEY (resource_id) REFERENCES std_resource(id) ON DELETE CASCADE
);

-- 文件解读信息表注释
COMMENT ON TABLE std_interpretation IS '文件解读信息表';
COMMENT ON COLUMN std_interpretation.resource_id IS '对应标准资源ID';
COMMENT ON COLUMN std_interpretation.source_type IS '来源类型:文件/网站';
COMMENT ON COLUMN std_interpretation.source_title IS '解读标题 如:《疾病控制基本数据集第13部分：职业病危害因素监测》标准解读';
COMMENT ON COLUMN std_interpretation.is_official IS '是否官方解读:false-否,true-是';
COMMENT ON COLUMN std_interpretation.deleted IS '逻辑删除:false-未删除,true-已删除';

-- ==========================================
-- 创建索引
-- ==========================================

-- 标准资源主表索引
CREATE INDEX idx_std_resource_code ON std_resource(resource_code);
CREATE INDEX idx_std_resource_status ON std_resource(standard_status);
CREATE INDEX idx_std_resource_type ON std_resource(standard_type);
CREATE INDEX idx_std_resource_field ON std_resource(standard_field);
CREATE INDEX idx_std_resource_create_time ON std_resource(create_time);
CREATE INDEX idx_std_resource_deleted ON std_resource(deleted);

-- 标准资源附件表索引
CREATE INDEX idx_std_attachment_resource_id ON std_resource_attachment(resource_id);
CREATE INDEX idx_std_attachment_type ON std_resource_attachment(attachment_type);
CREATE INDEX idx_std_attachment_upload_date ON std_resource_attachment(upload_date);
CREATE INDEX idx_std_attachment_is_main ON std_resource_attachment(is_main_file);
CREATE INDEX idx_std_attachment_deleted ON std_resource_attachment(deleted);

-- 术语定义表索引
CREATE INDEX idx_std_terminology_resource_id ON std_terminology(resource_id);
CREATE INDEX idx_std_terminology_term_name ON std_terminology(term_name);
CREATE INDEX idx_std_terminology_category ON std_terminology(term_category);
CREATE INDEX idx_std_terminology_order ON std_terminology(term_order);
CREATE INDEX idx_std_terminology_deleted ON std_terminology(deleted);

-- 标准资源引用关联表索引
CREATE INDEX idx_std_reference_resource_id ON std_resource_reference(resource_id);
CREATE INDEX idx_std_reference_ref_id ON std_resource_reference(reference_id);
CREATE INDEX idx_std_reference_type ON std_resource_reference(reference_type);
CREATE INDEX idx_std_reference_order ON std_resource_reference(reference_order);
CREATE INDEX idx_std_reference_deleted ON std_resource_reference(deleted);

-- 文件解读信息表索引
CREATE INDEX idx_std_interpretation_resource_id ON std_interpretation(resource_id);
CREATE INDEX idx_std_interpretation_source_type ON std_interpretation(source_type);
CREATE INDEX idx_std_interpretation_upload_date ON std_interpretation(upload_date);
CREATE INDEX idx_std_interpretation_is_official ON std_interpretation(is_official);
CREATE INDEX idx_std_interpretation_deleted ON std_interpretation(deleted);

-- ==========================================
-- 创建更新时间触发器函数
-- ==========================================

-- 创建更新时间触发器函数
CREATE OR REPLACE FUNCTION update_updated_time_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.update_time = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 为每个表创建更新时间触发器
CREATE TRIGGER update_std_resource_update_time BEFORE UPDATE ON std_resource 
FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();

CREATE TRIGGER update_std_attachment_update_time BEFORE UPDATE ON std_resource_attachment 
FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();

CREATE TRIGGER update_std_terminology_update_time BEFORE UPDATE ON std_terminology 
FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();

CREATE TRIGGER update_std_reference_update_time BEFORE UPDATE ON std_resource_reference 
FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();

CREATE TRIGGER update_std_interpretation_update_time BEFORE UPDATE ON std_interpretation 
FOR EACH ROW EXECUTE FUNCTION update_updated_time_column();

-- ==========================================
-- PostgreSQL数据库初始化完成
-- 包含5个主要数据表:
-- 1. std_resource - 标准资源主表
-- 2. std_resource_attachment - 标准资源附件表
-- 3. std_terminology - 术语定义表  
-- 4. std_resource_reference - 标准资源引用关联表
-- 5. std_interpretation - 文件解读信息表
-- 包含完整的索引和触发器支持
-- ==========================================