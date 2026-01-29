package io.github.qwzhang01.luzhi.sdk;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.qwzhang01.luzhi.sdk.common.ApiMethod;
import io.github.qwzhang01.luzhi.sdk.common.BaseResponse;
import io.github.qwzhang01.luzhi.sdk.common.PageData;
import io.github.qwzhang01.luzhi.sdk.dto.auth.TokenData;
import io.github.qwzhang01.luzhi.sdk.dto.daily.ProductDailyResponse;
import io.github.qwzhang01.luzhi.sdk.dto.daily.RoomTypeDailyResponse;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelBasicInfo;
import io.github.qwzhang01.luzhi.sdk.dto.hotel.HotelDetailInfo;
import io.github.qwzhang01.luzhi.sdk.dto.member.BindMemberResponse;
import io.github.qwzhang01.luzhi.sdk.dto.member.MemberInfo;
import io.github.qwzhang01.luzhi.sdk.dto.member.MemberNotifyResponse;
import io.github.qwzhang01.luzhi.sdk.dto.order.*;
import io.github.qwzhang01.luzhi.sdk.dto.product.ProductDetail;
import io.github.qwzhang01.luzhi.sdk.dto.product.ProductListResponse;
import io.github.qwzhang01.luzhi.sdk.dto.region.RegionInfo;
import io.github.qwzhang01.luzhi.sdk.dto.roomtype.RoomTypeDetail;
import io.github.qwzhang01.luzhi.sdk.dto.roomtype.RoomTypeInfo;
import io.github.qwzhang01.luzhi.sdk.service.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 旅智分销平台API集成测试
 * 按照ApiMethod顺序执行所有接口，并生成Markdown报告
 *
 * @author lvzhi-drp-starter
 */
