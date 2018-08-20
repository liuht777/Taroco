package cn.taroco.common.vo;

import cn.taroco.common.entity.SysLog;
import lombok.Data;

import java.io.Serializable;

/**
 * 日志记录VO
 *
 * @author liuht
 * @date 2017/11/20
 */
@Data
public class LogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private SysLog sysLog;
    private String username;
}
