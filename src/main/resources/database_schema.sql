-- 旅智分销平台数据库表结构设计
-- 基于同程酒店PMS接口文档 V1.0.6

-- ==================== 基础配置表 ====================

-- 国家表
CREATE TABLE country (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '国家ID',
    country_code VARCHAR(10) NOT NULL COMMENT '国家代码',
    country_name VARCHAR(100) NOT NULL COMMENT '国家名称',
    native_code VARCHAR(10) COMMENT '国家码',
    area_code VARCHAR(10) COMMENT '区域码',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_country_code (country_code),
    INDEX idx_country_name (country_name)
) COMMENT '国家信息表';

-- 省份表
CREATE TABLE province (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '省份ID',
    country_id BIGINT NOT NULL COMMENT '国家ID',
    province_code VARCHAR(10) NOT NULL COMMENT '省份代码',
    province_name VARCHAR(100) NOT NULL COMMENT '省份名称',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (country_id) REFERENCES country(id),
    INDEX idx_province_code (province_code),
    INDEX idx_province_name (province_name)
) COMMENT '省份信息表';

-- 城市表
CREATE TABLE city (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '城市ID',
    province_id BIGINT NOT NULL COMMENT '省份ID',
    city_code VARCHAR(10) NOT NULL COMMENT '城市代码',
    city_name VARCHAR(100) NOT NULL COMMENT '城市名称',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (province_id) REFERENCES province(id),
    INDEX idx_city_code (city_code),
    INDEX idx_city_name (city_name)
) COMMENT '城市信息表';

-- 区域表
CREATE TABLE district (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '区域ID',
    city_id BIGINT NOT NULL COMMENT '城市ID',
    district_code VARCHAR(10) NOT NULL COMMENT '区域代码',
    district_name VARCHAR(100) NOT NULL COMMENT '区域名称',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (city_id) REFERENCES city(id),
    INDEX idx_district_code (district_code),
    INDEX idx_district_name (district_name)
) COMMENT '区域信息表';

-- ==================== 酒店相关表 ====================

-- 酒店集团表
CREATE TABLE hotel_group (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '集团ID',
    group_code VARCHAR(50) NOT NULL UNIQUE COMMENT '集团代码',
    group_name VARCHAR(200) NOT NULL COMMENT '集团名称',
    contact_phone VARCHAR(20) COMMENT '联系电话',
    contact_email VARCHAR(100) COMMENT '联系邮箱',
    address VARCHAR(500) COMMENT '地址',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_group_code (group_code),
    INDEX idx_group_name (group_name)
) COMMENT '酒店集团表';

-- 酒店表
CREATE TABLE hotel (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '酒店ID',
    hotel_vid BIGINT NOT NULL UNIQUE COMMENT '酒店VID（分销平台唯一标识）',
    hotel_group_id BIGINT COMMENT '所属集团ID',
    hotel_name VARCHAR(200) NOT NULL COMMENT '酒店名称',
    country_id BIGINT NOT NULL COMMENT '国家ID',
    province_id BIGINT NOT NULL COMMENT '省份ID',
    city_id BIGINT NOT NULL COMMENT '城市ID',
    district_id BIGINT COMMENT '区域ID',
    address VARCHAR(500) NOT NULL COMMENT '详细地址',
    longitude DECIMAL(10, 6) COMMENT '经度',
    latitude DECIMAL(10, 6) COMMENT '纬度',
    tel_main_code VARCHAR(20) COMMENT '主要联系电话',
    tel_ext_code VARCHAR(20) COMMENT '分机号',
    invoice_type TINYINT DEFAULT 0 COMMENT '开票类型：0-销售端提供，1-酒店开具',
    customer_type TINYINT DEFAULT 0 COMMENT '客户类型：0-不区分，1-仅内宾，2-仅外宾，3-仅日宾，4-仅大陆客人',
    support_preference TINYINT DEFAULT 0 COMMENT '支持偏好：0-不支持，1-支持',
    status TINYINT DEFAULT 1 COMMENT '状态：0-下线，1-上线',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (hotel_group_id) REFERENCES hotel_group(id),
    FOREIGN KEY (country_id) REFERENCES country(id),
    FOREIGN KEY (province_id) REFERENCES province(id),
    FOREIGN KEY (city_id) REFERENCES city(id),
    FOREIGN KEY (district_id) REFERENCES district(id),
    INDEX idx_hotel_vid (hotel_vid),
    INDEX idx_hotel_name (hotel_name),
    INDEX idx_city_id (city_id),
    INDEX idx_status (status)
) COMMENT '酒店基础信息表';

