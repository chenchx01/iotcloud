package com.iotcloud.system.web.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iotcloud.common.core.util.Result;
import com.iotcloud.system.web.entity.IotNavigationBar;
import com.iotcloud.system.web.service.IotNavigationBarService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 导航栏(IotNavigationBar)表控制层
 *
 * @author chenchx
 * @since 2021-09-28 16:12:53
 */
@RestController
@RequestMapping("iotNavigationBar")
public class IotNavigationBarController {
    /**
     * 服务对象
     */
    @Resource
    private IotNavigationBarService iotNavigationBarService;

    /**
     * 分页查询所有数据
     *
     * @param page             分页对象
     * @param iotNavigationBar 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<IotNavigationBar> page, IotNavigationBar iotNavigationBar) {
        return Result.ok(this.iotNavigationBarService.page(page, new QueryWrapper<>(iotNavigationBar)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.ok(this.iotNavigationBarService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param iotNavigationBar 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody IotNavigationBar iotNavigationBar) {
        return Result.ok(this.iotNavigationBarService.save(iotNavigationBar));
    }

    /**
     * 修改数据
     *
     * @param iotNavigationBar 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody IotNavigationBar iotNavigationBar) {
        return Result.ok(this.iotNavigationBarService.updateById(iotNavigationBar));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.iotNavigationBarService.removeByIds(idList));
    }
}