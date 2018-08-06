package cn.taroco.gateway.filter;

import cn.taroco.gateway.filter.pre.*;
import com.netflix.zuul.ZuulFilter;

/**
 *
 * @author liuht
 * @date 2017/12/17
 */
public interface MyFilterConstants {

    // ORDER constants -----------------------------------

    /**
     * Filter Order for {@link XlabelRequestPreFilter#filterOrder()}
     */
    int PRE_REQUEST_XLABEL_ORDER = 7;

    /**
     * Filter Order for {@link CorsFilter#filterOrder()}
     */
    int PRE_CORS_LOG_ORDER = 9999;

    /**
     * Filter Order for {@link PreRequestLogFilter#filterOrder()}
     */
    int PRE_REQUEST_LOG_ORDER = 10000;

    /**
     * Filter Order for {@link OptionsTestFilter#filterOrder()}
     */
    int PRE_OPTIONS_TEST_ORDER = 10001;

    /**
     * Filter Order for {@link WebSocketFilter#filterOrder()}
     */
    int PRE_WEBSOCKET_ORDER = 10002;

    /**
     * Filter Order for {@link PreRequestLogFilter#filterOrder()}
     */
    int POST_REQUEST_XLABEL_ORDER = 0;

    // Zuul Filter TYPE constants -----------------------------------

    /**
     * {@link ZuulFilter#filterType()} error type.
     */
    String ERROR_TYPE = "error";

    /**
     * {@link ZuulFilter#filterType()} post type.
     */
    String POST_TYPE = "post";

    /**
     * {@link ZuulFilter#filterType()} pre type.
     */
    String PRE_TYPE = "pre";

    /**
     * {@link ZuulFilter#filterType()} route type.
     */
    String ROUTE_TYPE = "route";

}
