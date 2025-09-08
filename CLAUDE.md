# CLAUDE.md

本文件为 Claude Code (claude.ai/code) 在此代码仓库中工作时提供指导。

## 🌐 语言规范
- 所有对话和文档必须使用中文

## 📋 项目概述

NewbieBoot3 是基于 Spring Boot 3 和 Java 21 构建的多模块企业管理系统。采用模块化架构实现关注点分离和可维护性。

## 🚀 快速开始

### 构建项目
```bash
# 清理并构建所有模块（跳过测试）
mvn clean install -Dmaven.test.skip=true

# Windows 快速重构建脚本
bin/reinstall.bat
```

### 运行应用
```bash
# 开发环境：从编译的jar包运行
java -jar newbie-admin/target/newbie-admin-0.0.1-SNAPSHOT.jar

# Windows 快速运行脚本
bin/run.bat
```

### 生产环境部署
使用 `bin/` 目录下的 shell 脚本：
```bash
# 启动服务
sh bin/jar.sh start

# 停止服务
sh bin/jar.sh stop

# 查看状态
sh bin/jar.sh status

# 重启服务
sh bin/jar.sh restart
```

## 🏗️ 项目架构

### 模块结构
这是一个 Maven 多模块项目，包含以下模块：

#### 核心模块
- **newbie-admin** - 主应用模块
  - REST 控制器和 Spring Boot 入口点 (`NewbieApplication.java`)
  - 聚合所有其他模块
  - 端口：8080
- **newbie-common** - 基础模块
  - 共享工具类、基础实体、配置、常量和异常处理
  - 所有模块的基础依赖

#### 功能模块  
- **newbie-security** - 认证授权
  - 使用 Sa-Token 框架
  - Redis 集成
  - 自定义 `StpInterfaceImpl` 进行权限控制
- **newbie-system** - 系统管理
  - 用户、角色、菜单、部门、字典管理
- **newbie-file** - 文件服务
  - 支持 MinIO 对象存储
  - 文件上传下载服务
- **newbie-weblog** - 日志服务
  - 基于 AOP 的 Web 请求日志功能
  - 使用 `@WebLog` 注解
- **newbie-generator** - 代码生成器
  - 基于 MyBatis-Plus 的数据库表代码生成器
  - 使用 FreeMarker 模板

### 模块依赖关系
```
newbie-admin (主应用模块)
├── newbie-system
├── newbie-file  
├── newbie-security
├── newbie-weblog
├── newbie-generator
└── newbie-common (所有模块的基础依赖)
```

## 🛠️ 技术栈

### 核心技术
- **Spring Boot** 3.2.4 搭配 Java 21
- **MyBatis-Plus** - 数据持久化
- **Sa-Token** 1.37.0 - 认证授权
- **Redis** - 缓存和会话存储
- **MinIO** 8.4.3 - 文件存储

### 支持工具
- **Knife4j** - API 文档
- **Hutool** 5.8.26 - 工具库
- **FreeMarker** - 代码生成模板

## ⚙️ 配置信息

### 环境配置
- **开发环境**: `application-dev.yml`
- **生产环境**: `application-prod.yml`
- **API文档**: `application-apidoc.yml`
- **默认配置**: `dev` 和 `apidoc`

### 数据库支持
在 `db/` 目录提供以下数据库初始化脚本：
- MySQL
- Oracle 
- PostgreSQL

### 关键配置路径
- 配置文件：`newbie-admin/src/main/resources/application*.yml`
- 数据库脚本：`db/`

## 🏛️ 架构模式

### 分层架构
- **标准三层架构**: Controller → Service → Mapper
- **基础实体**: 使用 `BaseEntity` 包含通用审计字段
- **统一响应**: 使用 `R` 工具类统一响应格式
- **异常处理**: 通过 `GlobalExceptionHandle` 进行全局异常处理

### 特殊功能
- **日志记录**: 使用 `@WebLog` 注解进行 AOP 日志记录
- **树形结构**: 层次数据（菜单、部门）的树形结构工具
- **系统监控**: 通过 `SysServerController` 提供服务器指标

## 💡 开发指南

### 重要入口点
- **主应用入口**: `newbie-admin` 模块中的 `com.newbie.NewbieApplication`
- **权限控制**: Sa-Token 通过自定义 `StpInterfaceImpl` 处理
- **文件存储**: 使用可配置的存储后端（默认 MinIO）

### 开发规范
- 所有模块都依赖 newbie-common 获取共享功能
- 代码生成模板使用 FreeMarker
- 遵循标准的 Spring Boot 项目结构
- 使用 MyBatis-Plus 进行数据访问

### 常用操作
- **测试**: 运行测试前确保数据库连接正常
- **代码生成**: 使用 newbie-generator 模块生成 CRUD 代码
- **API 文档**: 访问 Knife4j 接口文档
- **日志查看**: 通过 @WebLog 注解自动记录请求日志

## 🏆 架构师级别技术指南

### 角色定位
作为「全栈全生命周期架构师」，覆盖从需求到运营的完整链路，需要确保系统达到以下标准：

