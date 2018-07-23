package cn.taroco.common.exception;


/**
 * 用户已存在
 *
 * @author liuht
 */
public class UserExistException extends BusinessException {

    public UserExistException(String message) {
        super(message);
    }

}
