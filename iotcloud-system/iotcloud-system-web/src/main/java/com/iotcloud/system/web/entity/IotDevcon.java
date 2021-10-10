package com.iotcloud.system.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备(IotDevcon)表实体类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:04
 */
@Data
@SuppressWarnings("serial")
public class IotDevcon extends Model<IotDevcon> {
    @TableId(type = IdType.AUTO)
    /**设备编号*/
    private String dcCode;
    /**
     * 项目编号
     */
    private String pmCode;
    /**
     * 设备名称
     */
    private String dcName;
    /**
     * 设备说明
     */
    private String dcText;
    /**
     * 0：禁用，1：启用，4：删除
     */
    private Integer dcState;
    /**
     * 创建时间
     */
    private Date dcIntime;
    /**
     * 修改时间
     */
    private Date dcUpdtime;
    /**
     * 创建者ID
     */
    private Integer creatorId;
    /**
     * 协议ID
     */
    private Integer protocolId;
    /**
     * 是否解析
     */
    private Integer isAnalysis;
    /**
     * 是否在线
     */
    private Integer isOnline;
    /**
     * 状态改变时间
     */
    private String onlineTime;
    /**
     * 版本号
     */
    private String dcVersion;
    /**
     * 图片路径
     */
    private String filePath;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.dcCode;
    }
}