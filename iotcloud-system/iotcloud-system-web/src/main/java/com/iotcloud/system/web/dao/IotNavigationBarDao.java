package com.iotcloud.system.web.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.system.web.entity.IotNavigationBar;
import org.apache.ibatis.annotations.Mapper;

/**
 * 导航栏(IotNavigationBar)表数据库访问层
 *
 * @author chenchx
 * @since 2021-09-28 16:12:52
 */
@Mapper
public interface IotNavigationBarDao extends BaseMapper<IotNavigationBar> {

}