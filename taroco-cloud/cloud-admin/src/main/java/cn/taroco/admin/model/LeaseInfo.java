package cn.taroco.admin.model;

import cn.taroco.common.utils.TarocoConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述
 *
 * @author liuht
 * @date 2017/11/20 17:47
 */
public class LeaseInfo implements Serializable {

    private static final long serialVersionUID = 2L;

    /**
     * 续约更新时间间隔
     */
    private int renewalIntervalInSecs ;
    /**
     * 续约到期时间
     */
    private int durationInSecs;

    /**
     *  注册时间
     */
    @JsonFormat(pattern = TarocoConstants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date registrationTime;
    /**
     * 最后续约时间
     */
    @JsonFormat(pattern = TarocoConstants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date lastRenewalTime;
    /**
     * 剔除时间
     */
    @JsonFormat(pattern = TarocoConstants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date evictionTime;
    /**
     * 服务上线时间
     */
    @JsonFormat(pattern = TarocoConstants.DATETIME_FORMAT, timezone = "GMT+8")
    private Date serviceUpTime;

    public int getRenewalIntervalInSecs() {
        return renewalIntervalInSecs;
    }

    public void setRenewalIntervalInSecs(int renewalIntervalInSecs) {
        this.renewalIntervalInSecs = renewalIntervalInSecs;
    }

    public int getDurationInSecs() {
        return durationInSecs;
    }

    public void setDurationInSecs(int durationInSecs) {
        this.durationInSecs = durationInSecs;
    }

    public Date getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(Date registrationTime) {
        this.registrationTime = registrationTime;
    }

    public Date getLastRenewalTime() {
        return lastRenewalTime;
    }

    public void setLastRenewalTime(Date lastRenewalTime) {
        this.lastRenewalTime = lastRenewalTime;
    }

    public Date getEvictionTime() {
        return evictionTime;
    }

    public void setEvictionTime(Date evictionTime) {
        this.evictionTime = evictionTime;
    }

    public Date getServiceUpTime() {
        return serviceUpTime;
    }

    public void setServiceUpTime(Date serviceUpTime) {
        this.serviceUpTime = serviceUpTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LeaseInfo leaseInfo = (LeaseInfo) o;

        return new EqualsBuilder()
                .append(renewalIntervalInSecs, leaseInfo.renewalIntervalInSecs)
                .append(durationInSecs, leaseInfo.durationInSecs)
                .append(registrationTime, leaseInfo.registrationTime)
                .append(lastRenewalTime, leaseInfo.lastRenewalTime)
                .append(evictionTime, leaseInfo.evictionTime)
                .append(serviceUpTime, leaseInfo.serviceUpTime)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(renewalIntervalInSecs)
                .append(durationInSecs)
                .append(registrationTime)
                .append(lastRenewalTime)
                .append(evictionTime)
                .append(serviceUpTime)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("renewalIntervalInSecs", renewalIntervalInSecs)
                .append("durationInSecs", durationInSecs)
                .append("registrationTime", registrationTime)
                .append("lastRenewalTime", lastRenewalTime)
                .append("evictionTime", evictionTime)
                .append("serviceUpTime", serviceUpTime)
                .toString();
    }
}