-- 酒店图片表
CREATE TABLE hotel_image (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '图片ID',
    hotel_id BIGINT NOT NULL COMMENT '酒店ID',
    album_type VARCHAR(50) NOT NULL COMMENT '相册类型：HEAD_IMG_ALBUM-头图，OUTWARD_ALBUM-外观等',
    album_name VARCHAR(100) COMMENT '相册名称',
    image_url VARCHAR(500) NOT NULL COMMENT '图片URL',
    sort_order INT DEFAULT 0 COMMENT '排序顺序',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    INDEX idx_hotel_id (hotel_id),
    INDEX idx_album_type (album_type)
) COMMENT '酒店图片表';

-- ==================== 房型相关表 ====================

-- 房型表
CREATE TABLE room_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '房型ID',
    room_type_id BIGINT NOT NULL UNIQUE COMMENT '房型ID（分销平台唯一标识）',
    hotel_id BIGINT NOT NULL COMMENT '酒店ID',
    room_type_name VARCHAR(200) NOT NULL COMMENT '房型名称',
    window_type VARCHAR(20) DEFAULT 'UNKNOWN' COMMENT '窗户类型：WIN-有窗，SWIN-部分有窗，WINL-无窗等',
    floor VARCHAR(50) COMMENT '楼层',
    area VARCHAR(50) COMMENT '面积',
    broadband TINYINT DEFAULT 1 COMMENT '宽带类型：1-免费提供，2-不提供，3-部分收费等',
    wifi TINYINT DEFAULT 1 COMMENT 'WiFi类型：1-免费提供，2-不提供，3-部分收费等',
    max_occupancy INT DEFAULT 0 COMMENT '最大入住人数，0表示不限制',
    beds_relation VARCHAR(10) COMMENT '床型关系：AND-与关系，OR-或关系',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    INDEX idx_room_type_id (room_type_id),
    INDEX idx_hotel_id (hotel_id),
    INDEX idx_room_type_name (room_type_name)
) COMMENT '房型信息表';

-- 房型床型表
CREATE TABLE room_bed_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '床型ID',
    room_type_id BIGINT NOT NULL COMMENT '房型ID',
    bed_type VARCHAR(20) NOT NULL COMMENT '床型：DOUBLE-双床，SINGLE-单人床等',
    bed_type_desc VARCHAR(100) COMMENT '床型描述',
    bed_spec_code VARCHAR(50) COMMENT '床型规格编码',
    bed_spec_desc VARCHAR(100) COMMENT '床型规格描述',
    bed_num INT DEFAULT 1 COMMENT '床型数量',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (room_type_id) REFERENCES room_type(id),
    INDEX idx_room_type_id (room_type_id),
    INDEX idx_bed_type (bed_type)
) COMMENT '房型床型表';

-- 房型图片表
CREATE TABLE room_type_image (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '图片ID',
    room_type_id BIGINT NOT NULL COMMENT '房型ID',
    image_url VARCHAR(500) NOT NULL COMMENT '图片URL',
    sort_order INT DEFAULT 0 COMMENT '排序顺序',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (room_type_id) REFERENCES room_type(id),
    INDEX idx_room_type_id (room_type_id)
) COMMENT '房型图片表';

-- ==================== 产品相关表 ====================

-- 价格方案表
CREATE TABLE rate_plan (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '价格方案ID',
    rate_plan_id BIGINT NOT NULL UNIQUE COMMENT '价格方案ID（分销平台唯一标识）',
    rate_plan_name VARCHAR(200) NOT NULL COMMENT '价格方案名称',
    rate_plan_type TINYINT DEFAULT 0 COMMENT '价格方案类型：0-普通房价格，1-钟点房价格',
    start_date DATE COMMENT '开始日期',
    end_date DATE COMMENT '结束日期',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_rate_plan_id (rate_plan_id),
    INDEX idx_rate_plan_name (rate_plan_name)
) COMMENT '价格方案表';

-- 价格方案时间段表（钟点房使用）
CREATE TABLE rate_plan_time_bucket (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '时间段ID',
    rate_plan_id BIGINT NOT NULL COMMENT '价格方案ID',
    start_time TIME COMMENT '开始时间',
    end_time TIME COMMENT '结束时间',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (rate_plan_id) REFERENCES rate_plan(id),
    INDEX idx_rate_plan_id (rate_plan_id)
) COMMENT '价格方案时间段表';

