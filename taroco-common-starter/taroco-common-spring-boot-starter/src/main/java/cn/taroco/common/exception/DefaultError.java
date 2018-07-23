package cn.taroco.common.exception;



/**
 * 说明：默认异常
 *
 * @author zhangwei
 * @date 2017年11月18日23:45:26
 */
public enum DefaultError implements IError {
    /**
     * 系统内部错误
     */
    SYSTEM_INTERNAL_ERROR("0000", "System Internal Error"),
    /**
     * 无效参数
     */
    INVALID_PARAMETER("0001", "Invalid Parameter"),
    /**
     * 服务不存在
     */
    SERVICE_NOT_FOUND("0002", "Service Not Found"),
    /**
     * 参数不全
     */
    PARAMETER_REQUIRED("0003", "Parameter required"),
    /**
     * 参数过长
     */
    PARAMETER_MAX_LENGTH("0004", "Parameter max length limit"),
    /**
     * 参数过短
     */
    PARAMETER_MIN_LENGTH("0005", "Parameter min length limit"),
    /**
     * 参数出错
     */
    PARAMETER_ANNOTATION_NOT_MATCH("0006", "Parameter annotation not match"),
    /**
     * 参数验证失败
     */
    PARAMETER_NOT_MATCH_RULE("0007", "Parameter not match validation rule"),
    /**
     * 请求方法出错
     */
    METHOD_NOT_SUPPORTED("0008", "method not supported"),
    /**
     * 不支持的content类型
     */
    CONTENT_TYPE_NOT_SUPPORT("0009", "content type is not support"),
    /**
     * json格式化出错
     */
    JSON_FORMAT_ERROR("0010", "json format error"),
    /**
     * 远程调用出错
     */
    CALL_REMOTE_ERROR("0011", "call remote error"),
    /**
     * 服务运行SQLException异常
     */
    SQL_EXCEPTION("0012", "sql exception"),
    /**
     * 客户端异常 给调用者 app,移动端调用
     */
    CLIENT_EXCEPTION("0013", "client exception"),
    /**
     * 服务端异常, 微服务服务端产生的异常
     */
    SERVER_EXCEPTION("0014", "server exception")
    ;

    String errorCode;
    String errorMessage;
    private static final String ns = "SYS";

    DefaultError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getNameSpace() {
        return ns;
    }

    @Override
    public String getErrorCode() {
        return ns + "." + this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }
}
