package cn.taroco.oauth2.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuht
 * @date 2018/1/9
 */
@Data
@ConfigurationProperties(prefix = "ignore")
public class FilterIgnorePropertiesConfig {
    private List<String> urls = new ArrayList<>();

    private List<String> clients = new ArrayList<>();
}
