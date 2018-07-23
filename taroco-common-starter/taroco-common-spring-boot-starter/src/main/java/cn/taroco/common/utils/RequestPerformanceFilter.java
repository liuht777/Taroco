package cn.taroco.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * request请求性能过滤器
 * 其中<code>threshold</code>参数表明超时阈值，如果处理的总时间超过该值，则filter会以warning的方式记录该次操作。
 *
 * @author liuht
 * @date 2017/12/22 9:08
 */
public class RequestPerformanceFilter extends OncePerRequestFilter implements Filter {
    private int threshold = 3000;
    private boolean includeQueryString = true;
    private static final Logger log = LoggerFactory.getLogger(RequestPerformanceFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletResponseWrapper httpResponse = new HttpServletResponseWrapper(response);
        Throwable failed = null;
        long start = System.currentTimeMillis();
        String uri = request.getRequestURL().toString();
        try {
            filterChain.doFilter(request, httpResponse);
        } catch (Throwable e) {
            failed = e;
            log.error("request failed...", e);
        } finally {
            String requestString = dumpRequest(request);
            long duration = System.currentTimeMillis() - start;
            if (failed != null) {
                log.error("[" + requestString + ",F," + duration + "ms," + httpResponse.getStatus() + "]");
            } else if (duration > threshold) {
                log.warn("[" + requestString + ",Y," + duration + "ms," + httpResponse.getStatus() + "]");
            } else {
                log.info("[" + requestString + ",Y," + duration + "ms," + httpResponse.getStatus() + "]");
            }
        }
    }

    /**
     * 取得request的内容(HTTP方法, URI)
     *
     * @param request HTTP请求
     * @return 字符串
     */
    private String dumpRequest(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        StringBuilder buffer = new StringBuilder(RequestUtils.getIpAddr(req));
        buffer.append(" ").append(req.getMethod()).append("__").append(req.getRequestURI());
        if (includeQueryString) {
            String queryString = RequestUtils.getQueryString(req);
            if (!StringUtils.isEmpty(queryString)) {
                buffer.append("?").append(queryString);
            }
        }
        return buffer.toString();
    }

    @Override
    protected void initFilterBean() throws ServletException {
        this.threshold = Integer.valueOf(getFilterParameter(getFilterConfig(), "threshold", "3000"));
        this.includeQueryString = Boolean.valueOf(getFilterParameter(getFilterConfig(), "includeQueryString", "true"));
        log.info("RequestPerformanceFilter started with threshold:" + threshold + "ms includeQueryString:" + includeQueryString);
    }

    @Override
    public void destroy() {
        log.info("destroy RequestPerformanceFilter...");
    }

    private String getFilterParameter(FilterConfig config, String key, String defaultValue) {
        String v = config.getInitParameter(key);
        return StringUtils.isEmpty(v) ? defaultValue : v;
    }
}
