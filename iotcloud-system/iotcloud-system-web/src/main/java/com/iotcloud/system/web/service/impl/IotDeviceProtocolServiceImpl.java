package com.iotcloud.system.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.iotcloud.system.web.dao.IotDeviceProtocolDao;
import com.iotcloud.system.web.entity.IotDeviceProtocol;
import com.iotcloud.system.web.service.IotDeviceProtocolService;
import org.springframework.stereotype.Service;

/**
 * (IotDeviceProtocol)表服务实现类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:07
 */
@Service("iotDeviceProtocolService")
public class IotDeviceProtocolServiceImpl extends ServiceImpl<IotDeviceProtocolDao, IotDeviceProtocol> implements IotDeviceProtocolService {

}