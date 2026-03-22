package io.github.qwzhang01.luzhi.sdk.dto.notify;

/**
 * 酒店信息变更通知类型枚举
 *
 * @author lvzhi-drp-starter
 */
public enum HotelChangeType {
    
    /**
     * 房态房量变化
     */
    AVAIL_ROOMS("AvailRooms", "房态房量变化"),
    
    /**
     * 产品房态的每日开关变化
     */
    PRODUCT_ROOM_STATE("ProductRoomState", "产品房态的每日开关变化"),
    
    /**
     * 房价变化
     */
    RATE("Rate", "房价变化"),
    
    /**
     * 酒店下线
     */
    HOTEL_OFFLINE("HotelOffline", "酒店下线"),
    
    /**
     * 酒店上线
     */
    HOTEL_ONLINE("HotelOnline", "酒店上线"),
    
    /**
     * 房型静态信息变化
     */
    ROOM_TYPE("RoomType", "房型静态信息变化"),
    
    /**
     * 产品静态信息变化(包含产品静态信息、取消规则、预订规则、价格方案变化)
     */
    PRODUCT("Product", "产品静态信息变化"),
    
    /**
     * 酒店静态信息变化
     */
    HOTEL("Hotel", "酒店静态信息变化");
    
    private final String code;
    private final String description;
    
    HotelChangeType(String code, String description) {
        this.code = code;
        this.description = description;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getDescription() {
        return description;
    }
    
    /**
     * 根据代码获取枚举值
     *
     * @param code 变更类型代码
     * @return 对应的枚举值，如果找不到返回null
     */
    public static HotelChangeType fromCode(String code) {
        for (HotelChangeType type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }
    
    /**
     * 检查代码是否有效
     *
     * @param code 变更类型代码
     * @return 如果代码有效返回true，否则返回false
     */
    public static boolean isValid(String code) {
        return fromCode(code) != null;
    }
}