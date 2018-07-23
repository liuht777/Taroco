package cn.taroco.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * HttpServletRequest帮助类
 *
 * @author liuht
 * @date 2017/12/22 9:08
 */
@SuppressWarnings("all")
public class RequestUtils {

    /**
     * 获取访问者IP
     * <p>
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * <p>
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request.getRemoteAddr()。
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    public static String getQueryString(HttpServletRequest request, String... exclude) {
        StringBuffer sb = new StringBuffer();
        List<String> excludeList = Arrays.asList(exclude);
        Enumeration<String> names = request.getParameterNames();
        String name;
        String value;
        while (names.hasMoreElements()) {
            name = names.nextElement();
            value = StringUtils.join(request.getParameterValues(name));
            if (!excludeList.contains(name)) {
                sb.append("&").append(name).append("=")
                        .append(value);
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }
}
