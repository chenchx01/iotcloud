package com.iotcloud.system.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iotcloud.system.web.entity.IotProject;
import org.apache.ibatis.annotations.Mapper;

/**
 * 项目(IotProject)表数据库访问层
 *
 * @author chenchx
 * @since 2021-09-28 15:56:08
 */
@Mapper
public interface IotProjectDao extends BaseMapper<IotProject> {

}