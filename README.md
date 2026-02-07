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

## 🚀 快速开始

### Maven 依赖

在 `pom.xml` 中添加依赖：

```xml
<dependency>
    <groupId>io.github.qwzhang01</groupId>
    <artifactId>seven-lvzhi-sdk-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle 依赖

在 `build.gradle` 中添加依赖：

```gradle
implementation 'io.github.qwzhang01:seven-lvzhi-sdk-spring-boot-starter:1.0.0'
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

## 🔧 高级配置

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
