package cn.taroco.rbac.admin.service;


import cn.taroco.common.entity.SysLog;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 日志表 服务类
 * </p>
 *
 * @author liuht
 * @since 2017-11-20
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 通过ID删除日志（逻辑删除）
     *
     * @param id 日志ID
     * @return true/false
     */
    Boolean updateByLogId(Long id);
}
