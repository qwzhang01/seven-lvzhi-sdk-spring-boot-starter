# 贡献指南

感谢你考虑为 Seven Lvzhi SDK Spring Boot Starter 做出贡献！

## 行为准则

本项目遵循贡献者公约行为准则。参与本项目即表示你同意遵守其条款。

## 如何贡献

### 报告 Bug

如果你发现了 Bug，请通过 [GitHub Issues](https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter/issues) 报告，并包含以下信息：

- **清晰的标题**：简要描述问题
- **详细描述**：详细说明问题的表现
- **复现步骤**：列出复现问题的步骤
- **期望行为**：说明你期望的正确行为
- **实际行为**：说明实际发生的情况
- **环境信息**：
  - JDK 版本
  - Spring Boot 版本
  - SDK 版本
  - 操作系统
- **相关日志**：如果有错误日志，请附上
- **代码示例**：如果可能，提供最小可复现示例

### 提出新功能

如果你有新功能的想法，请先通过 Issue 讨论：

1. 搜索现有 Issues，确保该功能未被提出
2. 创建新 Issue，使用 "Feature Request" 标签
3. 详细描述功能需求和使用场景
4. 等待维护者反馈

### 提交代码

#### 开发流程

1. **Fork 仓库**
   ```bash
   # 在 GitHub 上 Fork 本仓库
   # 克隆你的 Fork
   git clone https://github.com/YOUR_USERNAME/seven-lvzhi-sdk-spring-boot-starter.git
   cd seven-lvzhi-sdk-spring-boot-starter
   ```

2. **创建分支**
   ```bash
   # 从 main 分支创建新分支
   git checkout -b feature/your-feature-name
   # 或
   git checkout -b fix/your-bug-fix
   ```

3. **开发和测试**
   ```bash
   # 进行代码修改
   # 运行测试
   mvn test
   # 确保代码格式正确
   mvn clean compile
   ```

4. **提交更改**
   ```bash
   git add .
   git commit -m "feat: add some feature"
   # 或
   git commit -m "fix: fix some bug"
   ```

5. **推送到 GitHub**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **创建 Pull Request**
   - 在 GitHub 上创建 Pull Request
   - 填写 PR 模板
   - 等待代码审查

#### 提交信息规范

我们使用 [Conventional Commits](https://www.conventionalcommits.org/) 规范：

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Type 类型：**
- `feat`: 新功能
- `fix`: Bug 修复
- `docs`: 文档更新
- `style`: 代码格式调整（不影响代码运行）
- `refactor`: 重构（既不是新功能也不是 Bug 修复）
- `perf`: 性能优化
- `test`: 测试相关
- `chore`: 构建过程或辅助工具的变动

**示例：**
```
feat(order): add order cancellation feature

Add support for cancelling orders with reason codes.
Includes validation and error handling.

Closes #123
```

#### 代码规范

1. **Java 代码风格**
   - 遵循 [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)
   - 使用 4 个空格缩进
   - 类名使用 PascalCase
   - 方法名和变量名使用 camelCase
   - 常量使用 UPPER_SNAKE_CASE

2. **注释规范**
   - 公共 API 必须有 Javadoc 注释
   - 复杂逻辑需要添加行内注释
   - 注释使用中文或英文，保持一致

3. **命名规范**
   - 类名应该是名词
   - 方法名应该是动词或动词短语
   - 变量名应该有意义，避免使用单字母变量（除了循环计数器）

4. **代码组织**
   - 每个类只做一件事
   - 方法长度不超过 50 行
   - 避免深层嵌套（不超过 3 层）

#### 测试要求

1. **单元测试**
   - 新功能必须包含单元测试
   - Bug 修复应该包含回归测试
   - 测试覆盖率应该保持或提高

2. **测试命名**
   ```java
   @Test
   public void shouldReturnHotelListWhenValidRequest() {
       // 测试代码
   }
   ```

3. **测试结构**
   - 使用 Given-When-Then 模式
   - 每个测试只测试一个场景
   - 使用有意义的断言消息

#### Pull Request 检查清单

在提交 PR 之前，请确保：

- [ ] 代码遵循项目的代码规范
- [ ] 所有测试通过 (`mvn test`)
- [ ] 添加了必要的测试
- [ ] 更新了相关文档
- [ ] 提交信息符合规范
- [ ] 没有引入新的警告
- [ ] 代码已经过自我审查
- [ ] 复杂的代码添加了注释

### 文档贡献

文档改进同样重要！你可以：

- 修正拼写或语法错误
- 改进现有文档的清晰度
- 添加使用示例
- 翻译文档

### 代码审查

所有提交都需要经过代码审查。审查者会关注：

- 代码质量和可维护性
- 测试覆盖率
- 文档完整性
- 性能影响
- 安全性

请耐心等待审查，并根据反馈进行修改。

## 开发环境设置

### 必需工具

- JDK 17 或更高版本
- Maven 3.6 或更高版本
- Git
- IDE（推荐 IntelliJ IDEA 或 Eclipse）

### 配置开发环境

1. **安装 JDK 17**
   ```bash
   # macOS (使用 Homebrew)
   brew install openjdk@17
   
   # Linux (Ubuntu/Debian)
   sudo apt-get install openjdk-17-jdk
   ```

2. **克隆项目**
   ```bash
   git clone https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter.git
   cd seven-lvzhi-sdk-spring-boot-starter
   ```

3. **导入 IDE**
   - IntelliJ IDEA: File -> Open -> 选择项目目录
   - Eclipse: File -> Import -> Existing Maven Projects

4. **配置测试环境**
   ```bash
   # 复制配置文件模板
   cp src/test/resources/application-local-example.yml src/test/resources/application-local.yml
   
   # 编辑配置文件，填入真实的 API 凭证
   vim src/test/resources/application-local.yml
   ```

5. **运行测试**
   ```bash
   mvn clean test
   ```

## 发布流程

（仅限维护者）

1. 更新版本号
2. 更新 CHANGELOG.md
3. 创建 Git 标签
4. 推送到 GitHub
5. 发布到 Maven Central

## 获取帮助

如果你有任何问题：

- 查看 [README.md](README.md)
- 搜索 [现有 Issues](https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter/issues)
- 创建新 Issue
- 发送邮件至 avinzhang@tencent.com

## 许可证

通过贡献代码，你同意你的贡献将在 Apache License 2.0 下授权。

---

再次感谢你的贡献！🎉
