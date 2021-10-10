package com.iotcloud.system.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotcloud.system.web.dao.IotNavigationBarDao;
import com.iotcloud.system.web.entity.IotNavigationBar;
import com.iotcloud.system.web.service.IotNavigationBarService;
import org.springframework.stereotype.Service;

/**
 * 导航栏(IotNavigationBar)表服务实现类
 *
 * @author chenchx
 * @since 2021-09-28 16:12:52
 */
@Service("iotNavigationBarService")
public class IotNavigationBarServiceImpl extends ServiceImpl<IotNavigationBarDao, IotNavigationBar> implements IotNavigationBarService {

}