package cn.taroco.gateway.filter.pre;

import cn.taroco.common.ribbon.XlabelHeaderInterceptor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static cn.taroco.gateway.filter.MyFilterConstants.PRE_REQUEST_XLABEL_ORDER;
import static cn.taroco.gateway.filter.MyFilterConstants.PRE_TYPE;

/**
 * xlabel标签拦截器,主要是为了初始化XlabelHeaderInterceptor.LABEL
 * 不然XlabelWeightMetadataRule在判断某个具有标签的server的时候,会报错:
 * HystrixRequestContext.initializeContext() must be called at the beginning of each request before RequestVariable functionality can be used.
 *
 * @author liuht
 * @date 2017/12/17
 */
@Component
public class XlabelRequestPreFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_REQUEST_XLABEL_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        XlabelHeaderInterceptor.initHystrixRequestContext(request.getHeader(XlabelHeaderInterceptor.HEADER_LABEL));
        return null;
    }
}
