package cn.taroco.admin.model;

import cn.taroco.common.utils.TarocoConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * 描述
 *
 * @author liuht
 * @date 2017/11/20 17:26
 */
public class Instance implements Serializable {

    private static final long serialVersionUID = 2L;

    private String instanceId;

    private String hostName;

    private String appName;

    private String ipAddr;

    private String status;

    private String overriddenstatus;

    private int port;

    private int securePort;

    private Map<String, String> metadata = Collections.emptyMap();

    private String homePageUrl;

    private String statusPageUrl;

    private String healthCheckUrl;

    private String vipAddress;

    private String secureVipAddress;

    private boolean isCoordinatingDiscoveryServer;

    @JsonFormat(pattern = TarocoConstants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date lastUpdatedTime;

    @JsonFormat(pattern = TarocoConstants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date lastDirtyTime;

    private String actionType;

    private LeaseInfo leaseInfo;

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOverriddenstatus() {
        return overriddenstatus;
    }

    public void setOverriddenstatus(String overriddenstatus) {
        this.overriddenstatus = overriddenstatus;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSecurePort() {
        return securePort;
    }

    public void setSecurePort(int securePort) {
        this.securePort = securePort;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public String getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public String getStatusPageUrl() {
        return statusPageUrl;
    }

    public void setStatusPageUrl(String statusPageUrl) {
        this.statusPageUrl = statusPageUrl;
    }

    public String getHealthCheckUrl() {
        return healthCheckUrl;
    }

    public void setHealthCheckUrl(String healthCheckUrl) {
        this.healthCheckUrl = healthCheckUrl;
    }

    public String getVipAddress() {
        return vipAddress;
    }

    public void setVipAddress(String vipAddress) {
        this.vipAddress = vipAddress;
    }

    public String getSecureVipAddress() {
        return secureVipAddress;
    }

    public void setSecureVipAddress(String secureVipAddress) {
        this.secureVipAddress = secureVipAddress;
    }

    public boolean isCoordinatingDiscoveryServer() {
        return isCoordinatingDiscoveryServer;
    }

    public void setCoordinatingDiscoveryServer(boolean coordinatingDiscoveryServer) {
        isCoordinatingDiscoveryServer = coordinatingDiscoveryServer;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public Date getLastDirtyTime() {
        return lastDirtyTime;
    }

    public void setLastDirtyTime(Date lastDirtyTime) {
        this.lastDirtyTime = lastDirtyTime;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public LeaseInfo getLeaseInfo() {
        return leaseInfo;
    }

    public void setLeaseInfo(LeaseInfo leaseInfo) {
        this.leaseInfo = leaseInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Instance instance = (Instance) o;

        return new EqualsBuilder()
                .append(port, instance.port)
                .append(securePort, instance.securePort)
                .append(isCoordinatingDiscoveryServer, instance.isCoordinatingDiscoveryServer)
                .append(instanceId, instance.instanceId)
                .append(hostName, instance.hostName)
                .append(appName, instance.appName)
                .append(ipAddr, instance.ipAddr)
                .append(status, instance.status)
                .append(overriddenstatus, instance.overriddenstatus)
                .append(metadata, instance.metadata)
                .append(homePageUrl, instance.homePageUrl)
                .append(statusPageUrl, instance.statusPageUrl)
                .append(healthCheckUrl, instance.healthCheckUrl)
                .append(vipAddress, instance.vipAddress)
                .append(secureVipAddress, instance.secureVipAddress)
                .append(lastUpdatedTime, instance.lastUpdatedTime)
                .append(lastDirtyTime, instance.lastDirtyTime)
                .append(actionType, instance.actionType)
                .append(leaseInfo, instance.leaseInfo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(instanceId)
                .append(hostName)
                .append(appName)
                .append(ipAddr)
                .append(status)
                .append(overriddenstatus)
                .append(port)
                .append(securePort)
                .append(metadata)
                .append(homePageUrl)
                .append(statusPageUrl)
                .append(healthCheckUrl)
                .append(vipAddress)
                .append(secureVipAddress)
                .append(isCoordinatingDiscoveryServer)
                .append(lastUpdatedTime)
                .append(lastDirtyTime)
                .append(actionType)
                .append(leaseInfo)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("instanceId", instanceId)
                .append("hostName", hostName)
                .append("appName", appName)
                .append("ipAddr", ipAddr)
                .append("status", status)
                .append("overriddenstatus", overriddenstatus)
                .append("port", port)
                .append("securePort", securePort)
                .append("metadata", metadata)
                .append("homePageUrl", homePageUrl)
                .append("statusPageUrl", statusPageUrl)
                .append("healthCheckUrl", healthCheckUrl)
                .append("vipAddress", vipAddress)
                .append("secureVipAddress", secureVipAddress)
                .append("isCoordinatingDiscoveryServer", isCoordinatingDiscoveryServer)
                .append("lastUpdatedTime", lastUpdatedTime)
                .append("lastDirtyTime", lastDirtyTime)
                .append("actionType", actionType)
                .append("leaseInfo", leaseInfo)
                .toString();
    }
}
