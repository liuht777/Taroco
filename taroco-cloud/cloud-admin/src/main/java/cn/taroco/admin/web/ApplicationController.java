package cn.taroco.admin.web;

import cn.taroco.admin.registry.store.ApplicationStore;
import cn.taroco.common.web.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * 服务治理server端 注册Controller
 *
 * @author liuht
 * @date 2017/11/20 11:52
 */
@RestController
@RequestMapping("/api/applications")
@Api(tags = "服务治理接口", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationController.class);

    @Autowired
    private ApplicationStore applicationStore;

    /**
     * 根据serviceId返回Applications serviceId为空返回所有
     *
     * @param serviceId 服务serviceId
     * @return List
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询服务列表", notes = "根据指定参数获取服务列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "serviceId", value = "serviceId", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "服务状态", dataType = "String", paramType = "query")
    })
    public Response applications(@RequestParam(value = "serviceId", required = false) String serviceId,
                                 @RequestParam(value = "status", required = false) String status) {
        LOGGER.debug("Deliver registered applications with serviceId={}", serviceId);
        if (StringUtils.isEmpty(serviceId) && StringUtils.isEmpty(status)) {
            return Response.success(applicationStore.findAll());
        } else {
            return Response.success(applicationStore.findByNameAndStatus(serviceId, status));
        }
    }

    /**
     * 获取服务信息详情
     *
     * @param instanceId 服务instanceId
     * @return List
     */
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ApiOperation(value = "查询服务详情", notes = "根据instanceId获取服务详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "instanceId", value = "instanceId", dataType = "String", required = true, paramType = "query"),
    })
    public Response getDetail(@RequestParam(value = "instanceId") String instanceId) {
        return Response.success(applicationStore.find(instanceId));
    }

    /**
     * 获取所有app统计信息
     *
     * @return Response
     */
    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    @ApiOperation(value = "app统计信息", notes = "获取所有app统计信息")
    public Response appSum() {
        return Response.success(applicationStore.getSumMap());
    }

    /**
     * 移除app
     *
     * @return Response
     */
    @RequestMapping(value = "/{instanceId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "app统计信息", notes = "获取所有app统计信息")
    public Response delete(@PathVariable("instanceId") String instanceId) {
        applicationStore.delete(instanceId);
        return Response.success();
    }
}
