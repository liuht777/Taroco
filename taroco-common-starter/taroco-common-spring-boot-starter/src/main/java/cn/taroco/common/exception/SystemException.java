package cn.taroco.common.exception;

/**
 * 系统业务异常.
 *
 * @author liuht
 */
public class SystemException extends BaseException {

    public SystemException() {
        super();
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }
}