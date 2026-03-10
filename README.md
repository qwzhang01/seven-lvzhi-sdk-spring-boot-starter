# Seven Lvzhi SDK Spring Boot Starter

[![Maven Central](https://img.shields.io/maven-central/v/io.github.qwzhang01/seven-lvzhi-sdk-spring-boot-starter.svg)](https://search.maven.org/artifact/io.github.qwzhang01/seven-lvzhi-sdk-spring-boot-starter)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-17+-green.svg)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-brightgreen.svg)](https://spring.io/projects/spring-boot)

旅智分销开放平台 API 的 Spring Boot Starter，提供开箱即用的 SDK 集成方案。

## 📖 简介

本项目是旅智分销开放平台（Lvzhi DRP）的 Spring Boot Starter，旨在简化开发者接入旅智分销平台的流程。通过自动配置和依赖注入，开发者只需简单配置即可快速使用旅智平台的各项服务。

### 主要功能

- 🚀 **开箱即用**：基于 Spring Boot 自动配置，零代码即可完成 SDK 初始化
- 🔐 **自动鉴权**：自动管理 Access Token 的获取和刷新
- 📝 **完整 API 覆盖**：支持旅智平台所有 API 接口
- 🔧 **灵活配置**：支持自定义 HTTP 客户端、超时时间等配置
- 📦 **类型安全**：提供完整的请求和响应 DTO 定义
- 🛡️ **异常处理**：统一的异常处理机制
- 🗄️ **Redis缓存支持**：支持Redis分布式缓存管理Token，提升多实例部署的稳定性

### 支持的 API 模块

- **授权接口**：Token 获取和管理
- **静态信息接口**：区域信息查询
- **酒店基础数据接口**：酒店列表、酒店详情
- **房型接口**：房型列表、房型详情
- **产品接口**：产品列表、产品详情
- **酒店动态信息接口**：产品库存、房型库存
- **订单接口**：订单创建、查询、取消、预订校验
- **会员接口**：会员绑定、查询、通知

## 📋 环境要求

- JDK 17 或更高版本
- Spring Boot 3.3.0 或更高版本
- Maven 3.6+ 或 Gradle 7.0+
- （可选）Redis 6.0+（如需使用Redis缓存功能）

## 🚀 快速开始

### Maven 依赖

在 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>io.github.qwzhang01</groupId>
    <artifactId>seven-lvzhi-sdk-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```

### Gradle 依赖

在 `build.gradle` 中添加依赖：

```gradle
implementation 'io.github.qwzhang01:seven-lvzhi-sdk-spring-boot-starter:1.0.1'
```

### 配置文件

在 `application.yml` 或 `application.properties` 中添加配置：

```yaml
lvzhi:
  drp:
    # API 基础 URL（可选，默认为生产环境）
    base-url: https://open.zktapi.com/drp
    # 客户端 ID（必填）
    client-id: your-client-id
    # 客户端密钥（必填）
    client-secret: your-client-secret
    # 签名密钥（必填）
    secret-key: your-secret-key
    # API 版本（可选，默认 V1.0.0）
    version: V1.0.0
    # HTTP 客户端配置（可选）
    http-client:
      connect-timeout: 5000
      read-timeout: 30000
      connection-request-timeout: 5000
      max-conn-total: 200
      max-conn-per-route: 50
    # Redis缓存配置（可选）
    redis-cache:
      enabled: true
      key-prefix: "lvzhi:drp:token:"
      expire-time: 86400
```

或使用 `application.properties`：

```properties
lvzhi.drp.base-url=https://open.zktapi.com/drp
lvzhi.drp.client-id=your-client-id
lvzhi.drp.client-secret=your-client-secret
lvzhi.drp.secret-key=your-secret-key
lvzhi.drp.version=V1.0.0
lvzhi.drp.http-client.connect-timeout=5000
lvzhi.drp.http-client.read-timeout=30000
lvzhi.drp.http-client.connection-request-timeout=5000
lvzhi.drp.http-client.max-conn-total=200
lvzhi.drp.http-client.max-conn-per-route=50
lvzhi.drp.redis-cache.enabled=true
lvzhi.drp.redis-cache.key-prefix=lvzhi:drp:token:
lvzhi.drp.redis-cache.expire-time=86400
```

## 🗄️ Redis缓存功能

### 功能特性

- **智能缓存策略**：自动检测RedisTemplate，优先使用Redis缓存，无Redis时回退到本地缓存
- **分布式支持**：多实例部署时Token共享，避免重复获取
- **自动过期管理**：支持自定义缓存过期时间
- **线程安全**：内置锁机制，防止并发问题
- **无缝切换**：无需修改业务代码，自动适配缓存策略

### 配置说明

#### 启用Redis缓存

要启用Redis缓存功能，需要满足以下条件：

1. 在项目中引入Spring Data Redis依赖
2. 配置Redis连接信息
3. 在`lvzhi.drp.redis-cache.enabled`中设置为`true`

#### Redis依赖配置

在`pom.xml`中添加Redis依赖：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

#### Redis连接配置

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: your-redis-password
    database: 0
    timeout: 2000
```

### 缓存策略说明

#### 1. Redis缓存优先
当检测到RedisTemplate存在且Redis缓存启用时，优先使用Redis缓存：
- Token存储在Redis中，键格式：`lvzhi:drp:token:{clientId}`
- 支持多实例共享Token
- 自动处理过期和刷新

#### 2. 本地缓存回退
当Redis不可用时，自动回退到本地缓存：
- 使用内存变量存储Token
- 单实例内有效
- 应用重启后需要重新获取Token

#### 3. 双重检查锁
无论使用哪种缓存方式，都采用双重检查锁机制确保线程安全。

### 使用示例

#### 基本使用（无需修改代码）

```java
@Service
public class HotelBookingService {
    
    @Autowired
    private HotelService hotelService;
    
    public void getHotelInfo(Long hotelVid) {
        // SDK会自动根据配置选择Redis缓存或本地缓存
        BaseResponse<HotelDetailInfo> response = hotelService.getByVid(hotelVid);
        // ...
    }
}
```

#### 手动清除缓存

```java
@Service
public class TokenManagementService {
    
    @Autowired
    private LvzhiDrpClient lvzhiDrpClient;
    
    public void forceRefreshToken() {
        // 强制清除缓存，下次请求会重新获取Token
        lvzhiDrpClient.clearTokenCache();
    }
}
```

### 缓存键格式

Redis缓存使用以下键格式：
```
lvzhi:drp:token:{clientId}
```

例如，clientId为`test-app`时，缓存键为：
```
lvzhi:drp:token:test-app
```

### 使用示例

#### 1. 注入服务

```java
import io.github.qwzhang01.luzhi.sdk.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotelBookingService {
    
    @Autowired
    private HotelService hotelService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
    // 使用服务...
}
```

#### 2. 查询酒店列表

```java
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.common.PageData;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelBasicInfo;

public void listHotels() {
    BaseResponse<PageData<HotelBasicInfo>> response = 
        hotelService.list(null, "110100", 1, 20);
    
    if (response.isSuccess()) {
        PageData<HotelBasicInfo> data = response.getData();
        System.out.println("总数: " + data.getTotalCount());
        data.getList().forEach(hotel -> {
            System.out.println("酒店名称: " + hotel.getHotelName());
        });
    }
}
```

#### 3. 获取酒店详情

```java
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelDetailInfo;

public void getHotelDetail(Long hotelVid) {
    BaseResponse<HotelDetailInfo> response = hotelService.getByVid(hotelVid);
    
    if (response.isSuccess()) {
        HotelDetailInfo hotel = response.getData();
        System.out.println("酒店名称: " + hotel.getHotelName());
        System.out.println("酒店地址: " + hotel.getAddress());
    }
}
```

#### 4. 查询产品列表

```java
import io.github.qwzhang01.luzhi.sdk.dto.product.ProductListResponse;

public void listProducts(Long hotelVid) {
    BaseResponse<ProductListResponse> response = productService.list(hotelVid);
    
    if (response.isSuccess()) {
        ProductListResponse data = response.getData();
        data.getProductList().forEach(product -> {
            System.out.println("产品名称: " + product.getProductName());
        });
    }
}
```

#### 5. 创建订单

```java
import io.github.qwzhang01.luzhi.sdk.dto.order.CreateOrderRequest;
import io.github.qwzhang01.luzhi.sdk.dto.order.CreateOrderResponse;

public void createOrder() {
    CreateOrderRequest request = new CreateOrderRequest();
    request.setHotelVid(123456L);
    request.setProductId(789L);
    // 设置其他必要参数...
    
    BaseResponse<CreateOrderResponse> response = orderService.create(request);
    
    if (response.isSuccess()) {
        CreateOrderResponse order = response.getData();
        System.out.println("订单号: " + order.getOrderNo());
    }
}
```

#### 6. 查询订单详情

```java
import io.github.qwzhang01.luzhi.sdk.dto.order.OrderDetailInfo;

public void getOrderDetail(String orderNo) {
    BaseResponse<OrderDetailInfo> response = orderService.getById(orderNo);
    
    if (response.isSuccess()) {
        OrderDetailInfo order = response.getData();
        System.out.println("订单状态: " + order.getOrderStatus());
    }
}
```

## 📚 API 文档

### 服务列表

| 服务类 | 说明 | 主要方法 |
|--------|------|----------|
| `AuthorizeService` | 授权服务 | `getToken()` |
| `RegionService` | 区域服务 | `listRegionTrees()` |
| `HotelService` | 酒店服务 | `list()`, `getByVid()` |
| `RoomTypeService` | 房型服务 | `list()`, `getById()` |
| `ProductService` | 产品服务 | `list()`, `getById()` |
| `DailyService` | 动态信息服务 | `getProductDaily()`, `getRoomTypeDaily()` |
| `OrderService` | 订单服务 | `create()`, `getById()`, `cancel()`, `reserveValidate()` |
| `MemberService` | 会员服务 | `bind()`, `search()`, `notify()` |

### 配置项说明

| 配置项 | 类型 | 默认值 | 说明 |
|--------|------|--------|------|
| `lvzhi.drp.base-url` | String | `https://open.zktapi.com/drp` | API 基础 URL |
| `lvzhi.drp.client-id` | String | - | 客户端 ID（必填） |
| `lvzhi.drp.client-secret` | String | - | 客户端密钥（必填） |
| `lvzhi.drp.secret-key` | String | - | 签名密钥（必填） |
| `lvzhi.drp.version` | String | `V1.0.0` | API 版本 |
| `lvzhi.drp.http-client.connect-timeout` | Integer | `5000` | 连接超时时间（毫秒） |
| `lvzhi.drp.http-client.read-timeout` | Integer | `30000` | 读取超时时间（毫秒） |
| `lvzhi.drp.http-client.connection-request-timeout` | Integer | `5000` | 连接请求超时时间（毫秒） |
| `lvzhi.drp.http-client.max-conn-total` | Integer | `200` | 最大连接数 |
| `lvzhi.drp.http-client.max-conn-per-route` | Integer | `50` | 每个路由的最大连接数 |
| `lvzhi.drp.redis-cache.enabled` | Boolean | `false` | 是否启用Redis缓存 |
| `lvzhi.drp.redis-cache.key-prefix` | String | `lvzhi:drp:token:` | Redis缓存键前缀 |
| `lvzhi.drp.redis-cache.expire-time` | Integer | `86400` | Token缓存过期时间（秒） |

## 🔧 高级配置

### 配置方式说明

本SDK支持两种不同的配置初始化方式，您可以根据实际需求选择最适合的方式。

#### 1. 配置文件方式（推荐）

这是最常用的方式，通过application.yml或application.properties文件进行配置。

##### application.yml配置示例：
```yaml
lvzhi:
  drp:
    base-url: https://open.zktapi.com/drp
    client-id: your-client-id
    client-secret: your-client-secret
    secret-key: your-secret-key
    version: V1.0.0
    http-client:
      max-conn-total: 200
      max-conn-per-route: 50
      connect-timeout: 5000
      read-timeout: 30000
      connection-request-timeout: 5000
    redis-cache:
      enabled: true
      key-prefix: "lvzhi:drp:token:"
      expire-time: 86400
```

##### 使用方式：
```java
@Autowired
private LvzhiDrpClient lvzhiDrpClient; // 自动注入默认的客户端
```

#### 配置方式说明

本SDK采用统一的配置方式，通过标准的Spring Boot配置文件进行配置。这种方式简单易用，符合Spring Boot最佳实践。

##### 使用方式：
```java
@Autowired
private LvzhiDrpClient lvzhiDrpClient; // 自动注入默认的客户端
```

##### 自定义配置（可选）：
如果您需要自定义配置，可以创建自己的配置类：
```java
@Configuration
public class CustomConfig {
    
    @Bean
    public LvzhiDrpClient customLvzhiDrpClient(
            CloseableHttpClient lvzhiDrpHttpClient,
            @Value("${custom.base-url}") String baseUrl,
            @Value("${custom.client-id}") String clientId,
            @Value("${custom.client-secret}") String clientSecret,
            @Value("${custom.secret-key}") String secretKey,
            @Value("${custom.version}") String version) {
        return new LvzhiDrpClient(lvzhiDrpHttpClient, baseUrl, clientId, clientSecret, secretKey, version);
    }
}
```

#### 注意事项

1. 默认配置方式已经足够满足大多数使用场景
2. 如果需要自定义配置，请确保配置参数完整且正确
3. 应用启动时会自动验证配置参数，确保配置正确性
3. HTTP客户端配置对所有方式都适用，统一由LvzhiDrpProperties中的httpClient配置管理

### 自定义 HTTP 客户端

如果需要自定义 HTTP 客户端配置，可以通过 Bean 覆盖：

```java
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomHttpClientConfig {
    
    @Bean
    public CloseableHttpClient lvzhiDrpHttpClient() {
        return HttpClients.custom()
            // 自定义配置
            .build();
    }
}
```

### 自定义服务实现

如果需要扩展或自定义服务实现，可以继承现有服务类：

```java
import io.github.qwzhang01.luzhi.sdk.client.LvzhiDrpClient;
import io.github.qwzhang01.luzhi.sdk.service.HotelService;
import org.springframework.stereotype.Service;

@Service
public class CustomHotelService extends HotelService {
    
    public CustomHotelService(LvzhiDrpClient client) {
        super(client);
    }
    
    // 添加自定义方法
    public void customMethod() {
        // 自定义逻辑
    }
}
```

## 🛠️ 开发指南

### 构建项目

```bash
# 克隆项目
git clone https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter.git
cd seven-lvzhi-sdk-spring-boot-starter

# 编译项目
mvn clean compile

# 运行测试
mvn test

# 打包
mvn clean package
```

### 运行测试

项目包含集成测试，需要配置真实的 API 凭证：

1. 复制 `src/test/resources/application-local-example.yml` 为 `application-local.yml`
2. 填入真实的 API 凭证
3. 运行测试：`mvn test`

## 🤝 贡献指南

欢迎贡献代码！请遵循以下步骤：

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

### 代码规范

- 遵循 Java 编码规范
- 保持代码简洁清晰
- 添加必要的注释和文档
- 编写单元测试
- 确保所有测试通过

## 📄 许可证

本项目采用 [Apache License 2.0](LICENSE) 许可证。

## 🔗 相关链接

- [旅智分销开放平台官网](https://open.zktapi.com)
- [API 文档](https://open.zktapi.com/doc)
- [问题反馈](https://github.com/qwzhang01/seven-lvzhi-sdk-spring-boot-starter/issues)

## 📮 联系方式

- 作者：avinzhang
- 邮箱：avinzhang@tencent.com
- GitHub：[@qwzhang01](https://github.com/qwzhang01)

## 🙏 致谢

感谢所有为本项目做出贡献的开发者！

## 📝 更新日志

### v1.0.1 (2024-03-10)

- ✨ **新增Redis缓存支持**：支持Redis分布式缓存管理Token
- 🔄 **智能缓存策略**：自动检测RedisTemplate，优先使用Redis缓存
- 🌐 **分布式支持**：多实例部署时Token共享
- ⚡ **性能优化**：缓存自动过期管理，减少API调用
- 🔒 **线程安全**：双重检查锁机制确保并发安全

### v1.0.0 (2024-02-08)

- 🎉 首次发布
- ✨ 支持 Spring Boot 3.3.0
- ✨ 支持 JDK 17
- ✨ 完整的 API 接口支持
- ✨ 自动配置和依赖注入
- ✨ Token 自动管理
- ✨ 完整的类型定义

---

**如果这个项目对你有帮助，请给个 ⭐️ Star 支持一下！**