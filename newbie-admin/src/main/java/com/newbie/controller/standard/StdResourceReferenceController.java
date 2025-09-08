package com.newbie.controller.standard;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.newbie.common.util.R;
import com.newbie.standard.domain.entity.StdResourceReference;
import com.newbie.standard.service.StdResourceReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 标准资源引用关联管理控制器
 *
 * @author Claude
 */
@RestController
@RequestMapping("/standard/reference")
@RequiredArgsConstructor
@Tag(name = "标准资源引用关联管理")
public class StdResourceReferenceController {

    private final StdResourceReferenceService stdResourceReferenceService;

    @Operation(summary = "根据资源ID查询引用关系列表")
    @SaCheckPermission("standard:reference:list")
    @GetMapping("/listByResourceId/{resourceId}")
    public R<List<StdResourceReference>> listByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdResourceReferenceService.listByResourceId(resourceId));
    }

    @Operation(summary = "根据引用类型查询引用关系列表")
    @SaCheckPermission("standard:reference:list")
    @GetMapping("/listByType")
    public R<List<StdResourceReference>> listByResourceIdAndType(@Parameter(description = "标准资源ID") @RequestParam String resourceId,
                                                               @Parameter(description = "引用类型") @RequestParam String referenceType) {
        return R.ok(stdResourceReferenceService.listByResourceIdAndType(resourceId, referenceType));
    }

    @Operation(summary = "查询引用了指定资源的其他标准")
    @SaCheckPermission("standard:reference:list")
    @GetMapping("/listByReferenceId/{referenceId}")
    public R<List<StdResourceReference>> listByReferenceId(@Parameter(description = "被引用资源ID") @PathVariable String referenceId) {
        return R.ok(stdResourceReferenceService.listByReferenceId(referenceId));
    }

    @Operation(summary = "根据资源ID和排序查询引用关系列表")
    @SaCheckPermission("standard:reference:list")
    @GetMapping("/listOrderBySort/{resourceId}")
    public R<List<StdResourceReference>> listByResourceIdOrderBySort(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdResourceReferenceService.listByResourceIdOrderBySort(resourceId));
    }

    @Operation(summary = "获取引用关系详情")
    @SaCheckPermission("standard:reference:query")
    @GetMapping("/{id}")
    public R<StdResourceReference> getById(@Parameter(description = "引用关系ID") @PathVariable String id) {
        StdResourceReference reference = stdResourceReferenceService.getById(id);
        if (reference == null) {
            return R.error("引用关系不存在");
        }
        return R.ok(reference);
    }

    @Operation(summary = "新增引用关系")
    @SaCheckPermission("standard:reference:add")
    @PostMapping("/add")
    public R<Object> add(@RequestBody StdResourceReference reference) {
        return stdResourceReferenceService.add(reference) ? R.ok().setMsg("新增成功") : R.error("新增失败");
    }

    @Operation(summary = "修改引用关系")
    @SaCheckPermission("standard:reference:update")
    @PostMapping("/update")
    public R<Object> update(@RequestBody StdResourceReference reference) {
        if (reference.getId() == null) {
            return R.error("引用关系ID为空");
        }
        try {
            return stdResourceReferenceService.update(reference) ? R.ok().setMsg("修改成功") : R.error("修改失败");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @Operation(summary = "批量删除引用关系")
    @SaCheckPermission("standard:reference:delete")
    @PostMapping("/deleteBatch")
    public R<Object> deleteBatch(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return R.error("引用关系ID为空");
        }
        return stdResourceReferenceService.deleteBatch(Arrays.asList(ids)) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "根据资源ID删除所有引用关系")
    @SaCheckPermission("standard:reference:delete")
    @DeleteMapping("/deleteByResourceId/{resourceId}")
    public R<Object> deleteByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return stdResourceReferenceService.deleteByResourceId(resourceId) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "统计资源的引用数量")
    @SaCheckPermission("standard:reference:query")
    @GetMapping("/count")
    public R<Long> countByResourceIdAndType(@Parameter(description = "标准资源ID") @RequestParam String resourceId,
                                          @Parameter(description = "引用类型") @RequestParam String referenceType) {
        return R.ok(stdResourceReferenceService.countByResourceIdAndType(resourceId, referenceType));
    }

    @Operation(summary = "批量新增引用关系")
    @SaCheckPermission("standard:reference:add")
    @PostMapping("/batchAdd")
    public R<Object> batchAdd(@RequestBody List<StdResourceReference> referenceList) {
        if (referenceList == null || referenceList.isEmpty()) {
            return R.error("引用关系数据为空");
        }
        return stdResourceReferenceService.batchAdd(referenceList) ? R.ok().setMsg("新增成功") : R.error("新增失败");
    }
}