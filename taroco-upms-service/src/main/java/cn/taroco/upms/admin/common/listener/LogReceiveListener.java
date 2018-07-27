///*
// *    Copyright (c) 2018-2025, liuht All rights reserved.
// *
// * Redistribution and use in source and binary forms, with or without
// * modification, are permitted provided that the following conditions are met:
// *
// * Redistributions of source code must retain the above copyright notice,
// * this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright
// * notice, this list of conditions and the following disclaimer in the
// * documentation and/or other materials provided with the distribution.
// * Neither the name of the pig4cloud.com developer nor the names of its
// * contributors may be used to endorse or promote products derived from
// * this software without specific prior written permission.
// * Author: liuht (wangiegie@gmail.com)
// */
//
//package cn.taroco.upms.admin.common.listener;
//
//import cn.taroco.common.constants.MqQueueConstant;
//import cn.taroco.common.entity.SysLog;
//import cn.taroco.common.vo.LogVO;
//import cn.taroco.upms.admin.service.SysLogService;
//import org.slf4j.MDC;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @author liuht
// * @date 2017/11/17
// */
//@Component
//@RabbitListener(queues = MqQueueConstant.LOG_QUEUE)
//public class LogReceiveListener {
//    private static final String KEY_USER = "user";
//
//    @Autowired
//    private SysLogService sysLogService;
//
//    @RabbitHandler
//    public void receive(LogVO logVo) {
//        SysLog sysLog = logVo.getSysLog();
//        MDC.put(KEY_USER, logVo.getUsername());
//        sysLog.setCreateBy(logVo.getUsername());
//        sysLogService.insert(sysLog);
//        MDC.remove(KEY_USER);
//    }
//}
