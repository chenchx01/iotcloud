package com.iotcloud.system.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 导航栏(IotNavigationBar)表实体类
 *
 * @author chenchx
 * @since 2021-09-28 16:12:52
 */
@Data
@SuppressWarnings("serial")
public class IotNavigationBar extends Model<IotNavigationBar> {
    @TableId(type = IdType.AUTO)

    private Integer navId;
    /**
     * 标题
     */
    private String title;
    /**
     * 路径
     */
    private String url;
    /**
     * 图标
     */
    private String icon;
    /**
     * 排序
     */
    private Integer orderby;
    /**
     * 父级ID
     */
    private Integer parentId;
    /**
     * 状态
     */
    private String status;
    /**
     * 新增时间
     */
    private Date insTime;
    /**
     * 修改时间
     */
    private Date updTime;
    /**
     * 权限标识符
     */
    private String permissionKey;


    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.navId;
    }
}