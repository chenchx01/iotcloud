package com.iotcloud.system.web.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iotcloud.common.core.util.Result;
import com.iotcloud.system.web.entity.IotDeviceProtocol;
import com.iotcloud.system.web.service.IotDeviceProtocolService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (IotDeviceProtocol)表控制层
 *
 * @author chenchx
 * @since 2021-09-28 15:56:07
 */
@RestController
@RequestMapping("iotDeviceProtocol")
public class IotDeviceProtocolController {
    /**
     * 服务对象
     */
    @Resource
    private IotDeviceProtocolService iotDeviceProtocolService;

    /**
     * 分页查询所有数据
     *
     * @param page              分页对象
     * @param iotDeviceProtocol 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<IotDeviceProtocol> page, IotDeviceProtocol iotDeviceProtocol) {
        return Result.ok(this.iotDeviceProtocolService.page(page, new QueryWrapper<>(iotDeviceProtocol)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.ok(this.iotDeviceProtocolService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param iotDeviceProtocol 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody IotDeviceProtocol iotDeviceProtocol) {
        return Result.ok(this.iotDeviceProtocolService.save(iotDeviceProtocol));
    }

    /**
     * 修改数据
     *
     * @param iotDeviceProtocol 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody IotDeviceProtocol iotDeviceProtocol) {
        return Result.ok(this.iotDeviceProtocolService.updateById(iotDeviceProtocol));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.iotDeviceProtocolService.removeByIds(idList));
    }
}