#### 🎯 需求分析师
- **需求澄清**: 业务需求拆解和技术可行性评估
- **原型设计**: 交互流程和数据模型设计
- **架构决策**: 技术选型和架构方案设计
- **风险评估**: 技术风险识别和解决方案

#### 🏗️ 全栈架构师
- **系统设计**: 微服务拆分、数据库设计、接口规范
- **技术选型**: 框架选择、中间件集成、第三方服务
- **性能架构**: 缓存策略、数据库优化、分布式方案
- **可扩展性**: 水平扩展、负载均衡、容灾设计

#### 🔒 安全负责人
- **安全设计**: 身份认证、权限控制、数据加密
- **漏洞防护**: SQL注入、XSS攻击、CSRF防护
- **合规审查**: 数据脱敏、隐私保护、安全审计
- **渗透测试**: 安全扫描、漏洞修复、安全监控

#### 👨‍💻 全栈开发工程师
- **前端开发**: Vue3/React、TypeScript、组件库
- **后端开发**: Spring Boot、微服务、API设计
- **数据库**: MySQL/PostgreSQL优化、Redis缓存
- **集成开发**: 第三方API、消息队列、文件存储

#### 🧪 测试工程师
- **测试策略**: 单元测试、集成测试、端到端测试
- **自动化测试**: 测试用例编写、CI/CD集成
- **性能测试**: 压力测试、并发测试、稳定性测试
- **质量保证**: 代码覆盖率、Bug跟踪、质量报告

#### ⚙️ DevOps工程师
- **CI/CD**: Jenkins/GitLab CI、自动化构建部署
- **容器化**: Docker、Kubernetes、服务编排
- **监控运维**: 日志收集、性能监控、告警机制
- **环境管理**: 开发/测试/生产环境隔离和配置

#### 📊 运营数据分析师
- **业务监控**: 关键指标监控、用户行为分析
- **性能分析**: 系统性能指标、瓶颈识别
- **数据驱动**: A/B测试、用户反馈、优化建议
- **成本优化**: 资源使用分析、成本控制、ROI评估

#### 🎯 全生命周期质量标准

#### 🚀 高并发性能指标
- **目标QPS**: 上传/检索/日志 QPS 2w+
- **响应时间**: P99 < 200ms
- **关键优化点**:
  - Redis 缓存策略优化
  - 数据库连接池调优
  - 异步处理机制
  - 文件上传流优化

#### 🛡️ 高可靠性保障
- **任务幂等**: 使用唯一键防重复提交
- **分布式锁**: Redis 分布式锁防并发问题
- **熔断降级**: 服务调用超时熔断
- **事务一致性**: 分布式事务处理

#### 🔒 高安全防护
- **先审后发**: 内容审核机制
- **水平越权**: 数据权限检查
- **垂直越权**: 功能权限注解 `@PreAuthorize`
- **脱敏日志**: 敏感信息过滤

#### 📋 高可维护性
- **文档即接口**: Knife4j + OpenAPI 3.0
- **测试即质量**: 单元测试覆盖率 > 80%
- **规范即真理**: 代码规范强制检查

### 🚀 高并发性能优化

#### 缓存策略
```java
// Redis 分层缓存
@Cacheable(value = "user", key = "#userId", unless = "#result == null")
@Async("taskExecutor")  // 异步更新缓存
@DistributedLock(key = "'user:' + #userId", leaseTime = 5000)
```

#### 数据库优化
- **连接池配置**: HikariCP 最优参数
- **分页查询**: MyBatis-Plus 分页插件
- **索引优化**: 复合索引策略
- **读写分离**: 主从数据源配置

#### 异步处理
```java
@Async("webLogExecutor")
@WebLog(description = "异步日志记录")
public CompletableFuture<Void> asyncLogOperation() {
    // 异步处理逻辑
}
```

### 🛡️ 安全防护最佳实践

#### 权限控制
```java
// 垂直越权防护
@PreAuthorize("hasPermission('system:user:edit')")
@SaCheckPermission("system:user:edit")

// 水平越权防护
@DataScope(deptAlias = "d", userAlias = "u")
public List<SysUser> selectUserList(SysUser user)
```

#### 数据脱敏
```java
// 日志脱敏
@WebLog(sensitiveFields = {"password", "phone", "email"})
@JsonIgnore  // 序列化忽略敏感字段
```

#### 安全配置
- **HTTPS 强制**: SSL 证书配置
- **CORS 限制**: 跨域访问控制
- **SQL 注入防护**: MyBatis 参数化查询
- **XSS 防护**: 输入验证和输出编码

### 📊 代码质量标准

#### 架构分层
```
Controller 层 (17 个文件)
├── 统一异常处理: GlobalExceptionHandle
├── 参数验证: @Valid + BindingResult
└── 响应统一: R.ok() / R.fail()

Service 层 (23 个文件) 
├── 业务逻辑封装
├── 事务管理: @Transactional
└── 缓存策略: @Cacheable

Mapper 层 (16 个文件)
├── MyBatis-Plus BaseMapper
├── 自定义 SQL 映射
└── 分页插件集成
```

