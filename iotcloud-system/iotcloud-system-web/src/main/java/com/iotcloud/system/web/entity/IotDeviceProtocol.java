package com.iotcloud.system.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (IotDeviceProtocol)表实体类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:07
 */
@Data
@SuppressWarnings("serial")
public class IotDeviceProtocol extends Model<IotDeviceProtocol> {
    @TableId(type = IdType.AUTO)

    private Integer id;

    private Integer port;

    private String name;

    private String filePath;

    private String decoderClass;

    private String unpackClass;

    private Integer status;

    private Date instime;

    private Date updtime;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}