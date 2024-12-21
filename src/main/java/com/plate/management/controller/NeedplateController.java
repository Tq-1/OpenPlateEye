package com.plate.management.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.plate.framework.web.domain.server.Sys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.plate.common.annotation.Log;
import com.plate.common.core.controller.BaseController;
import com.plate.common.core.domain.AjaxResult;
import com.plate.common.enums.BusinessType;
import com.plate.management.domain.Needplate;
import com.plate.management.service.INeedplateService;
import com.plate.common.utils.poi.ExcelUtil;
import com.plate.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 入库车牌管理Controller
 * 
 * @author yzy
 * @date 2024-08-03
 */
@RestController
@RequestMapping("/management/needplate")
public class NeedplateController extends BaseController
{
    @Autowired
    private INeedplateService needplateService;
    /**
     * 导入入库车牌管理数据
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:import')")
    @Log(title = "入库车牌管理", businessType = BusinessType.IMPORT)
    @PostMapping("/import")
    public ResponseEntity<List<String>> importNeedplate(@RequestParam("file") MultipartFile file) {
        try {
            List<String> failedPlates = needplateService.importNeedplate(file);
            return ResponseEntity.ok(failedPlates);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    /**
     * 查询入库车牌管理列表
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(Needplate needplate)
    {
        startPage();
        List<Needplate> list = needplateService.selectNeedplateList(needplate);
        return getDataTable(list);
    }

    /**
     * 导出入库车牌管理列表
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:export')")
    @Log(title = "入库车牌管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Needplate needplate)
    {
        List<Needplate> list = needplateService.selectNeedplateList(needplate);
        ExcelUtil<Needplate> util = new ExcelUtil<Needplate>(Needplate.class);
        util.exportExcel(response, list, "入库车牌管理数据");
    }

    /**
     * 获取入库车牌管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:query')")
    @GetMapping(value = "/{pid}")
    public AjaxResult getInfo(@PathVariable("pid") Long pid)
    {
        return success(needplateService.selectNeedplateByPid(pid));
    }

    /**
     * 新增入库车牌管理
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:add')")
    @Log(title = "入库车牌管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Needplate needplate)
    {
        return toAjax(needplateService.insertNeedplate(needplate));
    }

    /**
     * 修改入库车牌管理
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:edit')")
    @Log(title = "入库车牌管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Needplate needplate)
    {
        return toAjax(needplateService.updateNeedplate(needplate));
    }

    /**
     * 删除入库车牌管理
     */
    @PreAuthorize("@ss.hasPermi('management:needplate:remove')")
    @Log(title = "入库车牌管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pids}")
    public AjaxResult remove(@PathVariable Long[] pids)
    {
        return toAjax(needplateService.deleteNeedplateByPids(pids));
    }
}
