package com.iotcloud.system.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.system.web.entity.IotDevcon;
import org.apache.ibatis.annotations.Mapper;

/**
 * 设备(IotDevcon)表数据库访问层
 *
 * @author chenchx
 * @since 2021-09-28 15:56:04
 */
@Mapper
public interface IotDevconDao extends BaseMapper<IotDevcon> {

}