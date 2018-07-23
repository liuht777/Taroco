/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.taroco.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * 服务状态
 *
 * @author liuht
 */
public class StatusInfo implements Serializable {
	private static final long serialVersionUID = 2L;
    public static final String STATUS_KEY = "status";

	private final String status;
	private final long timestamp;

	public StatusInfo(String status, long timestamp) {
		this.status = status.toUpperCase();
		this.timestamp = timestamp;
	}

	public static StatusInfo valueOf(String statusCode) {
		return new StatusInfo(statusCode, System.currentTimeMillis());
	}

	public static StatusInfo ofUnknown() {
		return valueOf("UNKNOWN");
	}

	public static StatusInfo ofUp() {
		return valueOf("UP");
	}

	public static StatusInfo ofDown() {
        return valueOf("DOWN");
	}

	public static StatusInfo ofOffline() {
        return valueOf("OFFLINE");
	}

	public String getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp;
	}

	@JsonIgnore
	public boolean isUp() {
		return "UP".equals(status);
	}

	@JsonIgnore
	public boolean isOffline() {
		return "OFFLINE".equals(status);
	}

	@JsonIgnore
	public boolean isDown() {
		return "DOWN".equals(status);
	}

	@JsonIgnore
	public boolean isUnknown() {
		return "UNKNOWN".equals(status);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatusInfo that = (StatusInfo) o;

        return new EqualsBuilder()
                .append(timestamp, that.timestamp)
                .append(status, that.status)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(status)
                .append(timestamp)
                .toHashCode();
    }

    @Override
	public String toString() {
		return "StatusInfo [status=" + status + ", timestamp=" + timestamp + "]";
	}

}