package cn.taroco.common.ribbon;

import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 扩展ribbon 负责均衡策略(LoadBalancerRule)
 * 根据服务权重做负载均衡
 *
 * @author liuht
 * @date 2017/11/17 10:09
 */
public class XlabelWeightMetadataRule extends ZoneAvoidanceRule {
    public static final String META_DATA_KEY_LABEL_AND = "labelAnd";
    public static final String META_DATA_KEY_LABEL_OR = "labelOr";
    public static final String META_DATA_KEY_WEIGHT = "weight";

    private Random random = new Random();

    /**
     * 重写choose 方法
     *
     * @param key server id
     * @return Server
     */
    @Override
    public Server choose(Object key) {
        // 获取server list
        List<Server> serverList = this.getPredicate().getEligibleServers(this.getLoadBalancer().getAllServers(), key);
        if (CollectionUtils.isEmpty(serverList)) {
            return null;
        }
        // 计算总值并剔除0权重节点
        int totalWeight = 0;
        Map<Server, Integer> serverWeightMap = new HashMap<>(serverList.size());
        for (Server server : serverList) {
            // 获取server Meta信息
            Map<String, String> metadata = ((DiscoveryEnabledServer) server).getInstanceInfo().getMetadata();

            // 优先匹配 x-label标签信息,为header中带有x-label标签的请求优先选择对应的server
            // labelOr 或
            String labelOr = metadata.get(META_DATA_KEY_LABEL_OR);
            if(!StringUtils.isEmpty(labelOr)){
                List<String> metadataLabel = Arrays.asList(labelOr.split(XlabelHeaderInterceptor.HEADER_LABEL_SPLIT));
                for (String label : metadataLabel) {
                    if(XlabelHeaderInterceptor.LABEL.get().contains(label)){
                        return server;
                    }
                }
            }
            // labelAnd 且
            String labelAnd = metadata.get(META_DATA_KEY_LABEL_AND);
            if(!StringUtils.isEmpty(labelAnd)){
                List<String> metadataLabel = Arrays.asList(labelAnd.split(XlabelHeaderInterceptor.HEADER_LABEL_SPLIT));
                if(XlabelHeaderInterceptor.LABEL.get().containsAll(metadataLabel)){
                    return server;
                }
            }
            // 根据权重做选择
            String strWeight = metadata.get(META_DATA_KEY_WEIGHT);
            // 默认 100
            int weight = 100;
            try {
                weight = Integer.parseInt(strWeight);
            } catch (Exception e) {
                // 无需处理
            }
            // 剔除0权重节点
            if (weight <= 0) {
                continue;
            }

            serverWeightMap.put(server, weight);
            totalWeight += weight;
        }

        // 权重随机
        int randomWight = this.random.nextInt(totalWeight);
        int current = 0;
        for (Map.Entry<Server, Integer> entry : serverWeightMap.entrySet()) {
            current += entry.getValue();
            if (randomWight <= current) {
                return entry.getKey();
            }
        }

        return null;
    }
}
