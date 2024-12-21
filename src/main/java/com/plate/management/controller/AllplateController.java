package com.plate.management.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.plate.common.annotation.Log;
import com.plate.common.core.controller.BaseController;
import com.plate.common.core.domain.AjaxResult;
import com.plate.common.enums.BusinessType;
import com.plate.management.domain.Allplate;
import com.plate.management.service.IAllplateService;
import com.plate.common.utils.poi.ExcelUtil;
import com.plate.common.core.page.TableDataInfo;

/**
 * 所有车牌Controller
 * 
 * @author yzy
 * @date 2024-08-03
 */
@RestController
@RequestMapping("/management/allplate")
public class AllplateController extends BaseController
{
    @Autowired
    private IAllplateService allplateService;

    /**
     * 查询所有车牌列表
     */
    @PreAuthorize("@ss.hasPermi('management:allplate:list')")
    @GetMapping("/list")
    public TableDataInfo list(Allplate allplate)
    {
        startPage();
        List<Allplate> list = allplateService.selectAllplateList(allplate);
        return getDataTable(list);
    }

    /**
     * 导出所有车牌列表
     */
    @PreAuthorize("@ss.hasPermi('management:allplate:export')")
    @Log(title = "所有车牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Allplate allplate)
    {
        List<Allplate> list = allplateService.selectAllplateList(allplate);
        ExcelUtil<Allplate> util = new ExcelUtil<Allplate>(Allplate.class);
        util.exportExcel(response, list, "所有车牌数据");
    }

    /**
     * 获取所有车牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:allplate:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(allplateService.selectAllplateById(id));
    }

    /**
     * 新增所有车牌
     */
    @PreAuthorize("@ss.hasPermi('management:allplate:add')")
    @Log(title = "所有车牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Allplate allplate)
    {
        return toAjax(allplateService.insertAllplate(allplate));
    }

    /**
     * 修改所有车牌
     */
    @PreAuthorize("@ss.hasPermi('management:allplate:edit')")
    @Log(title = "所有车牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Allplate allplate)
    {
        return toAjax(allplateService.updateAllplate(allplate));
    }

    /**
     * 删除所有车牌
     */
    @PreAuthorize("@ss.hasPermi('management:allplate:remove')")
    @Log(title = "所有车牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(allplateService.deleteAllplateByIds(ids));
    }
}
