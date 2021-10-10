package com.iotcloud.system.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 传感器(IotSensor)表实体类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:10
 */
@Data
@SuppressWarnings("serial")
public class IotSensor extends Model<IotSensor> {
    @TableId(type = IdType.AUTO)
    /**传感器ID*/
    private Integer senId;
    /**
     * 设备编号
     */
    private String dcCode;
    /**
     * 传感器名称
     */
    private String senName;
    /**
     * 传感器编号
     */
    private String senCode;
    /**
     * 上报时间
     */
    private Date reportTime;
    /**
     * 创建时间
     */
    private Date intime;
    /**
     * 修改时间
     */
    private Date updtime;
    /**
     * 产品ID
     */
    private Integer productId;
    /**
     * 数据总数
     */
    private Integer dataAccount;
    /**
     * 自编码
     */
    private String ownCoding;
    /**
     * 传感器状态，0禁用 1启用 2数据异常 3 更换 4删除
     */
    private Integer status;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.senId;
    }
}