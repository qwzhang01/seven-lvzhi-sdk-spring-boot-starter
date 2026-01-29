package io.github.qwzhang01.luzhi.sdk.exception;

/**
 * 旅智分销平台API异常
 *
 * @author lvzhi-drp-starter
 */
public class LvzhiDrpException extends RuntimeException {

    /**
     * 错误码
     */
    private String errorCode;

    public LvzhiDrpException(String message) {
        super(message);
    }

    public LvzhiDrpException(String message, Throwable cause) {
        super(message, cause);
    }

    public LvzhiDrpException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public LvzhiDrpException(String errorCode, String message,
                             Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