#### 代码规范检查
- **SonarQube**: 代码质量扫描
- **SpotBugs**: 静态代码分析
- **Checkstyle**: 代码风格检查
- **单元测试**: JUnit 5 + Mockito

### 📝 代码修改注释规范

#### 基本要求
- **语言**: 必须全部使用简体中文
- **格式**: `# 变更类型: 变更说明  作者  日期`
- **变更类型**: 新增、修改、删除、重构、优化、修复

#### 注释位置规范
```java
// 行内变更 → 在改动行末尾加注释
private String phoneNumber;  // 新增: 支持手机号登录  张三  2024-03-15

// 块级变更 → 在改动代码上方加注释
// 新增: 支持手机号+验证码登录，应对微信OAuth下线  李四  2024-03-16
@PostMapping("/login/phone")
public R<LoginVO> phoneLogin(@RequestBody PhoneLoginDTO dto) {
    return R.ok(securityService.phoneLogin(dto));
}
```

#### 重要变更标记
```java
## 变更说明: 数据库连接池从C3P0迁移到HikariCP ⚠️ 不兼容
spring:
  datasource:
    hikari:  // 修改: C3P0 -> HikariCP，提升性能  王五  2024-03-17
      maximum-pool-size: 50

## 变更说明: 新增Redis缓存配置 ⚠️ 配置变更  
@Configuration
public class CacheConfig {
    // 新增: Redis缓存配置，支持分布式缓存  赵六  2024-03-18
}
```

#### 复用注释标记
```java
private String username;  // 修改: 用户名长度限制 6-20位  张三  2024-03-15
private String nickname;  // 修改: 昵称长度限制 2-20位 (同#1)  张三  2024-03-15
private String realName;  // 修改: 真实姓名长度限制 2-20位 (同#1)  张三  2024-03-15
```

#### 删除代码处理
```java
// 删除: 废弃邮箱注册链路，统一走手机号注册  李四  2024-03-16
// @PostMapping("/register/email")
// public R<Void> emailRegister(@RequestBody EmailRegisterDTO dto) {
//     return R.ok(userService.emailRegister(dto));
// }
```

#### 注释分类标准
- **新增**: 全新功能或代码块
- **修改**: 现有逻辑调整或参数变更
- **删除**: 移除废弃功能或代码
- **重构**: 代码结构优化，功能不变
- **优化**: 性能提升或算法改进
- **修复**: Bug修复或问题解决

#### 特殊场景标记
- **⚠️ 不兼容**: API变更或数据结构调整
- **⚠️ 配置变更**: 需要更新配置文件
- **🔒 安全**: 安全相关的重要变更
- **⚡ 性能**: 性能优化相关变更

### 🔧 关键组件配置

#### Redis 配置优化
```yaml
spring:
  redis:
    lettuce:
      pool:
        max-active: 200    # 连接池最大连接数
        max-wait: -1ms     # 连接池最大阻塞等待时间
        max-idle: 10       # 连接池中的最大空闲连接
        min-idle: 5        # 连接池中的最小空闲连接
```

#### 数据库连接池
```yaml
spring:
  datasource:
    hikari:
      maximum-pool-size: 50
      minimum-idle: 10
      connection-timeout: 30000
      idle-timeout: 600000
      max-lifetime: 1800000
```

### 🔍 监控与运维

#### 系统监控
- **服务器指标**: `SysServerController` 提供 CPU、内存、磁盘监控
- **业务监控**: 操作日志 `SysLogOperateController`
- **登录监控**: 登录日志 `SysLogLoginController`
- **性能监控**: AOP 切面记录方法执行时间

#### 日志管理
- **操作日志**: `@WebLog` 自动记录用户操作
- **错误日志**: 全局异常处理统一记录
- **性能日志**: 慢SQL和接口响应时间监控
- **安全日志**: 登录、权限检查异常记录

### 🧪 测试策略

#### 测试分层
- **单元测试**: Service 层业务逻辑测试
- **集成测试**: Controller 层接口测试
- **性能测试**: 高并发场景压力测试
- **安全测试**: 权限验证和数据安全测试

#### 测试工具
- **JUnit 5**: 单元测试框架
- **MockMvc**: Web 层测试
- **TestContainers**: 数据库集成测试
- **JMeter**: 性能压力测试

## 📁 重要目录结构
```
newbie-boot3/
├── bin/                    # 部署脚本
├── db/                     # 数据库初始化脚本
├── newbie-admin/           # 主应用模块 (17个Controller)
├── newbie-common/          # 通用基础模块 (Redis/MybatisPlus配置)
├── newbie-security/        # 安全认证模块 (Sa-Token/权限控制)
├── newbie-system/          # 系统管理模块 (10个Service实现)
├── newbie-file/           # 文件服务模块 (MinIO集成)
├── newbie-weblog/         # 日志服务模块 (AOP切面)
├── newbie-generator/      # 代码生成器模块 (3个数据库支持)
├── pom.xml               # 根项目配置
└── README.md             # 项目说明文档
```