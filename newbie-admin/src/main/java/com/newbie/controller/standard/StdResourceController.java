package com.newbie.controller.standard;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.newbie.common.util.R;
import com.newbie.standard.domain.entity.StdResource;
import com.newbie.standard.service.StdResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 标准资源管理控制器
 *
 * @author Claude
 */
@RestController
@RequestMapping("/standard/resource")
@RequiredArgsConstructor
@Tag(name = "标准资源管理")
public class StdResourceController {

    private final StdResourceService stdResourceService;

    @Operation(summary = "分页查询标准资源列表")
    @SaCheckPermission("standard:resource:list")
    @GetMapping("/page")
    public R<Page<StdResource>> page(@Parameter(description = "页码") @RequestParam(defaultValue = "1") Integer current,
                                    @Parameter(description = "每页数量") @RequestParam(defaultValue = "10") Integer size,
                                    StdResource stdResource) {
        Page<StdResource> page = new Page<>(current, size);
        return R.ok(stdResourceService.selectPage(page, stdResource));
    }

    @Operation(summary = "获取标准资源详情")
    @SaCheckPermission("standard:resource:query")
    @GetMapping("/{id}")
    public R<StdResource> getById(@Parameter(description = "标准资源ID") @PathVariable String id) {
        StdResource stdResource = stdResourceService.getById(id);
        if (stdResource == null) {
            return R.error("标准资源不存在");
        }
        return R.ok(stdResource);
    }

    @Operation(summary = "根据标准编号查询标准资源")
    @SaCheckPermission("standard:resource:query")
    @GetMapping("/code/{resourceCode}")
    public R<StdResource> getByResourceCode(@Parameter(description = "标准编号") @PathVariable String resourceCode) {
        StdResource stdResource = stdResourceService.getByResourceCode(resourceCode);
        if (stdResource == null) {
            return R.error("标准资源不存在");
        }
        return R.ok(stdResource);
    }

    @Operation(summary = "新增标准资源")
    @SaCheckPermission("standard:resource:add")
    @PostMapping("/add")
    public R<Object> add(@RequestBody StdResource stdResource) {
        try {
            return stdResourceService.add(stdResource) ? R.ok().setMsg("新增成功") : R.error("新增失败");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @Operation(summary = "修改标准资源")
    @SaCheckPermission("standard:resource:update")
    @PostMapping("/update")
    public R<Object> update(@RequestBody StdResource stdResource) {
        if (stdResource.getId() == null) {
            return R.error("标准资源ID为空");
        }
        try {
            return stdResourceService.update(stdResource) ? R.ok().setMsg("修改成功") : R.error("修改失败");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @Operation(summary = "批量删除标准资源")
    @SaCheckPermission("standard:resource:delete")
    @PostMapping("/deleteBatch")
    public R<Object> deleteBatch(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return R.error("标准资源ID为空");
        }
        return stdResourceService.deleteBatch(Arrays.asList(ids)) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "根据标准状态查询标准资源列表")
    @SaCheckPermission("standard:resource:list")
    @GetMapping("/listByStatus")
    public R<List<StdResource>> listByStatus(@Parameter(description = "标准状态") @RequestParam String standardStatus) {
        return R.ok(stdResourceService.listByStatus(standardStatus));
    }

    @Operation(summary = "根据标准领域查询标准资源列表")
    @SaCheckPermission("standard:resource:list")
    @GetMapping("/listByField")
    public R<List<StdResource>> listByField(@Parameter(description = "标准领域") @RequestParam String standardField) {
        return R.ok(stdResourceService.listByField(standardField));
    }

    @Operation(summary = "根据标准分类查询标准资源列表")
    @SaCheckPermission("standard:resource:list")
    @GetMapping("/listByClassification")
    public R<List<StdResource>> listByClassification(@Parameter(description = "标准分类") @RequestParam String standardClassification) {
        return R.ok(stdResourceService.listByClassification(standardClassification));
    }

    @Operation(summary = "获取标准状态选项")
    @GetMapping("/statusOptions")
    public R<List<String>> getStatusOptions() {
        return R.ok(stdResourceService.getStatusOptions());
    }

    @Operation(summary = "获取标准领域选项")
    @GetMapping("/fieldOptions")
    public R<List<String>> getFieldOptions() {
        return R.ok(stdResourceService.getFieldOptions());
    }
}