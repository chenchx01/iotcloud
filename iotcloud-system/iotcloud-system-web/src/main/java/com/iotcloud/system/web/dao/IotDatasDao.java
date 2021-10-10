package com.iotcloud.system.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.system.web.entity.IotDatas;
import org.apache.ibatis.annotations.Mapper;

/**
 * (IotDatas)表数据库访问层
 *
 * @author chenchx
 * @since 2021-09-28 15:55:58
 */
@Mapper
public interface IotDatasDao extends BaseMapper<IotDatas> {

}