@SpringBootTest(classes = TestApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LvzhiDrpApiIntegrationTest {

    @Value("${lvzhi.drp.base-url}")
    private String baseUrl;

    @Value("${lvzhi.drp.client-id}")
    private String clientId;

    @Value("${lvzhi.drp.client-secret}")
    private String clientSecret;

    @Value("${lvzhi.drp.secret-key}")
    private String secretKey;

    @Value("${lvzhi.drp.version}")
    private String version;

    @Value("${test.hotel-vid}")
    private Long hotelVid;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<ApiTestResult> testResults = new ArrayList<>();
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // 动态获取的ID
    private static Long roomTypeId;
    private static Long productId;
    private static String createdOrderId;

    @Autowired
    private AuthorizeService authorizeService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RoomTypeService roomTypeService;

    @Autowired
    private ProductService productService;

    @Autowired
    private DailyService dailyService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MemberService memberService;

    @BeforeAll
    static void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @AfterAll
    static void generateReport() throws IOException {
        String reportPath = "src/test/resources/api-test-report.md";
        File reportFile = new File(reportPath);
        reportFile.getParentFile().mkdirs();

        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFile))) {
            writer.println("# 旅智分销平台API接口测试报告");
            writer.println();
            writer.println("**生成时间**: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println();
            writer.println("**测试环境**: https://open-qa.zktapi.com/drp");
            writer.println();
            writer.println("**酒店VID**: 200372");
            writer.println();
            writer.println("---");
            writer.println();

            // 汇总表格
            writer.println("## 测试结果汇总");
            writer.println();
            writer.println("| 序号 | 接口名称 | 接口描述 | 请求URL | 执行状态 |");
            writer.println("|------|---------|---------|---------|---------|");

            int index = 1;
            for (ApiTestResult result : testResults) {
                String status = result.isSuccess() ? "✅ 成功" : "❌ 失败";
                writer.printf("| %d | %s | %s | %s | %s |%n",
                        index++,
                        result.getApiMethod().name(),
                        result.getApiMethod().getDescription(),
                        result.getRequestUrl(),
                        status);
            }

            writer.println();
            writer.println("---");
            writer.println();

            // 详细结果
            writer.println("## 详细测试结果");
            writer.println();

            index = 1;
            for (ApiTestResult result : testResults) {
                writer.printf("### %d. %s - %s%n%n", index++,
                        result.getApiMethod().name(),
                        result.getApiMethod().getDescription());
                writer.println("**请求URL**: `" + result.getRequestUrl() + "`");
                writer.println();
                writer.println("**请求参数**:");
                writer.println("```json");
                writer.println(result.getRequestParams());
                writer.println("```");
                writer.println();
                writer.println("**执行状态**: " + (result.isSuccess() ? "✅ 成功" :
                        "❌ 失败"));
                writer.println();

                // REGION_LIST_TREES 不保存返回数据
                if (result.getApiMethod() == ApiMethod.REGION_LIST_TREES) {
                    writer.println("**返回数据**: (数据量较大，已省略)");
                } else {
                    writer.println("**返回数据**:");
                    writer.println("```json");
                    writer.println(result.getResponseData());
                    writer.println("```");
                }

                if (result.getErrorMessage() != null) {
                    writer.println();
                    writer.println("**错误信息**: " + result.getErrorMessage());
                }

                writer.println();
                writer.println("---");
                writer.println();
            }
        }

        System.out.println("测试报告已生成: " + reportFile.getAbsolutePath());
    }

    /**
     * 1. 获取Token
     */
    @Test
    @Order(1)
    @DisplayName("1. AUTHORIZE_GET_TOKEN - 获取Token")
    void testGetToken() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.AUTHORIZE_GET_TOKEN;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("clientId", clientId);
        params.put("clientSecret", clientSecret);

        BaseResponse<TokenData> response = authorizeService.getToken(clientId
                , clientSecret);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "获取Token失败: " + response.getMessage());
    }

    /**
     * 2. 查询三级城市信息
     */
    @Test
    @Order(2)
    @DisplayName("2. REGION_LIST_TREES - 查询三级城市信息")
    void testListRegionTrees() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.REGION_LIST_TREES;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("provinceId", null);

        BaseResponse<List<RegionInfo>> response =
                regionService.listRegionTrees(null);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                "(数据量较大，已省略)", // 不保存返回数据
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "查询区域树失败: " + response.getMessage());
    }

    /**
     * 3. 酒店基础数据列表
     */
    @Test
    @Order(3)
    @DisplayName("3. HOTEL_LIST - 酒店基础数据列表")
    void testHotelList() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.HOTEL_LIST;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("provinceId", null);
        params.put("cityId", null);
        params.put("pageIndex", 1);
        params.put("pageSize", 10);

        BaseResponse<PageData<HotelBasicInfo>> response =
                hotelService.list(null, null, 1, 10);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "查询酒店列表失败: " + response.getMessage());
    }

    /**
     * 4. 酒店详情
     */
    @Test
    @Order(4)
    @DisplayName("4. HOTEL_GET_BY_VID - 酒店详情")
    void testHotelGetByVid() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.HOTEL_GET_BY_VID;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);

        BaseResponse<HotelDetailInfo> response =
                hotelService.getByVid(hotelVid);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "获取酒店详情失败: " + response.getMessage());
    }

    /**
     * 5. 酒店房型列表
     */
    @Test
    @Order(5)
    @DisplayName("5. ROOM_TYPE_LIST_BY_HOTEL_VID - 酒店房型列表")
    void testRoomTypeListByHotelVid() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ROOM_TYPE_LIST_BY_HOTEL_VID;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);

        BaseResponse<List<RoomTypeInfo>> response =
                roomTypeService.listByHotelVid(hotelVid);

        // 获取第一个房型ID供后续使用
        if (response.isSuccess() && response.getData() != null && !response.getData().isEmpty()) {
            roomTypeId = response.getData().get(0).getRoomTypeId();
        }

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "查询房型列表失败: " + response.getMessage());
    }

    /**
     * 6. 酒店房型详情
     */
    @Test
    @Order(6)
    @DisplayName("6. ROOM_TYPE_GET_BY_ID - 酒店房型详情")
    void testRoomTypeGetById() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ROOM_TYPE_GET_BY_ID;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 如果没有获取到roomTypeId，使用默认值
        Long testRoomTypeId = roomTypeId != null ? roomTypeId : 1L;

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);
        params.put("roomTypeId", testRoomTypeId);

        BaseResponse<RoomTypeDetail> response =
                roomTypeService.getById(hotelVid, testRoomTypeId);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "获取房型详情失败: " + response.getMessage());
    }

    /**
     * 7. 酒店产品列表
     */
    @Test
    @Order(7)
    @DisplayName("7. PRODUCT_LIST - 酒店产品列表")
    void testProductList() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.PRODUCT_LIST;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);

        BaseResponse<ProductListResponse> response =
                productService.list(hotelVid);

        // 获取第一个产品ID供后续使用
        if (response.isSuccess() && response.getData() != null) {
            ProductListResponse firstProduct = response.getData();
            if (firstProduct.getProducts() != null && !firstProduct.getProducts().isEmpty()) {
                productId = firstProduct.getProducts().get(0).getProductId();
                if (roomTypeId == null) {
                    roomTypeId =
                            firstProduct.getProducts().get(0).getRoomTypeId();
                }
            }
        }

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "查询产品列表失败: " + response.getMessage());
    }

    /**
     * 8. 酒店产品详情
     */
    @Test
    @Order(8)
    @DisplayName("8. PRODUCT_GET_BY_ID - 酒店产品详情")
    void testProductGetById() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.PRODUCT_GET_BY_ID;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 如果没有获取到productId，使用默认值
        Long testProductId = productId != null ? productId : 1L;

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);
        params.put("productId", testProductId);

        BaseResponse<ProductDetail> response =
                productService.getById(hotelVid, testProductId);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "获取产品详情失败: " + response.getMessage());
    }

    /**
     * 9. 酒店房型每日明细
     */
    @Test
    @Order(9)
    @DisplayName("9. ROOM_TYPE_LIST_DAILY_DETAILS - 酒店房型每日明细")
    void testRoomTypeDailyDetails() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ROOM_TYPE_LIST_DAILY_DETAILS;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 如果没有获取到roomTypeId，使用默认值
        Long testRoomTypeId = roomTypeId != null ? roomTypeId : 1L;

        String startDate = LocalDate.now().plusDays(1).format(DATE_FORMATTER);
        String endDate = LocalDate.now().plusDays(3).format(DATE_FORMATTER);

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);
        params.put("roomTypeId", testRoomTypeId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        BaseResponse<RoomTypeDailyResponse> response =
                dailyService.listRoomTypeDailyDetails(
                hotelVid, testRoomTypeId, startDate, endDate);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "查询房型每日明细失败: " + response.getMessage());
    }

    /**
     * 10. 酒店产品每日明细
     */
    @Test
    @Order(10)
    @DisplayName("10. PRODUCT_HOTEL_LIST_DAILY_DETAILS - 酒店产品每日明细")
    void testProductDailyDetails() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.PRODUCT_HOTEL_LIST_DAILY_DETAILS;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 如果没有获取到roomTypeId和productId，使用默认值
        Long testRoomTypeId = roomTypeId != null ? roomTypeId : 1L;
        Long testProductId = productId != null ? productId : 1L;

        String startDate = LocalDate.now().plusDays(1).format(DATE_FORMATTER);
        String endDate = LocalDate.now().plusDays(3).format(DATE_FORMATTER);

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("hotelVid", hotelVid);
        params.put("roomTypeId", testRoomTypeId);
        params.put("productId", testProductId);
        params.put("startDate", startDate);
        params.put("endDate", endDate);

        BaseResponse<ProductDailyResponse> response =
                dailyService.listProductDailyDetails(
                hotelVid, testRoomTypeId, testProductId, startDate, endDate);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        Assertions.assertTrue(response.isSuccess(),
                "查询产品每日明细失败: " + response.getMessage());
    }

    /**
     * 11. 创建订单 - 跳过（需要真实数据）
     */
    @Test
    @Order(11)
    @DisplayName("11. ORDER_CREATE - 创建订单")
    void testOrderCreate() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ORDER_CREATE;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 如果没有获取到roomTypeId和productId，跳过此测试
        Long testRoomTypeId = roomTypeId != null ? roomTypeId : 1L;
        Long testProductId = productId != null ? productId : 1L;

        String checkInDate = LocalDate.now().plusDays(7).format(DATE_FORMATTER);
        String checkOutDate =
                LocalDate.now().plusDays(8).format(DATE_FORMATTER);
        String drpOrderId = "TEST_" + System.currentTimeMillis();

        CreateOrderRequest request = new CreateOrderRequest();
        request.setDrpOrderId(drpOrderId);
        request.setHotelVid(hotelVid);
        request.setRoomTypeId(testRoomTypeId);
        request.setProductId(testProductId);
        request.setCheckInDate(checkInDate);
        request.setCheckOutDate(checkOutDate);
        request.setRoomCount(1);
        request.setTotalAmount(new BigDecimal("300.00"));
        request.setSettleAmount(new BigDecimal("300.00"));
        request.setPaymentType(1);
        request.setLatestArrivalTime("18:00");

        // 入住人信息
        CreateOrderRequest.Guest guest = new CreateOrderRequest.Guest();
        guest.setGuestName("测试用户");
        guest.setGuestPhone("13800138000");
        guest.setContactPhone("13800138000");
        guest.setGuestIdType(0);
        guest.setGuestIdNo("110101199001011234");
        guest.setGuests(1);
        request.setGuests(Collections.singletonList(guest));

        // 联系人信息
        CreateOrderRequest.Contact contact = new CreateOrderRequest.Contact();
        contact.setName("测试联系人");
        contact.setPhone("13800138000");
        request.setContact(contact);

        // 每日明细
        CreateOrderRequest.DailyInfo dailyInfo =
                new CreateOrderRequest.DailyInfo();
        dailyInfo.setRoomNo(1);
        dailyInfo.setCheckDate(checkInDate);
        dailyInfo.setRate(new BigDecimal("300.00"));
        dailyInfo.setSettleRate(new BigDecimal("300.00"));
        dailyInfo.setCurrency("CNY");
        request.setDailyInfos(Collections.singletonList(dailyInfo));

        // 取消规则
        CreateOrderRequest.CancelRule cancelRule =
                new CreateOrderRequest.CancelRule();
        cancelRule.setCancelType(0);
        request.setCancelRule(cancelRule);

        BaseResponse<CreateOrderResponse> response =
                orderService.create(request);

        // 保存订单ID供取消使用
        if (response.isSuccess() && response.getData() != null) {
            createdOrderId = response.getData().getOrderId();
        }

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(request),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        // 创建订单可能会失败（依赖实际库存），这里不做强断言
        System.out.println("创建订单结果: " + response.getMessage());
    }

    /**
     * 12. 取消订单
     */
    @Test
    @Order(12)
    @DisplayName("12. ORDER_CANCEL - 取消订单")
    void testOrderCancel() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ORDER_CANCEL;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 使用刚创建的订单ID，如果没有则使用模拟ID
        String testOrderId = createdOrderId != null ? createdOrderId :
                "TEST_ORDER_NOT_EXISTS";
        String testDrpOrderId = "TEST_" + System.currentTimeMillis();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("drpOrderId", testDrpOrderId);
        params.put("orderId", testOrderId);
        params.put("hotelVid", hotelVid);
        params.put("reason", "测试取消订单");

        BaseResponse<CancelOrderResponse> response = orderService.cancel(
                testDrpOrderId, testOrderId, hotelVid, "测试取消订单");

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        // 取消订单可能会失败（订单不存在等），这里不做强断言
        System.out.println("取消订单结果: " + response.getMessage());
    }

    /**
     * 13. 产品可定检查
     */
    @Test
    @Order(13)
    @DisplayName("13. ORDER_RESERVE_VALIDATE - 产品可定检查")
    void testReserveValidate() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ORDER_RESERVE_VALIDATE;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 如果没有获取到roomTypeId和productId，使用默认值
        Long testRoomTypeId = roomTypeId != null ? roomTypeId : 1L;
        Long testProductId = productId != null ? productId : 1L;

        String checkInDate = LocalDate.now().plusDays(7).format(DATE_FORMATTER);
        String checkOutDate =
                LocalDate.now().plusDays(8).format(DATE_FORMATTER);

        ReserveValidateRequest request = new ReserveValidateRequest();
        request.setHotelVid(hotelVid);
        request.setRoomTypeId(testRoomTypeId);
        request.setProductId(testProductId);
        request.setCheckInDate(checkInDate);
        request.setCheckOutDate(checkOutDate);
        request.setCount(1);

        // 订单每日明细
        ReserveValidateRequest.OrderDailyDetail dailyDetail =
                new ReserveValidateRequest.OrderDailyDetail();
        dailyDetail.setDate(checkInDate);
        dailyDetail.setRate(new BigDecimal("300.00"));
        request.setOrderDailyDetails(Collections.singletonList(dailyDetail));

        BaseResponse<ReserveValidateResponse> response =
                orderService.reserveValidate(request);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(request),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        // 可定检查可能会失败（依赖实际库存），这里不做强断言
        System.out.println("产品可定检查结果: " + response.getMessage());
    }

    /**
     * 14. 订单查询
     */
    @Test
    @Order(14)
    @DisplayName("14. ORDER_GET_BY_ID - 订单查询")
    void testOrderGetById() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.ORDER_GET_BY_ID;
        String requestUrl = baseUrl + apiMethod.getUrl();

        // 使用刚创建的订单ID，如果没有则使用模拟ID
        String testOrderId = createdOrderId != null ? createdOrderId :
                "TEST_ORDER_NOT_EXISTS";

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("orderId", testOrderId);

        BaseResponse<OrderDetailInfo> response =
                orderService.getByOrderId(testOrderId);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        // 订单查询可能会失败（订单不存在等），这里不做强断言
        System.out.println("订单查询结果: " + response.getMessage());
    }

    /**
     * 15. 会员绑定
     */
    @Test
    @Order(15)
    @DisplayName("15. MEMBER_BIND - 会员绑定")
    void testMemberBind() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.MEMBER_BIND;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("blocCode", "TEST_BLOC");
        params.put("eventType", 1);
        params.put("memberNo", null);
        params.put("phoneNo", "13800138000");
        params.put("name", "测试会员");
        params.put("idType", 0);
        params.put("idNo", "110101199001011234");
        params.put("memberLevel", 1);

        BaseResponse<BindMemberResponse> response = memberService.bind(
                "TEST_BLOC", 1, null, "13800138000",
                "测试会员", 0, "110101199001011234", 1);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        // 会员绑定可能会失败（依赖实际环境），这里不做强断言
        System.out.println("会员绑定结果: " + response.getMessage());
    }

    /**
     * 16. 会员信息查询
     */
    @Test
    @Order(16)
    @DisplayName("16. MEMBER_SEARCH - 会员信息查询")
    void testMemberSearch() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.MEMBER_SEARCH;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("blocCode", "TEST_BLOC");
        params.put("memberNo", "TEST_MEMBER_001");
        params.put("phoneNo", 13800138000L);

        BaseResponse<MemberInfo> response = memberService.search(
                "TEST_BLOC", "TEST_MEMBER_001", 13800138000L);

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                objectMapper.writeValueAsString(response),
                response.isSuccess(),
                response.isSuccess() ? null : response.getMessage()
        ));

        // 会员查询可能会失败（会员不存在等），这里不做强断言
        System.out.println("会员查询结果: " + response.getMessage());
    }

    /**
     * 17. 会员信息变化通知
     */
    @Test
    @Order(17)
    @DisplayName("17. MEMBER_NOTIFY - 会员信息变化通知")
    void testMemberNotify() throws JsonProcessingException {
        ApiMethod apiMethod = ApiMethod.MEMBER_NOTIFY;
        String requestUrl = baseUrl + apiMethod.getUrl();

        Map<String, Object> params = new LinkedHashMap<>();
        params.put("memberNo", "TEST_MEMBER_001");
        params.put("phoneNo", "13800138000");
        params.put("blocCode", "TEST_BLOC");
        params.put("memberLevel", "2");
        params.put("name", "测试会员");

        boolean success = false;
        String responseStr = "";
        String errorMsg = null;

        try {
            BaseResponse<MemberNotifyResponse> response = memberService.notify(
                    "TEST_MEMBER_001", "13800138000", "TEST_BLOC", "2", "测试会员");
            success = response.isSuccess();
            responseStr = objectMapper.writeValueAsString(response);
            errorMsg = success ? null : response.getMessage();
        } catch (Exception e) {
            errorMsg = e.getMessage();
            responseStr = "{\"error\": \"" + errorMsg + "\"}";
        }

        testResults.add(new ApiTestResult(
                apiMethod,
                requestUrl,
                objectMapper.writeValueAsString(params),
                responseStr,
                success,
                errorMsg
        ));

        // 会员通知可能会失败（依赖实际环境），这里不做强断言
        System.out.println("会员通知结果: " + (errorMsg != null ? errorMsg : "成功"));
    }

    /**
     * API测试结果
     */
    static class ApiTestResult {
        private final ApiMethod apiMethod;
        private final String requestUrl;
        private final String requestParams;
        private final String responseData;
        private final boolean success;
        private final String errorMessage;

        public ApiTestResult(ApiMethod apiMethod, String requestUrl,
                             String requestParams,
                             String responseData, boolean success,
                             String errorMessage) {
            this.apiMethod = apiMethod;
            this.requestUrl = requestUrl;
            this.requestParams = requestParams;
            this.responseData = responseData;
            this.success = success;
            this.errorMessage = errorMessage;
        }

        public ApiMethod getApiMethod() {
            return apiMethod;
        }

        public String getRequestUrl() {
            return requestUrl;
        }

        public String getRequestParams() {
            return requestParams;
        }

        public String getResponseData() {
            return responseData;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
    }
}
