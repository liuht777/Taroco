package cn.taroco.gateway.filter.pre;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.Map;

import static cn.taroco.gateway.filter.MyFilterConstants.PRE_REQUEST_LIMIT_ORDER;
import static cn.taroco.gateway.filter.MyFilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

/**
 * 服务级别的限流 filter
 *
 * @author liuht
 * @date 2017/12/24 17:58
 */
@Component
public class PreRequestRateLimitFilter extends ZuulFilter{

    private Map<String, RateLimiter> limitMap = Maps.newConcurrentMap();

    /**
     * 默认每秒钟可获得多少许可证
     */
    private static final double PERMITS_PER_SECOND = 1000.0;

    @Value("${zuul.rate-limit.permits-per-second}")
    private double permitsPerSecond;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // order一定要大于org.springframework.cloud.netflix.zuul.filters.pre.PreDecorationFilter的order
        // 也就是要大于5
        // 否则，RequestContext.getCurrentContext()里拿不到serviceId等数据。
        return PRE_REQUEST_LIMIT_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        if (permitsPerSecond == 0) {
            permitsPerSecond = PERMITS_PER_SECOND;
        }
        return true;
    }

    @Override
    public Object run() {
        try {
            RequestContext context = RequestContext.getCurrentContext();
            HttpServletResponse response = context.getResponse();
            String key = null;
            // 对于service格式的路由，走RibbonRoutingFilter
            String serviceId = (String) context.get(SERVICE_ID_KEY);
            if (serviceId != null) {
                key = serviceId;
                if (!limitMap.containsKey(serviceId)) {
                    limitMap.putIfAbsent(serviceId, RateLimiter.create(permitsPerSecond));
                }
            }
            // 如果压根不走RibbonRoutingFilter，则认为是URL格式的路由
            else {
                // 对于URL格式的路由，走SimpleHostRoutingFilter
                URL routeHost = context.getRouteHost();
                if (routeHost != null) {
                    String url = routeHost.toString();
                    key = url;
                    if (!limitMap.containsKey(url)) {
                        limitMap.putIfAbsent(url, RateLimiter.create(permitsPerSecond));
                    }
                }
            }
            RateLimiter rateLimiter = limitMap.get(key);
            if (!rateLimiter.tryAcquire()) {
                HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
                response.setContentType(MediaType.TEXT_PLAIN_VALUE);
                response.setStatus(httpStatus.value());
                response.getWriter().append(httpStatus.getReasonPhrase());
                context.setSendZuulResponse(false);
                throw new ZuulException(
                        httpStatus.getReasonPhrase(),
                        httpStatus.value(),
                        httpStatus.getReasonPhrase()
                );
            }
        } catch (Exception e) {
            ReflectionUtils.rethrowRuntimeException(e);
        }
        return null;
    }
}
