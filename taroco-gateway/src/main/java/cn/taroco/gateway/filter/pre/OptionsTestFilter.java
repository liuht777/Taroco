package cn.taroco.gateway.filter.pre;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.taroco.gateway.filter.MyFilterConstants.PRE_OPTIONS_TEST_ORDER;
import static cn.taroco.gateway.filter.MyFilterConstants.PRE_TYPE;

/**
 * 预检请求
 *
 * @author liuht
 * @date 2018/1/17 12:51
 */
@Component
public class OptionsTestFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return PRE_OPTIONS_TEST_ORDER;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String optionsMethod = "options";
        String heade1 = "Access-Control-Request-Headers";
        String heade2 = "Access-Control-Request-Method";
        String heade3 = "Origin";
        if (request.getMethod().equalsIgnoreCase(optionsMethod)) {
            if (!StringUtils.isEmpty(request.getHeader(heade1)) ||
                    !StringUtils.isEmpty(request.getHeader(heade2))) {
                // 预检请求
                // 过滤该请求，不往下级服务去转发请求，到此结束
                response.setHeader("Access-Control-Allow-Headers", request.getHeader(heade1));
                response.setHeader("Access-Control-Allow-Methods", request.getHeader(heade2));
                response.setHeader("Access-Control-Allow-Origin", request.getHeader(heade3));
                ctx.setSendZuulResponse(false);
                ctx.setResponseStatusCode(HttpStatus.OK.value());
                return null;
            }
        }
        return null;
    }
}
