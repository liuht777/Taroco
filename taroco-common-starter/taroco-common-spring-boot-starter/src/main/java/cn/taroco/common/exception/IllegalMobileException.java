package cn.taroco.common.exception;



/**
 * 手机号码不合法
 *
 * @author liuht
 */
public class IllegalMobileException extends BusinessException {
    private static final String MESSAGE = "手机号码不合法";

    public IllegalMobileException() {
        super(MESSAGE);
    }

    public IllegalMobileException(String message) {
        super(message);
    }
}
