package cn.taroco.gateway.filter.pre;

import cn.taroco.common.web.Response;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;

import static cn.taroco.gateway.filter.MyFilterConstants.PRE_OPTIONS_TEST_ORDER;
import static cn.taroco.gateway.filter.MyFilterConstants.PRE_TYPE;

/**
 * 预检请求
 *
 * @author liuht
 * @date 2018/1/17 12:51
 */
@Component
@Slf4j
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
        final HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        return RequestMethod.OPTIONS.toString().equalsIgnoreCase(request.getMethod());
    }

    @Override
    public Object run() {
        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletRequest request = ctx.getRequest();
        final HttpServletResponse response = ctx.getResponse();
        final String heade1 = "Access-Control-Request-Headers";
        final String heade2 = "Access-Control-Request-Method";
        final String heade3 = "Origin";
        if (!StringUtils.isEmpty(request.getHeader(heade1)) ||
                !StringUtils.isEmpty(request.getHeader(heade2))) {
            // 预检请求
            // 过滤该请求，不往下级服务去转发请求，到此结束
            response.setHeader("Access-Control-Allow-Headers", request.getHeader(heade1));
            response.setHeader("Access-Control-Allow-Methods", request.getHeader(heade2));
            response.setHeader("Access-Control-Allow-Origin", request.getHeader(heade3));
            ctx.setSendZuulResponse(false);
            response.setCharacterEncoding(Charset.defaultCharset().name());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpStatus.OK.value());
            try {
                response.getWriter().print(JSONObject.toJSONString(Response.success()));
            } catch (IOException e) {
                log.error("response io异常");
                e.printStackTrace();
            }
            ctx.setResponse(response);
        }
        return null;
    }
}
