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
- 🔔 **回调通知支持**：支持酒店信息变更回调通知处理
- 🔧 **灵活配置**：支持自定义 HTTP 客户端、超时时间等配置
- 📦 **类型安全**：提供完整的请求和响应 DTO 定义
- 🛡️ **异常处理**：统一的异常处理机制
- 🗄️ **Redis缓存支持**：支持Redis分布式缓存管理Token，提升多实例部署的稳定性
- 🔄 **双缓存策略**：支持Redis缓存和内存缓存，自动根据配置选择最优方案
- ⚡ **自动注入**：基于Spring Boot自动配置，无需手动注入即可使用所有服务

### 支持的 API 模块

- **授权接口**：Token 获取和管理
- **静态信息接口**：区域信息查询
- **酒店基础数据接口**：酒店列表、酒店详情
- **房型接口**：房型列表、房型详情
- **产品接口**：产品列表、产品详情
- **酒店动态信息接口**：产品库存、房型库存
- **订单接口**：订单创建、查询、取消、预订校验
- **会员接口**：会员绑定、查询、通知
- **回调接口**：酒店信息变更通知处理

## 📋 环境要求

- JDK 17 或更高版本
- Spring Boot 3.3.0 或更高版本
- Maven 3.6+ 或 Gradle 7.0+
- （可选）Redis 6.0+（如需使用Redis缓存功能）

## 🚀 快速开始

### 自动配置（推荐）

本SDK采用Spring Boot自动配置机制，只需添加依赖和配置即可自动注入所有服务组件。

#### 1. 添加依赖

**Maven 依赖**

在 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>io.github.qwzhang01</groupId>
    <artifactId>seven-lvzhi-sdk-spring-boot-starter</artifactId>
    <version>1.2.0</version>
</dependency>
```

**Gradle 依赖**

在 `build.gradle` 中添加依赖：

```gradle
implementation 'io.github.qwzhang01:seven-lvzhi-sdk-spring-boot-starter:1.2.0'
```

#### 2. 配置参数

在 `application.yml` 中配置必要的参数：

```yaml
lvzhi:
  drp:
    client-id: your-client-id           # 必填：客户端ID
    client-secret: your-client-secret   # 必填：客户端密钥
    secret-key: your-secret-key         # 必填：签名密钥
    # 可选配置
    base-url: https://open.zktapi.com/drp  # API基础URL，默认生产环境
    version: V1.0.0                     # API版本，默认V1.0.0
```

#### 3. 使用服务

配置完成后，SDK会自动注入所有服务组件，直接在代码中使用即可：

```java
@Service
public class HotelBookingService {
    
    @Autowired
    private HotelService hotelService;           // 酒店服务
    
    @Autowired
    private ProductService productService;      // 产品服务
    
    @Autowired
    private OrderService orderService;          // 订单服务
    
    @Autowired
    private MemberService memberService;        // 会员服务
    
    public void bookHotel(Long hotelVid) {
        // 获取酒店信息
        BaseResponse<HotelDetailInfo> hotelResponse = hotelService.getByVid(hotelVid);
        
        // 获取产品列表
        BaseResponse<ProductListResponse> productResponse = productService.list(hotelVid);
        
        // 自动处理Token获取和缓存
        // SDK会自动管理Token的生命周期
    }
}
```

### 手动配置（不推荐）

如果您需要手动配置，可以使用 `LvzhiDrpManualConfiguration` 工具类：

```java
import io.github.qwzhang01.luzhi.sdk.autoconfigure.LvzhiDrpManualConfiguration;

@Service
public class ManualConfigService {
    
