package cn.taroco.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色
 *
 * @author liuht
 * @since 2017-10-29
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleName;
    private String roleCode;
    private String roleDesc;
    private Date createTime;
    private Date updateTime;
    private String delFlag;
}