-- 产品表
CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '产品ID',
    product_id BIGINT NOT NULL UNIQUE COMMENT '产品ID（分销平台唯一标识）',
    hotel_id BIGINT NOT NULL COMMENT '酒店ID',
    room_type_id BIGINT NOT NULL COMMENT '房型ID',
    rate_plan_id BIGINT NOT NULL COMMENT '价格方案ID',
    product_name VARCHAR(200) NOT NULL COMMENT '产品名称',
    product_type TINYINT DEFAULT 0 COMMENT '产品类型：0-普通产品，1-会员独享产品，2-协议产品',
    meal_type TINYINT DEFAULT 0 COMMENT '餐食类型：0-未知，1-早餐，2-午餐，3-晚餐',
    meal_count INT DEFAULT 0 COMMENT '餐食份数',
    payment_type TINYINT DEFAULT 0 COMMENT '支付类型：0-未知，1-现付，2-预付，3-信用住',
    cancel_type TINYINT DEFAULT 0 COMMENT '取消类型：0-免费取消，1-限时取消，2-不可取消',
    stock_type TINYINT DEFAULT 0 COMMENT '库存类型：0-房型库存，1-产品库存（全渠道共享），2-产品渠道独享库存',
    max_occupancy INT DEFAULT 0 COMMENT '最大入住人数',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    FOREIGN KEY (room_type_id) REFERENCES room_type(id),
    FOREIGN KEY (rate_plan_id) REFERENCES rate_plan(id),
    INDEX idx_product_id (product_id),
    INDEX idx_hotel_id (hotel_id),
    INDEX idx_room_type_id (room_type_id)
) COMMENT '酒店产品表';

-- 产品每日价格日历表
CREATE TABLE product_daily_price (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '价格日历ID',
    product_id BIGINT NOT NULL COMMENT '产品ID',
    date DATE NOT NULL COMMENT '日期',
    rate DECIMAL(10, 2) NOT NULL COMMENT '价格',
    total_count INT DEFAULT 0 COMMENT '总库存',
    available_count INT DEFAULT 0 COMMENT '可用库存',
    state TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (product_id) REFERENCES product(id),
    UNIQUE KEY uk_product_date (product_id, date),
    INDEX idx_product_id (product_id),
    INDEX idx_date (date)
) COMMENT '产品每日价格日历表';

-- ==================== 订单相关表 ====================

-- 订单表
CREATE TABLE `order` (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    drp_order_id VARCHAR(100) NOT NULL UNIQUE COMMENT '外部订单号',
    order_id VARCHAR(100) COMMENT '分销系统订单号',
    hotel_id BIGINT NOT NULL COMMENT '酒店ID',
    room_type_id BIGINT NOT NULL COMMENT '房型ID',
    product_id BIGINT NOT NULL COMMENT '产品ID',
    check_in_date DATE NOT NULL COMMENT '入住日期',
    check_out_date DATE NOT NULL COMMENT '离店日期',
    count INT NOT NULL COMMENT '预定数量',
    total_amount DECIMAL(10, 2) NOT NULL COMMENT '总金额',
    pay_amount DECIMAL(10, 2) NOT NULL COMMENT '实付金额',
    discount_amount DECIMAL(10, 2) DEFAULT 0 COMMENT '折扣金额',
    guarantee_type TINYINT DEFAULT 0 COMMENT '担保类型：0-默认，1-首晚担保，2-全额担保',
    guarantee_amount DECIMAL(10, 2) DEFAULT 0 COMMENT '担保金额',
    contact_name VARCHAR(100) NOT NULL COMMENT '联系人姓名',
    contact_phone VARCHAR(20) NOT NULL COMMENT '联系人电话',
    contact_email VARCHAR(100) COMMENT '联系人邮箱',
    member_no VARCHAR(100) COMMENT '会员卡号',
    member_level TINYINT COMMENT '会员等级',
    guest_remark_info TEXT COMMENT '用户备注信息',
    guest_invoice_info TEXT COMMENT '用户开票信息',
    state TINYINT DEFAULT 0 COMMENT '订单状态：0-预定状态，1-待确认，2-待入住，3-已入住，4-已离店，5-已取消，6-noshow，7-拒单',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (hotel_id) REFERENCES hotel(id),
    FOREIGN KEY (room_type_id) REFERENCES room_type(id),
    FOREIGN KEY (product_id) REFERENCES product(id),
    INDEX idx_drp_order_id (drp_order_id),
    INDEX idx_order_id (order_id),
    INDEX idx_hotel_id (hotel_id),
    INDEX idx_check_in_date (check_in_date),
    INDEX idx_state (state)
) COMMENT '订单表';

-- 订单每日明细表
CREATE TABLE order_daily_detail (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '明细ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    date DATE NOT NULL COMMENT '日期',
    rate DECIMAL(10, 2) NOT NULL COMMENT '价格',
    meal_type TINYINT DEFAULT 0 COMMENT '餐食类型：0-未知，1-早餐，2-午餐，3-晚餐',
    meal_count INT DEFAULT 0 COMMENT '餐食份数',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (order_id) REFERENCES `order`(id),
    INDEX idx_order_id (order_id),
    INDEX idx_date (date)
) COMMENT '订单每日明细表';

