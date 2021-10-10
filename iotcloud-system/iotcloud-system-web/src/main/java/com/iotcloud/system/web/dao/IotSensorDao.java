package com.iotcloud.system.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.system.web.entity.IotSensor;
import org.apache.ibatis.annotations.Mapper;

/**
 * 传感器(IotSensor)表数据库访问层
 *
 * @author chenchx
 * @since 2021-09-28 15:56:10
 */
@Mapper
public interface IotSensorDao extends BaseMapper<IotSensor> {

}