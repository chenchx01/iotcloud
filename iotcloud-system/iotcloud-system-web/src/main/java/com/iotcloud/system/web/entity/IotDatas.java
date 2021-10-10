package com.iotcloud.system.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (IotDatas)表实体类
 *
 * @author chenchx
 * @since 2021-09-28 15:55:58
 */
@Data
@SuppressWarnings("serial")
public class IotDatas extends Model<IotDatas> {
    @TableId(type = IdType.AUTO)
    /**数据ID*/
    private Integer daId;
    /**
     * 设备编号
     */
    private String dcCode;
    /**
     * 传感器编码
     */
    private String senCode;
    /**
     * 数据内容 多个数据用||隔开
     */
    private String daValue;
    /**
     * 数据的插入时间
     */
    private Date daUnixtime;

    private String reportTime;

    private String ownCoding;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.daId;
    }
}