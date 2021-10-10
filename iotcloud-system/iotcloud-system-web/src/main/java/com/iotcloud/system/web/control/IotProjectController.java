package com.iotcloud.system.web.control;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.iotcloud.common.core.util.Result;
import com.iotcloud.system.web.entity.IotProject;
import com.iotcloud.system.web.service.IotProjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 项目(IotProject)表控制层
 *
 * @author chenchx
 * @since 2021-09-28 15:56:09
 */
@RestController
@RequestMapping("iotProject")
public class IotProjectController {
    /**
     * 服务对象
     */
    @Resource
    private IotProjectService iotProjectService;

    /**
     * 分页查询所有数据
     *
     * @param page       分页对象
     * @param iotProject 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Result selectAll(Page<IotProject> page, IotProject iotProject) {
        return Result.ok(this.iotProjectService.page(page, new QueryWrapper<>(iotProject)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public Result selectOne(@PathVariable Serializable id) {
        return Result.ok(this.iotProjectService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param iotProject 实体对象
     * @return 新增结果
     */
    @PostMapping
    public Result insert(@RequestBody IotProject iotProject) {
        return Result.ok(this.iotProjectService.save(iotProject));
    }

    /**
     * 修改数据
     *
     * @param iotProject 实体对象
     * @return 修改结果
     */
    @PutMapping
    public Result update(@RequestBody IotProject iotProject) {
        return Result.ok(this.iotProjectService.updateById(iotProject));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@RequestParam("idList") List<Long> idList) {
        return Result.ok(this.iotProjectService.removeByIds(idList));
    }
}