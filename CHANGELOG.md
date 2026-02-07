# 更新日志

所有重要的项目变更都将记录在此文件中。

本项目遵循 [语义化版本](https://semver.org/lang/zh-CN/) 规范。

## [未发布]

### 计划中
- 添加更多示例代码
- 支持异步调用
- 添加重试机制
- 支持 WebFlux

## [1.0.0] - 2024-02-08

### 新增
- 🎉 首次正式发布
- ✨ 支持 Spring Boot 3.3.0
- ✨ 支持 JDK 17
- ✨ 完整的旅智分销平台 API 支持
  - 授权接口（Token 管理）
  - 静态信息接口（区域查询）
  - 酒店基础数据接口（酒店列表、详情）
  - 房型接口（房型列表、详情）
  - 产品接口（产品列表、详情）
  - 酒店动态信息接口（库存查询）
  - 订单接口（创建、查询、取消、校验）
  - 会员接口（绑定、查询、通知）
- ✨ Spring Boot 自动配置支持
- ✨ 自动 Token 管理和刷新
- ✨ 完整的请求和响应 DTO 定义
- ✨ HTTP 客户端连接池管理
- ✨ 灵活的配置选项
- ✨ 统一的异常处理
- 📝 完整的 Javadoc 文档
- 📝 详细的 README 和使用示例
- 🧪 集成测试支持

### 技术栈
- Spring Boot 3.3.0
- JDK 17
- Apache HttpClient 5.2
- Jackson 2.17.2
- JUnit 5.10.3
- Mockito 5.18.0

### 配置支持
- API 基础 URL 配置
- 客户端认证配置
- HTTP 客户端超时配置
- 连接池配置

### 文档
- README.md - 项目介绍和快速开始
- CONTRIBUTING.md - 贡献指南
- CHANGELOG.md - 更新日志
- LICENSE - Apache License 2.0

---

## 版本说明

### 版本号格式

版本号格式：`主版本号.次版本号.修订号`

- **主版本号**：不兼容的 API 修改
- **次版本号**：向下兼容的功能性新增
- **修订号**：向下兼容的问题修正

### 变更类型

- `新增` - 新功能
- `变更` - 现有功能的变更
- `废弃` - 即将移除的功能
- `移除` - 已移除的功能
- `修复` - Bug 修复
- `安全` - 安全相关的修复

---

[未发布]: https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter/releases/tag/v1.0.0
