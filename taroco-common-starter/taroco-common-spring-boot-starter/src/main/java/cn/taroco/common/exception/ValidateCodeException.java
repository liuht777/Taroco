package cn.taroco.common.exception;

/**
 * 验证码异常
 *
 * @author liuht
 * @date 2017年12月21日20:45:38
 */
public class ValidateCodeException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
