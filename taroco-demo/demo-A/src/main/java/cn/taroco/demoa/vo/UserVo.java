package cn.taroco.demoa.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/23 14:21.
 */
@ApiModel(value = "userVo", description = "用户信息")
public class UserVo {

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("是否可用")
    private Boolean enable;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
