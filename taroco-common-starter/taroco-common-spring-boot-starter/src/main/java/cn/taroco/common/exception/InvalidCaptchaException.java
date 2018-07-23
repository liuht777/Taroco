package cn.taroco.common.exception;


/**
 * 无效验证码
 *
 * @author liuht
 */
public class InvalidCaptchaException extends BusinessException {

    public InvalidCaptchaException(String message) {
        super(message);
    }

}
