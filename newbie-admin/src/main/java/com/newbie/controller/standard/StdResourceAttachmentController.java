package com.newbie.controller.standard;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.newbie.common.util.R;
import com.newbie.standard.domain.entity.StdResourceAttachment;
import com.newbie.standard.service.StdResourceAttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 标准资源附件管理控制器
 *
 * @author Claude
 */
@RestController
@RequestMapping("/standard/attachment")
@RequiredArgsConstructor
@Tag(name = "标准资源附件管理")
public class StdResourceAttachmentController {

    private final StdResourceAttachmentService stdResourceAttachmentService;

    @Operation(summary = "根据资源ID查询附件列表")
    @SaCheckPermission("standard:attachment:list")
    @GetMapping("/listByResourceId/{resourceId}")
    public R<List<StdResourceAttachment>> listByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdResourceAttachmentService.listByResourceId(resourceId));
    }

    @Operation(summary = "根据资源ID查询主文件")
    @SaCheckPermission("standard:attachment:query")
    @GetMapping("/mainFile/{resourceId}")
    public R<StdResourceAttachment> getMainFileByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        StdResourceAttachment mainFile = stdResourceAttachmentService.getMainFileByResourceId(resourceId);
        return mainFile != null ? R.ok(mainFile) : R.error("主文件不存在");
    }

    @Operation(summary = "根据附件类型查询附件列表")
    @SaCheckPermission("standard:attachment:list")
    @GetMapping("/listByType")
    public R<List<StdResourceAttachment>> listByTypeAndResourceId(@Parameter(description = "标准资源ID") @RequestParam String resourceId,
                                                                @Parameter(description = "附件类型") @RequestParam String attachmentType) {
        return R.ok(stdResourceAttachmentService.listByTypeAndResourceId(resourceId, attachmentType));
    }

    @Operation(summary = "获取附件详情")
    @SaCheckPermission("standard:attachment:query")
    @GetMapping("/{id}")
    public R<StdResourceAttachment> getById(@Parameter(description = "附件ID") @PathVariable String id) {
        StdResourceAttachment attachment = stdResourceAttachmentService.getById(id);
        if (attachment == null) {
            return R.error("附件不存在");
        }
        return R.ok(attachment);
    }

    @Operation(summary = "新增标准资源附件")
    @SaCheckPermission("standard:attachment:add")
    @PostMapping("/add")
    public R<Object> add(@RequestBody StdResourceAttachment attachment) {
        return stdResourceAttachmentService.add(attachment) ? R.ok().setMsg("新增成功") : R.error("新增失败");
    }

    @Operation(summary = "修改标准资源附件")
    @SaCheckPermission("standard:attachment:update")
    @PostMapping("/update")
    public R<Object> update(@RequestBody StdResourceAttachment attachment) {
        if (attachment.getId() == null) {
            return R.error("附件ID为空");
        }
        try {
            return stdResourceAttachmentService.update(attachment) ? R.ok().setMsg("修改成功") : R.error("修改失败");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }

    @Operation(summary = "批量删除附件")
    @SaCheckPermission("standard:attachment:delete")
    @PostMapping("/deleteBatch")
    public R<Object> deleteBatch(@RequestBody String[] ids) {
        if (ids == null || ids.length == 0) {
            return R.error("附件ID为空");
        }
        return stdResourceAttachmentService.deleteBatch(Arrays.asList(ids)) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "根据资源ID删除所有附件")
    @SaCheckPermission("standard:attachment:delete")
    @DeleteMapping("/deleteByResourceId/{resourceId}")
    public R<Object> deleteByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return stdResourceAttachmentService.deleteByResourceId(resourceId) ? R.ok().setMsg("删除成功") : R.error("删除失败");
    }

    @Operation(summary = "统计资源的附件数量")
    @SaCheckPermission("standard:attachment:query")
    @GetMapping("/count/{resourceId}")
    public R<Long> countByResourceId(@Parameter(description = "标准资源ID") @PathVariable String resourceId) {
        return R.ok(stdResourceAttachmentService.countByResourceId(resourceId));
    }
}