-- 入住人信息表
CREATE TABLE order_guest (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '入住人ID',
    order_id BIGINT NOT NULL COMMENT '订单ID',
    guest_type TINYINT DEFAULT 0 COMMENT '入住人类型：0-成人，1-儿童',
    guest_name VARCHAR(100) NOT NULL COMMENT '入住人姓名',
    guest_first_name VARCHAR(100) COMMENT '入住人英文名',
    guest_last_name VARCHAR(100) COMMENT '入住人英文姓',
    age INT COMMENT '年龄',
    id_type TINYINT DEFAULT 0 COMMENT '证件类型：0-身份证',
    id_no VARCHAR(50) COMMENT '证件号码',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (order_id) REFERENCES `order`(id),
    INDEX idx_order_id (order_id),
    INDEX idx_guest_name (guest_name)
) COMMENT '订单入住人信息表';

-- ==================== 会员相关表 ====================

-- 会员表
CREATE TABLE member (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '会员ID',
    member_no VARCHAR(100) NOT NULL UNIQUE COMMENT '会员卡号',
    phone_no VARCHAR(20) NOT NULL COMMENT '手机号',
    name VARCHAR(100) COMMENT '姓名',
    id_type TINYINT DEFAULT 0 COMMENT '证件类型：0-身份证',
    id_no VARCHAR(50) COMMENT '证件号码',
    member_level TINYINT NOT NULL COMMENT '会员等级',
    effect_begin_date DATE COMMENT '等级有效期的开始时间',
    effect_end_date DATE COMMENT '等级有效期的结束时间',
    register_time DATETIME COMMENT '注册时间',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_member_no (member_no),
    INDEX idx_phone_no (phone_no),
    INDEX idx_name (name)
) COMMENT '会员信息表';

-- ==================== 规则相关表 ====================

-- 取消规则表
CREATE TABLE cancel_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '取消规则ID',
    cancel_rule_id BIGINT NOT NULL UNIQUE COMMENT '取消规则ID（分销平台唯一标识）',
    cancel_type TINYINT DEFAULT 0 COMMENT '取消类型：0-免费取消，1-限时取消，2-不可取消',
    cancel_time_type TINYINT DEFAULT 0 COMMENT '取消时间类型：0-时间，1-小时',
    cancel_days_ago INT DEFAULT 0 COMMENT 'N天前',
    cancel_time_ago VARCHAR(10) COMMENT 'N点前，如18:30',
    cancel_hours_ago INT DEFAULT 0 COMMENT '入住前N小时',
    deduction_type TINYINT DEFAULT 0 COMMENT '扣费的类型：0-默认，1-首晚，2-全额',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_cancel_rule_id (cancel_rule_id)
) COMMENT '取消规则表';

-- 预订规则表
CREATE TABLE booking_rule (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '预订规则ID',
    booking_rule_id BIGINT NOT NULL UNIQUE COMMENT '预订规则ID（分销平台唯一标识）',
    booking_confirm_type TINYINT DEFAULT 0 COMMENT '预定确认类型：0-默认，1-即时确认，2-待查，3-限时确认',
    min_amount INT DEFAULT 0 COMMENT '最少预定房间数量，0表示无限制',
    max_amount INT DEFAULT 0 COMMENT '最大预定房间数量，0表示无限制',
    min_days INT DEFAULT 0 COMMENT '最少入住天数，0表示无限制',
    max_days INT DEFAULT 0 COMMENT '最大入住天数，0表示无限制',
    min_adv_hours INT DEFAULT 0 COMMENT '最少提前预订时间（小时）',
    max_adv_hours INT DEFAULT 0 COMMENT '最大提前预订时间（小时）',
    status TINYINT DEFAULT 1 COMMENT '状态：0-无效，1-有效',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_booking_rule_id (booking_rule_id)
) COMMENT '预订规则表';

-- ==================== 系统配置表 ====================

-- API访问凭证表
CREATE TABLE api_access_token (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '凭证ID',
    client_id VARCHAR(100) NOT NULL COMMENT '客户端ID',
    client_secret VARCHAR(200) NOT NULL COMMENT '客户端密钥',
    access_token VARCHAR(100) COMMENT '访问令牌',
    expires_in BIGINT COMMENT '过期时间戳',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_client_id (client_id),
    INDEX idx_access_token (access_token)
) COMMENT 'API访问凭证表';

-- 系统配置表
CREATE TABLE system_config (
    id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '配置ID',
    config_key VARCHAR(100) NOT NULL UNIQUE COMMENT '配置键',
    config_value TEXT COMMENT '配置值',
    config_desc VARCHAR(200) COMMENT '配置描述',
    status TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    created_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_config_key (config_key)
) COMMENT '系统配置表';