    public void createManualClient() {
        // 创建使用内存缓存的客户端
        LvzhiDrpClient client = LvzhiDrpManualConfiguration.createMemoryCacheClient(
            "https://open.zktapi.com/drp",
            "your-client-id",
            "your-client-secret",
            "your-secret-key"
        );
        
        // 创建使用Redis缓存的客户端
        LvzhiDrpClient redisClient = LvzhiDrpManualConfiguration.createRedisCacheClient(
            "https://open.zktapi.com/drp",
            "your-client-id",
            "your-client-secret",
            "your-secret-key",
            redisTemplate
        );
    }
}
```

**注意：推荐使用自动配置方式，手动配置仅适用于特殊场景。**

## 🎯 API使用示例

### 酒店服务示例

```java
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    
    @Autowired
    private HotelService hotelService;
    
    // 获取酒店列表
    @GetMapping
    public BaseResponse<HotelListResponse> getHotels(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return hotelService.list(page, size);
    }
    
    // 获取酒店详情
    @GetMapping("/{vid}")
    public BaseResponse<HotelDetailInfo> getHotelDetail(@PathVariable Long vid) {
        return hotelService.getByVid(vid);
    }
    
    // 搜索酒店
    @GetMapping("/search")
    public BaseResponse<HotelListResponse> searchHotels(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return hotelService.search(keyword, page, size);
    }
}
```

### 产品服务示例

```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    // 获取酒店产品列表
    @GetMapping("/hotel/{hotelVid}")
    public BaseResponse<ProductListResponse> getHotelProducts(@PathVariable Long hotelVid) {
        return productService.list(hotelVid);
    }
    
    // 获取产品详情
    @GetMapping("/{productId}")
    public BaseResponse<ProductDetailInfo> getProductDetail(@PathVariable Long productId) {
        return productService.getDetail(productId);
    }
}
```

### 订单服务示例

```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    // 创建订单
    @PostMapping
    public BaseResponse<OrderCreateResponse> createOrder(@RequestBody OrderCreateRequest request) {
        return orderService.create(request);
    }
    
    // 查询订单详情
    @GetMapping("/{orderId}")
    public BaseResponse<OrderDetailInfo> getOrderDetail(@PathVariable String orderId) {
        return orderService.getDetail(orderId);
    }
    
    // 取消订单
    @PostMapping("/{orderId}/cancel")
    public BaseResponse<OrderCancelResponse> cancelOrder(
            @PathVariable String orderId,
            @RequestBody OrderCancelRequest request) {
        return orderService.cancel(orderId, request);
    }
}
```

## 🏆 最佳实践

### 1. 生产环境配置

```yaml
# application-prod.yml
lvzhi:
  drp:
    client-id: ${LVZHI_CLIENT_ID}
    client-secret: ${LVZHI_CLIENT_SECRET}
    secret-key: ${LVZHI_SECRET_KEY}
    base-url: https://open.zktapi.com/drp
    version: V1.0.0
    redis-cache:
      enabled: true
      key-prefix: "lvzhi:drp:token:"
      expire-time: 86400

spring:
  redis:
    host: ${REDIS_HOST:localhost}
    port: ${REDIS_PORT:6379}
    password: ${REDIS_PASSWORD:}
    database: 0
    timeout: 2000
```

### 2. 开发环境配置

```yaml
# application-dev.yml
lvzhi:
  drp:
    client-id: test-client-id
    client-secret: test-client-secret
    secret-key: test-secret-key
    base-url: https://open-test.zktapi.com/drp
    redis-cache:
      enabled: false  # 开发环境使用内存缓存
