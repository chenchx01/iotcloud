package com.iotcloud.system.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotcloud.system.web.dao.IotProjectDao;
import com.iotcloud.system.web.entity.IotProject;
import com.iotcloud.system.web.service.IotProjectService;
import org.springframework.stereotype.Service;

/**
 * 项目(IotProject)表服务实现类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:09
 */
@Service("iotProjectService")
public class IotProjectServiceImpl extends ServiceImpl<IotProjectDao, IotProject> implements IotProjectService {

}