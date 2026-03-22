package io.github.qwzhang01.luzhi.sdk.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 本地日期时间工具类
 * <p>
 * 提供字符串与LocalDateTime之间的转换功能
 * </p>
 * 
 * @author qwzhang01
 * @since 1.0.0
 */
public class LocalDateTimeUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(LocalDateTimeUtil.class);
    
    /**
     * 默认日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * ISO 8601格式：yyyy-MM-dd'T'HH:mm:ss
     */
    public static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
    
    /**
     * 紧凑格式：yyyyMMddHHmmss
     */
    public static final String COMPACT_PATTERN = "yyyyMMddHHmmss";
    
    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    
    /**
     * 时间格式：HH:mm:ss
     */
    public static final String TIME_PATTERN = "HH:mm:ss";
    
    private LocalDateTimeUtil() {
        // 工具类，禁止实例化
    }
    
    /**
     * 将字符串按照指定格式转换为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @param pattern     格式模式
     * @return LocalDateTime对象，如果解析失败返回null
     */
    public static LocalDateTime parse(String dateTimeStr, String pattern) {
        if (dateTimeStr == null || pattern == null) {
            logger.warn("日期时间字符串或格式模式为空，dateTimeStr: {}, pattern: {}", dateTimeStr, pattern);
            return null;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            logger.warn("日期时间字符串解析失败，dateTimeStr: {}, pattern: {}, error: {}", 
                dateTimeStr, pattern, e.getMessage());
            return null;
        }
    }
    
    /**
     * 将字符串按照默认格式(yyyy-MM-dd HH:mm:ss)转换为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime对象，如果解析失败返回null
     */
    public static LocalDateTime parse(String dateTimeStr) {
        return parse(dateTimeStr, DEFAULT_PATTERN);
    }
    
    /**
     * 将字符串按照ISO格式(yyyy-MM-dd'T'HH:mm:ss)转换为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime对象，如果解析失败返回null
     */
    public static LocalDateTime parseISO(String dateTimeStr) {
        return parse(dateTimeStr, ISO_PATTERN);
    }
    
    /**
     * 将字符串按照紧凑格式(yyyyMMddHHmmss)转换为LocalDateTime
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime对象，如果解析失败返回null
     */
    public static LocalDateTime parseCompact(String dateTimeStr) {
        return parse(dateTimeStr, COMPACT_PATTERN);
    }
    
    /**
     * 尝试使用多种常见格式解析日期时间字符串
     * <p>
     * 按顺序尝试以下格式：
     * 1. yyyy-MM-dd HH:mm:ss
     * 2. yyyy-MM-dd'T'HH:mm:ss
     * 3. yyyyMMddHHmmss
     * 4. yyyy-MM-dd（自动补充时间部分为00:00:00）
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime对象，如果所有格式都解析失败返回null
     */
    public static LocalDateTime parseFlexible(String dateTimeStr) {
        if (dateTimeStr == null) {
            logger.warn("日期时间字符串为空，无法进行灵活解析");
            return null;
        }
        
        // 尝试默认格式
        LocalDateTime result = parse(dateTimeStr, DEFAULT_PATTERN);
        if (result != null) {
            logger.debug("使用默认格式解析成功，dateTimeStr: {}, result: {}", dateTimeStr, result);
            return result;
        }
        
        // 尝试ISO格式
        result = parse(dateTimeStr, ISO_PATTERN);
        if (result != null) {
            logger.debug("使用ISO格式解析成功，dateTimeStr: {}, result: {}", dateTimeStr, result);
            return result;
        }
        
        // 尝试紧凑格式
        result = parse(dateTimeStr, COMPACT_PATTERN);
        if (result != null) {
            logger.debug("使用紧凑格式解析成功，dateTimeStr: {}, result: {}", dateTimeStr, result);
            return result;
        }
        
        // 尝试日期格式（自动补充时间部分）
        result = parseDateWithTime(dateTimeStr);
        if (result != null) {
            logger.debug("使用日期格式解析成功，dateTimeStr: {}, result: {}", dateTimeStr, result);
        } else {
            logger.warn("所有格式解析都失败，dateTimeStr: {}", dateTimeStr);
        }
        
        return result;
    }
    
    /**
     * 将日期字符串转换为LocalDateTime，自动补充时间部分为00:00:00
     *
     * @param dateStr 日期字符串（格式：yyyy-MM-dd）
     * @return LocalDateTime对象，如果解析失败返回null
     */
    public static LocalDateTime parseDateWithTime(String dateStr) {
        if (dateStr == null) {
            logger.warn("日期字符串为空，无法解析");
            return null;
        }
        
        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            LocalDateTime result = java.time.LocalDate.parse(dateStr, dateFormatter).atStartOfDay();
            logger.debug("日期字符串解析成功，dateStr: {}, result: {}", dateStr, result);
            return result;
        } catch (DateTimeParseException e) {
            logger.warn("日期字符串解析失败，dateStr: {}, error: {}", dateStr, e.getMessage());
            return null;
        }
    }
    
    /**
     * 将LocalDateTime格式化为指定格式的字符串
     *
     * @param dateTime LocalDateTime对象
     * @param pattern   格式模式
     * @return 格式化后的字符串，如果输入为null返回null
     */
    public static String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null || pattern == null) {
            logger.warn("LocalDateTime对象或格式模式为空，dateTime: {}, pattern: {}", dateTime, pattern);
            return null;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            String result = dateTime.format(formatter);
            logger.debug("LocalDateTime格式化成功，dateTime: {}, pattern: {}, result: {}", dateTime, pattern, result);
            return result;
        } catch (Exception e) {
            logger.warn("LocalDateTime格式化失败，dateTime: {}, pattern: {}, error: {}", 
                dateTime, pattern, e.getMessage());
            return null;
        }
    }
    
    /**
     * 将LocalDateTime格式化为默认格式(yyyy-MM-dd HH:mm:ss)的字符串
     *
     * @param dateTime LocalDateTime对象
     * @return 格式化后的字符串，如果输入为null返回null
     */
    public static String format(LocalDateTime dateTime) {
        return format(dateTime, DEFAULT_PATTERN);
    }
    
    /**
     * 将LocalDateTime格式化为ISO格式(yyyy-MM-dd'T'HH:mm:ss)的字符串
     *
     * @param dateTime LocalDateTime对象
     * @return 格式化后的字符串，如果输入为null返回null
     */
    public static String formatISO(LocalDateTime dateTime) {
        return format(dateTime, ISO_PATTERN);
    }
    
    /**
     * 将LocalDateTime格式化为紧凑格式(yyyyMMddHHmmss)的字符串
     *
     * @param dateTime LocalDateTime对象
     * @return 格式化后的字符串，如果输入为null返回null
     */
    public static String formatCompact(LocalDateTime dateTime) {
        return format(dateTime, COMPACT_PATTERN);
    }
}