```

### 3. 异常处理

```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(LvzhiDrpException.class)
    public ResponseEntity<ErrorResponse> handleLvzhiException(LvzhiDrpException ex) {
        ErrorResponse error = new ErrorResponse(
            ex.getCode(),
            ex.getMessage(),
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
            "SYSTEM_ERROR",
            "系统内部错误",
            System.currentTimeMillis()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
```

### 4. 性能优化建议

1. **启用Redis缓存**：生产环境务必启用Redis缓存，避免Token重复获取
2. **合理设置超时时间**：根据网络状况调整HTTP客户端超时配置
3. **连接池优化**：根据并发量调整最大连接数和路由连接数
4. **批量请求**：尽量使用批量接口减少API调用次数
5. **异步处理**：对于非实时性要求高的操作使用异步调用

## 🔧 故障排除

### 常见问题

1. **Token获取失败**
   - 检查client-id、client-secret、secret-key配置是否正确
   - 检查网络连接是否正常
   - 检查Redis连接是否正常（如果启用Redis缓存）

2. **API调用失败**
   - 检查base-url配置是否正确
   - 检查API版本是否匹配
   - 查看错误日志获取详细错误信息

3. **Redis缓存不生效**
   - 检查Redis配置是否正确
   - 检查`redis-cache.enabled`是否设置为true
   - 检查RedisTemplate是否已正确配置

### 日志调试

启用DEBUG日志查看详细调用信息：

```yaml
logging:
  level:
    io.github.qwzhang01.luzhi.sdk: DEBUG
```

## 📦 版本信息

- **当前版本**: 1.2.0
- **Spring Boot兼容**: 2.7.x, 3.x
- **Java版本**: 17+
- **Redis支持**: Spring Data Redis 2.7+

## 🤝 贡献指南

欢迎提交Issue和Pull Request来完善这个SDK。

## 📄 许可证

本项目采用 MIT 许可证，详情请查看 LICENSE 文件。

## 📞 支持

如果您在使用过程中遇到问题，可以通过以下方式获取帮助：

1. 查看本文档
2. 查看示例代码
3. 提交Issue
4. 联系技术支持

---

**Happy Coding! 🎉**

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

本SDK支持两种缓存策略，可根据配置自动选择最优方案：

#### 1. Redis缓存（推荐用于生产环境）

当检测到RedisTemplate存在且Redis缓存启用时，优先使用Redis缓存：

**优势：**
- 🗄️ **分布式支持**：多实例部署时Token共享，避免重复获取
- 🔄 **一致性保证**：所有实例使用相同的Token，确保数据一致性
- ⚡ **性能优化**：减少API调用，提升响应速度
- 🛡️ **高可用性**：Redis集群支持，提升系统稳定性

**配置方式：**

```yaml
lvzhi:
  drp:
    redis-cache:
      enabled: true                    # 启用Redis缓存
      key-prefix: "lvzhi:drp:token:"   # 缓存键前缀
      expire-time: 86400              # Token过期时间（秒），默认24小时
```

**Redis依赖配置：**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

**Redis连接配置：**

```yaml
spring:
  redis:
    host: localhost
    port: 6379
    password: your-redis-password
    database: 0
    timeout: 2000
```

#### 2. 内存缓存（适用于开发环境或单实例部署）

当Redis不可用或未启用Redis缓存时，自动回退到内存缓存：

**优势：**
- 🚀 **零配置**：无需额外依赖和配置
- 💾 **轻量级**：不依赖外部服务，部署简单
- ⚡ **高性能**：内存访问速度快

**配置方式：**

```yaml
lvzhi:
  drp:
    redis-cache:
      enabled: false                   # 禁用Redis缓存，使用内存缓存
```

#### 3. 智能缓存选择策略

SDK采用智能缓存选择策略：

1. **自动检测**：启动时自动检测RedisTemplate是否存在
2. **配置优先**：根据`redis-cache.enabled`配置决定是否启用Redis缓存
3. **优雅降级**：Redis不可用时自动回退到内存缓存
4. **双重检查锁**：确保线程安全，避免并发问题

#### 4. 缓存键格式

**Redis缓存键格式：**
```
lvzhi:drp:token:{clientId}
```

例如，clientId为`test-app`时，缓存键为：
```
lvzhi:drp:token:test-app
```

#### 5. 手动缓存管理

```java
@Service
public class CacheManagementService {
    
    @Autowired
    private LvzhiDrpClient lvzhiDrpClient;
    
    public void forceRefreshToken() {
        // 强制清除缓存，下次请求会重新获取Token
        lvzhiDrpClient.clearTokenCache();
    }
    
    public void refreshToken() {
        // 手动刷新Token
        lvzhiDrpClient.refreshToken();
    }
}
```

#### 6. 缓存监控

SDK内置缓存监控功能，可通过日志查看缓存状态：

```log
# 启用Redis缓存时
INFO  - 初始化旅智DRP API客户端（使用Redis缓存） - 基础URL: https://open.zktapi.com/drp

# 使用内存缓存时
INFO  - 初始化旅智DRP API客户端（使用内存缓存） - 基础URL: https://open.zktapi.com/drp
```

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

## 🔔 回调功能说明

### 功能特性

- **全面覆盖**：支持8种酒店信息变更类型的回调处理
- **类型安全**：提供完整的枚举类型定义，避免类型错误
- **自动路由**：根据变更类型自动路由到对应的处理方法
- **错误处理**：内置未知类型检测和错误响应机制
- **易于扩展**：接口设计清晰，易于实现自定义逻辑

### 支持的变更类型

| 变更类型 | 代码 | 说明 | 参数说明 |
|---------|------|------|----------|
| 房态房量变化 | `AvailRooms` | 酒店房态和房量信息变化 | hotelVid, startDate, endDate |
| 产品房态开关 | `ProductRoomState` | 产品房态的每日开关变化 | hotelVid, startDate, endDate |
| 房价变化 | `Rate` | 酒店房价信息变化 | hotelVid, startDate, endDate |
| 酒店下线 | `HotelOffline` | 酒店下线通知 | hotelVid |
| 酒店上线 | `HotelOnline` | 酒店上线通知 | hotelVid |
| 房型静态信息 | `RoomType` | 房型静态信息变化 | hotelVid |
| 产品静态信息 | `Product` | 产品静态信息变化 | hotelVid |
| 酒店静态信息 | `Hotel` | 酒店静态信息变化 | hotelVid |

### 回调请求格式

```json
{
  "hotelVid": 123456,
  "startDate": "2024-03-23",
  "endDate": "2024-03-25",
  "hotelChangeType": "Rate",
  "timestamp": 1644907835216,
  "trackingId": "750021d4-93d7-45dd-b719-434ec4956cea"
}
```

### 回调响应格式

```json
{
  "code": "200",
  "message": "成功",
  "data": "success"
}
```

### 错误响应示例

```json
{
  "code": "400",
  "message": "未知的变更类型：InvalidType",
  "data": "fail"
}
```

### 部署配置

#### 1. 配置回调地址

在旅智分销平台配置您的回调地址：
```
https://your-domain.com/callback/hotelNotify
```

#### 2. 安全配置

建议添加安全验证机制：

```java
@RestController
@RequestMapping("/callback")
@Component
public class HotelNotifyController {
    
    @Autowired
    private HotelChangeCallback hotelChangeCallback;
    
    @PostMapping("/hotelNotify")
    public HotelNotifyResponse handleHotelNotify(
            @RequestBody HotelNotifyRequest request,
            @RequestHeader("X-Signature") String signature) {
        
        // 验证签名
        if (!validateSignature(request, signature)) {
            HotelNotifyResponse response = new HotelNotifyResponse();
            response.setCode("401");
            response.setMessage("签名验证失败");
            response.setData("fail");
            return response;
        }
        
        return hotelChangeCallback.onNotify(request);
    }
    
    private boolean validateSignature(HotelNotifyRequest request, String signature) {
        // 实现签名验证逻辑
        return true;
    }
}
```

#### 3. 异步处理

对于耗时的回调处理，建议使用异步处理：

```java
@Service
public class AsyncHotelChangeCallback implements HotelChangeCallback {
    
    @Async
    @Override
    public void onHotelChange(Long hotelVid) {
        // 异步处理酒店信息变化
        // 可以发送消息到消息队列或使用线程池处理
    }
    
    // 其他方法同样可以添加@Async注解
}
```

### 最佳实践

1. **幂等性处理**：根据trackingId确保回调处理的幂等性
2. **错误重试**：实现适当的错误重试机制
3. **日志记录**：记录回调请求和处理结果
4. **监控告警**：设置回调处理失败告警
5. **性能优化**：对于大数据量处理使用异步方式

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

#### 2. 实现酒店变更回调接口

```java
import io.github.qwzhang01.luzhi.sdk.callback.HotelChangeCallback;
import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelNotifyRequest;
import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelNotifyResponse;
import org.springframework.stereotype.Service;

@Service
public class MyHotelChangeCallback implements HotelChangeCallback {
    
    @Override
    public void onHotelChange(Long hotelVid) {
        // 处理酒店静态信息变化
        System.out.println("酒店静态信息变化: " + hotelVid);
        // 更新本地酒店缓存或数据库
    }
    
    @Override
    public void onRoomTypeChange(Long hotelVid) {
        // 处理房型静态信息变化
        System.out.println("房型静态信息变化: " + hotelVid);
        // 更新本地房型缓存或数据库
    }
    
    @Override
    public void onProductChange(Long hotelVid) {
        // 处理产品静态信息变化
        System.out.println("产品静态信息变化: " + hotelVid);
        // 更新本地产品缓存或数据库
    }
    
    @Override
    public void onHotelOffline(Long hotelVid) {
        // 处理酒店下线
        System.out.println("酒店下线: " + hotelVid);
        // 标记酒店为不可用状态
    }
    
    @Override
    public void onHotelOnline(Long hotelVid) {
        // 处理酒店上线
        System.out.println("酒店上线: " + hotelVid);
        // 标记酒店为可用状态
    }
    
    @Override
    public void onRateChange(Long hotelVid, String startDate, String endDate) {
        // 处理房价变化
        System.out.println("房价变化: " + hotelVid + ", " + startDate + " - " + endDate);
        // 更新指定日期范围内的房价信息
    }
    
    @Override
    public void onProductRoomStateChange(Long hotelVid, String startDate, String endDate) {
        // 处理产品房态的每日开关变化
        System.out.println("产品房态变化: " + hotelVid + ", " + startDate + " - " + endDate);
        // 更新指定日期范围内的产品房态
    }
    
    @Override
    public void onAvailRoomsChange(Long hotelVid, String startDate, String endDate) {
        // 处理房态房量变化
        System.out.println("房态房量变化: " + hotelVid + ", " + startDate + " - " + endDate);
        // 更新指定日期范围内的房态房量
    }
}
```

#### 3. 创建回调控制器

```java
import io.github.qwzhang01.luzhi.sdk.callback.HotelChangeCallback;
import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelNotifyRequest;
import io.github.qwzhang01.luzhi.sdk.dto.notify.HotelNotifyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/callback")
@Component
public class HotelNotifyController {
    
    @Autowired
    private HotelChangeCallback hotelChangeCallback;
    
    @PostMapping("/hotelNotify")
    public HotelNotifyResponse handleHotelNotify(@RequestBody HotelNotifyRequest request) {
        return hotelChangeCallback.onNotify(request);
    }
}
```

#### 4. 处理旅智平台回调的授权验证

当旅智平台调用分销商接口时，需要在回调控制器中添加授权验证：

```java
import io.github.qwzhang01.luzhi.sdk.util.SignatureUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/callback")
@Component
public class HotelNotifyController {
    
    @Autowired
    private HotelChangeCallback hotelChangeCallback;
    
    @PostMapping("/hotelNotify")
    public HotelNotifyResponse handleHotelNotify(
            @RequestBody HotelNotifyRequest request,
            @RequestHeader("Authorization") String authorizationHeader) {
        
        // 验证Authorization头
        if (!SignatureUtil.validateAuthorization(authorizationHeader, "your-username", "your-password")) {
            HotelNotifyResponse response = new HotelNotifyResponse();
            response.setCode("401");
            response.setMessage("授权验证失败");
            response.setData("fail");
            return response;
        }
        
        return hotelChangeCallback.onNotify(request);
    }
    
    // 解析Authorization头获取MD5值
    @GetMapping("/parseAuth")
    public String parseAuthorization(@RequestHeader("Authorization") String authHeader) {
        String[] parsed = SignatureUtil.parseAuthorization(authHeader);
        if (parsed != null) {
            return "MD5值: " + parsed[0];
        }
        return "授权头解析失败";
    }
    
    // 生成Authorization头值
    @GetMapping("/generateAuth")
    public String generateAuthorization() {
        return SignatureUtil.generateAuthorizationHeader("test-user", "test-password");
    }
}
```

### 签名工具方法说明

| 方法名 | 参数 | 返回值 | 说明 |
|--------|------|--------|------|
| `generateSignature()` | url, secretKey, trackingId, method, timestamp, version | String | 生成API请求签名 |
| `md5()` | String data | String | MD5加密（小写） |
| `parseAuthorization()` | String authorizationHeader | String[] | 解析Authorization头，返回[MD5值, null] |
| `validateAuthorization()` | authorizationHeader, username, password | boolean | 验证Authorization头是否有效 |
| `generateAuthorizationHeader()` | username, password | String | 生成Authorization头值 |

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
| `HotelChangeCallback` | 酒店变更回调接口 | `onNotify()`, 各类型变更处理方法 |

### 回调接口方法

| 方法名 | 参数 | 说明 |
|--------|------|------|
| `onNotify()` | `HotelNotifyRequest` | 统一回调处理方法，自动路由到具体方法 |
| `onHotelChange()` | `Long hotelVid` | 酒店静态信息变化 |
| `onRoomTypeChange()` | `Long hotelVid` | 房型静态信息变化 |
| `onProductChange()` | `Long hotelVid` | 产品静态信息变化 |
| `onHotelOffline()` | `Long hotelVid` | 酒店下线通知 |
| `onHotelOnline()` | `Long hotelVid` | 酒店上线通知 |
| `onRateChange()` | `Long hotelVid, String startDate, String endDate` | 房价变化 |
| `onProductRoomStateChange()` | `Long hotelVid, String startDate, String endDate` | 产品房态开关变化 |
| `onAvailRoomsChange()` | `Long hotelVid, String startDate, String endDate` | 房态房量变化 |

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

### v1.1.0 (2024-03-23)

- 🔔 **新增酒店变更回调功能**：支持8种酒店信息变更类型的回调处理
- 🔐 **新增Authorization头解析功能**：支持旅智平台调用分销商接口的授权验证
- 📋 **完整回调接口定义**：提供`HotelChangeCallback`接口和相关的DTO类
- 🎯 **类型安全枚举**：新增`HotelChangeType`枚举，支持所有变更类型
- 🔄 **自动路由机制**：根据变更类型自动路由到对应的处理方法
- 🛡️ **错误处理增强**：内置未知类型检测和错误响应机制
- 📚 **详细文档**：添加回调功能使用指南和最佳实践
- ⏰ **新增LocalDateTimeUtil工具类**：提供字符串与LocalDateTime之间的转换功能，支持多种常见格式

#### 新增方法

- `SignatureUtil.parseAuthorization()` - 解析Authorization头中的MD5加密信息
- `SignatureUtil.validateAuthorization()` - 验证Authorization头是否有效
- `SignatureUtil.generateAuthorizationHeader()` - 生成Authorization头值
- `LocalDateTimeUtil.parse()` - 按指定格式解析字符串为LocalDateTime
- `LocalDateTimeUtil.parseFlexible()` - 灵活解析，自动尝试多种格式
- `LocalDateTimeUtil.format()` - 将LocalDateTime格式化为字符串
- `LocalDateTimeUtil.parseISO()` / `formatISO()` - ISO格式解析和格式化
- `LocalDateTimeUtil.parseCompact()` / `formatCompact()` - 紧凑格式解析和格式化

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

### LocalDateTimeUtil - 日期时间工具类

`LocalDateTimeUtil` 提供字符串与LocalDateTime之间的转换功能，支持多种常见格式。

#### 主要功能

- 支持多种日期时间格式的解析和格式化
- 提供灵活的解析方法，自动尝试多种格式
- 支持日期字符串自动补充时间部分

#### 使用示例

```java
import io.github.qwzhang01.luzhi.sdk.util.LocalDateTimeUtil;
import java.time.LocalDateTime;

public class DateTimeExample {
    
    public void demo() {
        // 使用默认格式解析
        LocalDateTime dt1 = LocalDateTimeUtil.parse("2024-03-23 14:30:00");
        
        // 使用指定格式解析
        LocalDateTime dt2 = LocalDateTimeUtil.parse("2024-03-23T14:30:00", "yyyy-MM-dd'T'HH:mm:ss");
        
        // 使用ISO格式解析
        LocalDateTime dt3 = LocalDateTimeUtil.parseISO("2024-03-23T14:30:00");
        
        // 使用紧凑格式解析
        LocalDateTime dt4 = LocalDateTimeUtil.parseCompact("20240323143000");
        
        // 灵活解析，自动尝试多种格式
        LocalDateTime dt5 = LocalDateTimeUtil.parseFlexible("2024-03-23 14:30:00");
        LocalDateTime dt6 = LocalDateTimeUtil.parseFlexible("2024-03-23"); // 自动补充时间为00:00:00
        
        // 格式化输出
        String formatted1 = LocalDateTimeUtil.format(dt1); // "2024-03-23 14:30:00"
        String formatted2 = LocalDateTimeUtil.formatISO(dt1); // "2024-03-23T14:30:00"
        String formatted3 = LocalDateTimeUtil.formatCompact(dt1); // "20240323143000"
    }
}
```

#### 支持的格式常量

| 常量名 | 格式 | 说明 |
|--------|------|------|
| `DEFAULT_PATTERN` | yyyy-MM-dd HH:mm:ss | 默认格式 |
| `ISO_PATTERN` | yyyy-MM-dd'T'HH:mm:ss | ISO 8601格式 |
| `COMPACT_PATTERN` | yyyyMMddHHmmss | 紧凑格式 |
| `DATE_PATTERN` | yyyy-MM-dd | 仅日期格式 |
| `TIME_PATTERN` | HH:mm:ss | 仅时间格式 |

#### 主要方法说明

| 方法名 | 参数 | 返回值 | 说明 |
|--------|------|--------|------|
| `parse(String, String)` | dateTimeStr, pattern | LocalDateTime | 按指定格式解析 |
| `parse(String)` | dateTimeStr | LocalDateTime | 按默认格式解析 |
| `parseISO(String)` | dateTimeStr | LocalDateTime | 按ISO格式解析 |
| `parseCompact(String)` | dateTimeStr | LocalDateTime | 按紧凑格式解析 |
| `parseFlexible(String)` | dateTimeStr | LocalDateTime | 灵活解析，尝试多种格式 |
| `parseDateWithTime(String)` | dateStr | LocalDateTime | 日期字符串补充时间 |
| `format(LocalDateTime, String)` | dateTime, pattern | String | 按指定格式格式化 |
| `format(LocalDateTime)` | dateTime | String | 按默认格式格式化 |
| `formatISO(LocalDateTime)` | dateTime | String | 按ISO格式格式化 |
| `formatCompact(LocalDateTime)` | dateTime | String | 按紧凑格式格式化 |