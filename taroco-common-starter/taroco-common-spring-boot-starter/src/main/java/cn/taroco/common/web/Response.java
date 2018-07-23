package cn.taroco.common.web;


import cn.taroco.common.exception.IError;

import java.io.Serializable;

/**
 * 说明：
 *
 * @author zhangwei
 * @date 2017年11月18日23:45:05
 */
public class Response implements Serializable {
    private static final long serialVersionUID = -5359531292427290394L;

    private String errorCode;
    private String errorMessage;
    private String extMessage;
    private Object result;
    private Response.Status status;

    public Response.Status getStatus() {
        return this.status;
    }

    public void setStatus(Response.Status status) {
        this.status = status;
    }

    public Response() {
        this.status = Response.Status.SUCCEED;
    }

    public Response(IError error) {
        this.errorCode = error.getErrorCode();
        this.errorMessage = error.getErrorMessage();
        this.status = Response.Status.FAILED;
    }

    public static Response success() {
        return new Response();
    }

    public static Response success(Object result) {
        Response response = new Response();
        response.setResult(result);
        return response;
    }

    public static Response failure(IError error) {
        Response response = new Response();
        response.errorCode = error.getErrorCode();
        response.errorMessage = error.getErrorMessage();
        response.status = Status.FAILED;
        return response;
    }

    public static Response failure(String message) {
        Response response = new Response();
        response.setErrorMessage(message);
        response.status = Status.FAILED;
        return response;
    }

    public static Response warring(Object result) {
        Response response = new Response();
        response.setResult(result);
        response.status = Status.WARRING;
        return response;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExtMessage() {
        return this.extMessage;
    }

    public void setExtMessage(String extMessage) {
        this.extMessage = extMessage;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.errorCode != null) {
            sb.append("ErrorCode : ").append(this.errorCode).append("ErrorMessage : ").append(this.errorMessage).append("ExtMessage : " + this.extMessage);
        } else {
            sb.append("Succeed");
        }

        return sb.toString();
    }

    public static enum Status {
        /**
         * 状态
         */
        SUCCEED,
        WARRING,
        FAILED;

        Status() {
        }
    }
}
