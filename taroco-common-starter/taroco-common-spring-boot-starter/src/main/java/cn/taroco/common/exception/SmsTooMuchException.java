package cn.taroco.common.exception;


/**
 * 短信发送太频繁
 *
 * @author liuht
 */
public class SmsTooMuchException extends BusinessException {

    public SmsTooMuchException(String message) {
        super(message);
    }

}
