package cn.taroco.gateway.config;

import com.xiaoleilu.hutool.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 跨域过滤器扩展
 *
 * @author liuht
 * @date 2018/8/14 10:55
 */
@Slf4j
public class CorsFilterExtend implements Filter {

    private String[] headers = {"Access-Control-Allow-Credentials", "Access-Control-Allow-Origin",
            "Access-Control-Allow-Methods", "Access-Control-Allow-Headers"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        final boolean debug = log.isDebugEnabled();
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        for (String header : headers) {
            Collection<String> vals = response.getHeaders(header);
            if (CollectionUtil.isNotEmpty(vals) && vals.size() > 1) {
                if (debug) {
                    log.debug("发现多个相同的 Response Header: {}", header);
                }
                log.info("发现多个相同的 Response Header: {}", header);
                final String val = vals.iterator().next();
                response.setHeader(header, val);
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
