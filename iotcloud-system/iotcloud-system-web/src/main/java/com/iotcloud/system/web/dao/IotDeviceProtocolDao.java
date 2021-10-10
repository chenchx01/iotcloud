package com.iotcloud.system.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.system.web.entity.IotDeviceProtocol;
import org.apache.ibatis.annotations.Mapper;

/**
 * (IotDeviceProtocol)表数据库访问层
 *
 * @author chenchx
 * @since 2021-09-28 15:56:07
 */
@Mapper
public interface IotDeviceProtocolDao extends BaseMapper<IotDeviceProtocol> {

}