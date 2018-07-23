package cn.taroco.common.exception;

/**
 * 客户端异常.给调用者
 *
 * @author liuht
 */
public class ClientException extends BaseException {

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

}