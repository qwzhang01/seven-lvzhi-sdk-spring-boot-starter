package io.github.qwzhang01.luzhi.sdk.util;

import io.github.qwzhang01.luzhi.sdk.exception.LvzhiDrpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 旅智分销平台API签名工具类
 * <p>
 * 签名算法说明：
 * 1. 将secretKey、trackingId、method、timestamp、version五个元素拼接成字符串
 * 2. 对拼接字符串进行MD5加密并Base64编码生成bodyMd5
 * 3. 将url、换行符、post、换行符、bodyMd5拼接，使用HmacSHA256加密生成签名
 *
 * @author lvzhi-drp-starter
 */
public final class SignatureUtil {

    private static final Logger logger =
            LoggerFactory.getLogger(SignatureUtil.class);

    private static final String HMAC_SHA256 = "HmacSHA256";
    private static final String MD5 = "MD5";
    private static final String HTTP_METHOD = "post";

    private SignatureUtil() {
        // 私有构造函数，防止实例化
    }

    /**
     * 生成API请求签名
     *
     * @param url        请求URL路径
     * @param secretKey  签名密钥
     * @param trackingId 请求唯一ID
     * @param method     接口方法名
     * @param timestamp  时间戳
     * @param version    版本号
     * @return 签名字符串
     */
    public static String generateSignature(String url, String secretKey,
                                           String trackingId,
                                           String method, Long timestamp,
                                           String version) {
        try {
            // 第一步：将参数拼接成字符串并计算bodyMd5
            // 顺序：secretKey + trackingId + method + timestamp + version
            String params =
                    secretKey + trackingId + method + timestamp + version;
            logger.debug("签名参数拼接: {}", params);

            byte[] inputStreamBytes = params.getBytes(StandardCharsets.UTF_8);
            String bodyMd5 =
                    calcBodyMd5(new ByteArrayInputStream(inputStreamBytes));
            logger.debug("bodyMd5: {}", bodyMd5);

            // 第二步：构建待签名字符串
            // 格式：url + \n + post + \n + bodyMd5
            String stringToSign = buildStringToSign(url, HTTP_METHOD, bodyMd5);
            logger.debug("待签名字符串: {}", stringToSign.replace("\n", "\\n"));

            // 第三步：使用HmacSHA256计算签名
            String signature = hmacSHA256(stringToSign, secretKey);
            logger.debug("生成签名: {}", signature);

            return signature;
        } catch (Exception e) {
            logger.error("生成签名失败", e);
            throw new LvzhiDrpException("生成签名失败", e);
        }
    }

    /**
     * HmacSHA256加密
     *
     * @param data      待加密数据
     * @param secretKey 密钥
     * @return Base64编码的签名
     */
    private static String hmacSHA256(String data, String secretKey) throws Exception {
        Mac mac = Mac.getInstance(HMAC_SHA256);
        SecretKeySpec secretKeySpec =
                new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
                        HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    /**
     * 计算请求体的MD5值并Base64编码
     *
     * @param inputStream 输入流
     * @return Base64编码的MD5值
     */
    private static String calcBodyMd5(InputStream inputStream) throws Exception {
        byte[] bodyBytes = readAllBytes(inputStream);
        if (bodyBytes != null && bodyBytes.length > 0) {
            return base64AndMD5(bodyBytes).trim();
        }
        return null;
    }

    /**
     * 读取输入流的所有字节（兼容Java 8）
     *
     * @param inputStream 输入流
     * @return 字节数组
     */
    private static byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    /**
     * 构建待签名字符串
     *
     * @param url     URL路径
     * @param method  HTTP方法
     * @param bodyMd5 请求体MD5值
     * @return 待签名字符串
     */
    private static String buildStringToSign(String url, String method,
                                            String bodyMd5) {
        StringBuilder sb = new StringBuilder();
        sb.append(url).append("\n").append(method).append("\n");
        if (bodyMd5 != null && !bodyMd5.isEmpty()) {
            sb.append(bodyMd5);
        }
        return sb.toString();
    }

    /**
     * 先进行MD5摘要再进行Base64编码获取摘要字符串
     *
     * @param bytes 待计算字节数组
     * @return Base64编码的MD5值
     */
    public static String base64AndMD5(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException("bytes cannot be null");
        }
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.reset();
            md.update(bytes);
            return new String(Base64.getEncoder().encode(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("unknown algorithm MD5");
        }
    }

    /**
     * MD5加密（用于旅智平台调用分销商接口的授权认证）
     *
     * @param data 待加密数据
     * @return MD5值（小写）
     */
    public static String md5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.update(data.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new LvzhiDrpException("MD5算法不可用", e);
        }
    }
}
