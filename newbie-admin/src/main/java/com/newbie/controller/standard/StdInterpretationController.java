package com.newbie.controller.standard;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.newbie.common.util.R;
import com.newbie.standard.domain.entity.StdInterpretation;
import com.newbie.standard.service.StdInterpretationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 文件解读信息管理控制器
 *
 * @author Claude
 */
@RestController
@RequestMapping("/standard/interpretation")
@RequiredArgsConstructor
@Tag(name = "文件解读信息管理")
public class StdInterpretationController {

    private final StdInterpretationService stdInterpretationService;

    @Operation(summary = "根据资源ID查询解读信息列表")
    @SaCheckPermission("standard:interpretation:list")
    @GetMapping("/listByResourceId/{resourceId}")
    public R<List<StdInterpretation>> listByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdInterpretationService.listByResourceId(resourceId));
    }

    @Operation(summary = "根据来源类型查询解读信息列表")
    @SaCheckPermission("standard:interpretation:list")
    @GetMapping("/listBySourceType")
    public R<List<StdInterpretation>> listByResourceIdAndSourceType(@Parameter(description = "标准资源ID") @RequestParam String resourceId,
                                                                   @Parameter(description = "来源类型") @RequestParam String sourceType) {
        return R.ok(stdInterpretationService.listByResourceIdAndSourceType(resourceId, sourceType));
    }

    @Operation(summary = "查询官方解读信息列表")
    @SaCheckPermission("standard:interpretation:list")
    @GetMapping("/listOfficial/{resourceId}")
    public R<List<StdInterpretation>> listOfficialByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdInterpretationService.listOfficialByResourceId(resourceId));
    }

    @Operation(summary = "根据标题模糊查询解读信息")
    @SaCheckPermission("standard:interpretation:query")
    @GetMapping("/listByTitle")
    public R<List<StdInterpretation>> listByTitleLike(@Parameter(description = "解读标题关键字") @RequestParam String title) {
        return R.ok(stdInterpretationService.listByTitleLike(title));
    }

    @Operation(summary = "获取解读信息详情")
    @SaCheckPermission("standard:interpretation:query")
    @GetMapping("/{id}")
    public R<StdInterpretation> getById(@Parameter(description = "解读信息ID") @PathVariable String id) {
        StdInterpretation interpretation = stdInterpretationService.getById(id);
        if (interpretation == null) {
            return R.error("解读信息不存在");
        }
        
        // 更新查看次数
        stdInterpretationService.incrementViewCount(id);
        
        return R.ok(interpretation);
    }

    @Operation(summary = "新增解读信息")
    @SaCheckPermission("standard:interpretation:add")
    @PostMapping("/add")
    public R<Object> add(@RequestBody StdInterpretation interpretation) {
        return stdInterpretationService.add(interpretation) ? R.ok().setMsg("新增成功") : R.error("新增失败");
    }

    @Operation(summary = "修改解读信息")
    @SaCheckPermission("standard:interpretation:update")
    @PostMapping("/update")
    public R<Object> update(@RequestBody StdInterpretation interpretation) {
        if (interpretation.getId() == null) {
            return R.error("解读信息ID为空");
        }
        try {
            return stdInterpretationService.update(interpretation) ? R.ok().setMsg("修改成功") : R.error("修改失败");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @Operation(summary = "批量删除解读信息")
    @SaCheckPermission("standard:interpretation:delete")
    @PostMapping("/deleteBatch")
    public R<Object> deleteBatch(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return R.error("解读信息ID为空");
        }
        return stdInterpretationService.deleteBatch(Arrays.asList(ids)) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "根据资源ID删除所有解读信息")
    @SaCheckPermission("standard:interpretation:delete")
    @DeleteMapping("/deleteByResourceId/{resourceId}")
    public R<Object> deleteByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return stdInterpretationService.deleteByResourceId(resourceId) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "统计资源的解读数量")
    @SaCheckPermission("standard:interpretation:query")
    @GetMapping("/count/{resourceId}")
    public R<Long> countByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdInterpretationService.countByResourceId(resourceId));
    }
}