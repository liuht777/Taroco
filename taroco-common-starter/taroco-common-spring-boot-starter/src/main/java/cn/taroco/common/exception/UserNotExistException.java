package cn.taroco.common.exception;


/**
 * 用户未存在
 *
 * @author liuht
 */
public class UserNotExistException extends BusinessException {

    public UserNotExistException(String message) {
        super(message);
    }

}
