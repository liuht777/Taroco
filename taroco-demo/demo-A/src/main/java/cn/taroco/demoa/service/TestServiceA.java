package cn.taroco.demoa.service;

import cn.taroco.common.web.Response;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 描述
 *
 * @author liuht
 * @date 2017/11/22 15:25
 */
@Service
@DefaultProperties(groupKey = "TAROCO-DEMO-A", threadPoolKey = "TAROCO-DEMO-A", defaultFallback = "fail")
public class TestServiceA {
    @HystrixCommand(commandKey = "TAROCO-DEMO-A.getDemoB", fallbackMethod = "fail",
            commandProperties = {
                    // 超时时间
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
                    // 判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    // 失败率达到多少百分比后熔断: 表示在一个统计窗口内有50%的请求处理失败，会触发熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
                    // 熔断开启多少秒后去重新尝试请求
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
            },
            threadPoolProperties = {
                    // 设置线程池的core size,这是最大的并发执行数量。默认10
                    @HystrixProperty(name = "coreSize", value = "20"),
                    // 最大队列长度。设置BlockingQueue的最大长度 默认5
                    @HystrixProperty(name = "maxQueueSize", value = "5"),
                    // 线程设置keep-live时间,这个一般用不到因为默认corePoolSize和maxPoolSize是一样的。
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    // 设置拒绝请求的临界值, 设置设个值的原因是maxQueueSize值运行时不能改变，我们可以通过修改这个变量动态修改允许排队的长度
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
                    // 设置统计滚动窗口的长度，以毫秒为单位。用于监控和熔断器。默认10000(10秒)
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    // 设置统计窗口的桶数量,metrics.rollingStats.timeInMilliseconds必须能被这个值整除
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10")

            }
    )
    public Response getDemoB() {
        Response response = restTemplate.getForObject("http://TAROCO-DEMO-B/test", Response.class);
        return response;
    }

    @Autowired
    private RestTemplate restTemplate;

    public Response fail() {
        return new Response().failure("请求失败");
    }
}
