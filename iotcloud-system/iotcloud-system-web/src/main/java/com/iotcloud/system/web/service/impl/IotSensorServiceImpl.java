package com.iotcloud.system.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotcloud.system.web.dao.IotSensorDao;
import com.iotcloud.system.web.entity.IotSensor;
import com.iotcloud.system.web.service.IotSensorService;
import org.springframework.stereotype.Service;

/**
 * 传感器(IotSensor)表服务实现类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:10
 */
@Service("iotSensorService")
public class IotSensorServiceImpl extends ServiceImpl<IotSensorDao, IotSensor> implements IotSensorService {

}