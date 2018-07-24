package cn.taroco.oauth2.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * 通过数据库加载客户端详情
 *
 * @author liuht
 * @date 2018/7/24 16:30
 */
public class DbClientDetailsService {

    @Autowired
    private DataSource dataSource;

    @Bean
    public ClientDetailsService clientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

}
