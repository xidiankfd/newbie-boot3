package com.newbie.controller.standard;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.newbie.common.util.R;
import com.newbie.standard.domain.entity.StdTerminology;
import com.newbie.standard.service.StdTerminologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 术语定义管理控制器
 *
 * @author Claude
 */
@RestController
@RequestMapping("/standard/terminology")
@RequiredArgsConstructor
@Tag(name = "术语定义管理")
public class StdTerminologyController {

    private final StdTerminologyService stdTerminologyService;

    @Operation(summary = "根据资源ID查询术语列表")
    @SaCheckPermission("standard:terminology:list")
    @GetMapping("/listByResourceId/{resourceId}")
    public R<List<StdTerminology>> listByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdTerminologyService.listByResourceId(resourceId));
    }

    @Operation(summary = "根据术语分类查询术语列表")
    @SaCheckPermission("standard:terminology:list")
    @GetMapping("/listByCategory")
    public R<List<StdTerminology>> listByResourceIdAndCategory(@Parameter(description = "标准资源ID") @RequestParam String resourceId,
                                                              @Parameter(description = "术语分类") @RequestParam String termCategory) {
        return R.ok(stdTerminologyService.listByResourceIdAndCategory(resourceId, termCategory));
    }

    @Operation(summary = "根据术语名称查询术语")
    @SaCheckPermission("standard:terminology:query")
    @GetMapping("/listByTermName")
    public R<List<StdTerminology>> listByTermName(@Parameter(description = "术语名称") @RequestParam String termName) {
        return R.ok(stdTerminologyService.listByTermName(termName));
    }

    @Operation(summary = "根据资源ID和排序查询术语列表")
    @SaCheckPermission("standard:terminology:list")
    @GetMapping("/listOrderBySort/{resourceId}")
    public R<List<StdTerminology>> listByResourceIdOrderBySort(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdTerminologyService.listByResourceIdOrderBySort(resourceId));
    }

    @Operation(summary = "获取术语分类列表")
    @SaCheckPermission("standard:terminology:list")
    @GetMapping("/categories/{resourceId}")
    public R<List<String>> getCategoriesByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdTerminologyService.getCategoriesByResourceId(resourceId));
    }

    @Operation(summary = "获取术语详情")
    @SaCheckPermission("standard:terminology:query")
    @GetMapping("/{id}")
    public R<StdTerminology> getById(@Parameter(description = "术语ID") @PathVariable String id) {
        StdTerminology terminology = stdTerminologyService.getById(id);
        if (terminology == null) {
            return R.error("术语不存在");
        }
        return R.ok(terminology);
    }

    @Operation(summary = "新增术语")
    @SaCheckPermission("standard:terminology:add")
    @PostMapping("/add")
    public R<Object> add(@RequestBody StdTerminology terminology) {
        return stdTerminologyService.add(terminology) ? R.ok().setMsg("新增成功") : R.error("新增失败");
    }

    @Operation(summary = "修改术语")
    @SaCheckPermission("standard:terminology:update")
    @PostMapping("/update")
    public R<Object> update(@RequestBody StdTerminology terminology) {
        if (terminology.getId() == null) {
            return R.error("术语ID为空");
        }
        try {
            return stdTerminologyService.update(terminology) ? R.ok().setMsg("修改成功") : R.error("修改失败");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @Operation(summary = "批量删除术语")
    @SaCheckPermission("standard:terminology:delete")
    @PostMapping("/deleteBatch")
    public R<Object> deleteBatch(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return R.error("术语ID为空");
        }
        return stdTerminologyService.deleteBatch(Arrays.asList(ids)) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "根据资源ID删除所有术语")
    @SaCheckPermission("standard:terminology:delete")
    @DeleteMapping("/deleteByResourceId/{resourceId}")
    public R<Object> deleteByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return stdTerminologyService.deleteByResourceId(resourceId) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "批量导入术语")
    @SaCheckPermission("standard:terminology:import")
    @PostMapping("/batchImport")
    public R<Object> batchImport(@RequestBody List<StdTerminology> terminologyList) {
        if (terminologyList == null || terminologyList.isEmpty()) {
            return R.error("导入数据为空");
        }
        return stdTerminologyService.batchImport(terminologyList) ? R.ok().setMsg("导入成功") : R.error("导入失败");
    }
}