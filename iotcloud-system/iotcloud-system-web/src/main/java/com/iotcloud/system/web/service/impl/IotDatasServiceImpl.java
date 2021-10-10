package com.iotcloud.system.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iotcloud.system.web.dao.IotDatasDao;
import com.iotcloud.system.web.entity.IotDatas;
import com.iotcloud.system.web.service.IotDatasService;
import org.springframework.stereotype.Service;

/**
 * (IotDatas)表服务实现类
 *
 * @author chenchx
 * @since 2021-09-28 15:55:59
 */
@Service("iotDatasService")
public class IotDatasServiceImpl extends ServiceImpl<IotDatasDao, IotDatas> implements IotDatasService {

}