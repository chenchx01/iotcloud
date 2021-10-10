package com.iotcloud.system.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目(IotProject)表实体类
 *
 * @author chenchx
 * @since 2021-09-28 15:56:08
 */
@Data
@SuppressWarnings("serial")
public class IotProject extends Model<IotProject> {
    @TableId(type = IdType.AUTO)
    /**项目编号*/
    private String pmCode;
    /**
     * 项目名称
     */
    private String pmName;
    /**
     * 项目说明
     */
    private String pmText;
    /**
     * 推送接口地址
     */
    private String pmPushadress;
    /**
     * 创建时间
     */
    private Date instime;
    /**
     * 修改时间
     */
    private Date pmEdtime;
    /**
     * 创建者
     */
    private Integer creatorId;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.pmCode;
    }
}