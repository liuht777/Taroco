package cn.taroco.demob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 描述
 *
 * @author liuht
 * @date 2017/11/22 15:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TarocoDemoBApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TarocoDemoBApplication.class, args);
    }
}
