package cn.taroco.demoa.exception;

import cn.taroco.common.exception.IError;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/23 14:15.
 */
public enum DemoAError implements IError {
    USER_IS_EXIST("0001","user is exist"),
    ;

    String errorCode;
    String errorMessage;
    private static final String ns = "DEMO_A";

    DemoAError(String errorCode, String errorMessage) {
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
