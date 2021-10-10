package com.iotcloud.system.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotcloud.system.web.dao.IotDevconDao;
import com.iotcloud.system.web.entity.IotDevcon;
import com.iotcloud.system.web.service.IotDevconService;
import org.springframework.stereotype.Service;

/**
 * 设备(IotDevcon)表服务实现类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:04
 */
@Service("iotDevconService")
public class IotDevconServiceImpl extends ServiceImpl<IotDevconDao, IotDevcon> implements IotDevconService {

}