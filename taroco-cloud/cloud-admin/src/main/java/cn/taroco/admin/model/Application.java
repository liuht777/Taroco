package cn.taroco.admin.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * 服务实体
 *
 * @author liuht
 * @date 2017/11/20 9:41
 */
public class Application implements Serializable {

    private static final long serialVersionUID = 2L;

    private String name;

    private String host;

    private int port;

    private boolean isSecure;

    private URI uri;

    private Map<String, String> metadata = Collections.emptyMap();

    private Instance instance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isSecure() {
        return isSecure;
    }

    public void setSecure(boolean secure) {
        isSecure = secure;
    }

    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }

    public Instance getInstance() {
        return instance;
    }

    public void setInstance(Instance instance) {
        this.instance = instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Application that = (Application) o;

        return new EqualsBuilder()
                .append(port, that.port)
                .append(isSecure, that.isSecure)
                .append(name, that.name)
                .append(host, that.host)
                .append(uri, that.uri)
                .append(metadata, that.metadata)
                .append(instance, that.instance)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(host)
                .append(port)
                .append(isSecure)
                .append(uri)
                .append(metadata)
                .append(instance)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("host", host)
                .append("port", port)
                .append("isSecure", isSecure)
                .append("uri", uri)
                .append("getMetadata", metadata)
                .append("instance", instance.toString())
                .toString();
    }
}
