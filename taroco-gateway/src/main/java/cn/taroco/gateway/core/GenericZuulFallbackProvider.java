package cn.taroco.gateway.core;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 公共的熔断类,在spring boot application里面根据不同的route ID,实现对应的熔断器
 *
 * @author liuht
 * @date 2017/11/19 16:25
 */
public class GenericZuulFallbackProvider implements ZuulFallbackProvider {

    private String responseBody = "{\"message\":\"Service Unavailable. Please try again later.\"}";
    private HttpHeaders headers = null;
    private String route = null;
    private int rawStatusCode = HttpStatus.SERVICE_UNAVAILABLE.value();
    private HttpStatus httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
    private String statusText = HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();

    @Override
    public String getRoute() {
        if (route == null) {
            route = "unknown";
        }
        return route;
    }

    /**
     * 熔断器会调用fallbackResponse方法,最终模拟返回一个ClientHttpResponse.
     * 里面包含HttpHeaders、rawStatusCode、statusCode和responseBody等信息,这些信息都可以自定义返回值.
     * MediaType则包含多种返回信息的格式Json、Pdf、Image等等.
     *
     * @see org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider#fallbackResponse()
     */
    @Override
    public ClientHttpResponse fallbackResponse() {

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return httpStatus;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return rawStatusCode;
            }

            @Override
            public String getStatusText() throws IOException {
                return statusText;
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(responseBody.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                if (headers == null) {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                }
                return headers;
            }
        };
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getRawStatusCode() {
        return rawStatusCode;
    }

    public void setRawStatusCode(int rawStatusCode) {
        this.rawStatusCode = rawStatusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
