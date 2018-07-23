package cn.taroco.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;

/**
 * 服务治理配置
 *
 * @author liuht
 * @date 2017/11/26 20:49
 */
@ConfigurationProperties("taroco.admin")
public class TarocoAdminServerProperties {
    /**
     * The context-path prefixes the path where the Admin Servers statics assets and api should be
     * served. Relative to the Dispatcher-Servlet.
     */
    private String contextPath = "";

    private Boolean enabled;

    private MonitorProperties monitor = new MonitorProperties();

    private Task task = new Task();

    private RoutesProperties routes = new RoutesProperties();

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public MonitorProperties getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorProperties monitor) {
        this.monitor = monitor;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public RoutesProperties getRoutes() {
        return routes;
    }

    public void setRoutes(RoutesProperties routes) {
        this.routes = routes;
    }

    public static class MonitorProperties {
        /**
         * Time interval in ms to update the status of applications with expired statusInfo
         */
        private long statusRefreshIntervalInMills = 30000L;

        /**
         * Connect timeout when querying the applications' status and info.
         */
        private int connectTimeout = 2_000;

        /**
         * read timeout when querying the applications' status and info.
         */
        private int readTimeout = 5_000;

        public long getStatusRefreshIntervalInMills() {
            return statusRefreshIntervalInMills;
        }

        public void setStatusRefreshIntervalInMills(long statusRefreshIntervalInMills) {
            this.statusRefreshIntervalInMills = statusRefreshIntervalInMills;
        }

        public int getConnectTimeout() {
            return connectTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.connectTimeout = connectTimeout;
        }

        public int getReadTimeout() {
            return readTimeout;
        }

        public void setReadTimeout(int readTimeout) {
            this.readTimeout = readTimeout;
        }
    }

    public static class Task {
        private int poolSize = 10;

        public int getPoolSize() {
            return poolSize;
        }

        public void setPoolSize(int poolSize) {
            this.poolSize = poolSize;
        }
    }

    public static class RoutesProperties {
        /**
         * Endpoints to be proxified by spring boot admin.
         */
        private String[] endpoints = {"env", "metrics", "trace", "dump", "jolokia", "info", "logfile", "refresh", "flyway", "liquibase", "heapdump", "loggers", "auditevents", "mappings"};

        public String[] getEndpoints() {
            return endpoints;
        }

        public void setEndpoints(String[] endpoints) {
            this.endpoints = Arrays.copyOf(endpoints, endpoints.length);
        }
    }
}
