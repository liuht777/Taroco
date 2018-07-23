package cn.taroco.gateway.filter.post;

import cn.taroco.common.ribbon.XlabelHeaderInterceptor;
import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

import static cn.taroco.gateway.filter.MyFilterConstants.POST_REQUEST_XLABEL_ORDER;
import static cn.taroco.gateway.filter.MyFilterConstants.POST_TYPE;

/**
 * xlabel标签拦截器,主要是为了XlabelHeaderInterceptor.shutdownHystrixRequestContext
 *
 * @author liuht
 * @date 2017/12/17
 */
@Component
public class XlabelRequestPostFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return POST_REQUEST_XLABEL_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        XlabelHeaderInterceptor.shutdownHystrixRequestContext();
        return null;
    }
}
