package com.plate.management.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.plate.common.utils.SecurityUtils;
import com.plate.management.domain.VO.HistoryVO;
import org.apache.catalina.filters.ExpiresFilter;
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
import com.plate.management.domain.History;
import com.plate.management.service.IHistoryService;
import com.plate.common.utils.poi.ExcelUtil;
import com.plate.common.core.page.TableDataInfo;



/**
 * 已入库车牌踪迹Controller
 * 
 * @author yzy
 * @date 2024-08-03
 */
@RestController
@RequestMapping("/management/history")
public class HistoryController extends BaseController
{
    @Autowired
    private IHistoryService historyService;
//
//    /**
//     * 查询已入库车牌踪迹列表
//     */
//    @PreAuthorize("@ss.hasPermi('management:history:list')")
//    @GetMapping("/list")
//    public TableDataInfo list(HistoryVO history)
//    {
//        startPage();
//        List<HistoryVO> list = historyService.selectHistoryList(history);
//        return getDataTable(list);
//    }

    @PreAuthorize("@ss.hasPermi('management:history:list')")
    @GetMapping("/userDevicesHistory")
    public TableDataInfo getUserDevicesHistory() {
        Long userId = getCurrentUserId(); // 获取当前用户ID的方法
        System.out.println("userId: " + userId);
        List<HistoryVO> list = historyService.selectHistoryByUserDevices(userId);
        startPage();
        return getDataTable(list);
    }

    //获取当前用户ID的方法
    public Long getCurrentUserId() {
        return SecurityUtils.getUserId();
    }

    @PreAuthorize("@ss.hasPermi('management:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(HistoryVO history)
    {
        List<HistoryVO> list;
        if (isAllFieldsNull(history)) {
            PageHelper.startPage(1, 30, false);
            list = historyService.selectHistoryList(new HistoryVO());
        } else {
            startPage();
            list = historyService.selectHistoryList(history);
        }
        return getDataTable(list);
    }

    private boolean isAllFieldsNull(HistoryVO history) {
        return history.getId() == null &&
                history.getPlate() == null &&
                history.getPhone() == null &&
                history.getOwner() == null &&
                history.getType() == null &&
                history.getFrame() == null &&
                history.getTime() == null &&
                history.getLocation() == null &&
                history.getAddress() == null &&
                history.getDeviceId() == null;
    }

    /**
     * 导出已入库车牌踪迹列表
     */
//    @PreAuthorize("@ss.hasPermi('management:history:export')")
//    @Log(title = "已入库车牌踪迹", businessType = BusinessType.EXPORT)
//    @PostMapping("/export_All")
//    public void export(HttpServletResponse response, HistoryVO history)
//    {
//        List<HistoryVO> list = historyService.selectHistoryList(history);
//        ExcelUtil<HistoryVO> util = new ExcelUtil<HistoryVO>(HistoryVO.class);
//        util.exportExcel(response, list, "已入库车牌踪迹数据");
//    }

    /**
     * 导出已入库车牌踪迹列表
     */
    @PreAuthorize("@ss.hasPermi('management:history:export')")
    @Log(title = "已入库车牌踪迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export2(HttpServletResponse response, HistoryVO history)
    {
        Long userId = getCurrentUserId(); // 获取当前用户ID的方法
        System.out.println("userId: " + userId);
        List<HistoryVO> list = historyService.selectHistoryByUserDevices(userId);
        ExcelUtil<HistoryVO> util = new ExcelUtil<HistoryVO>(HistoryVO.class);
        util.exportExcel(response, list, "已入库车牌踪迹数据");
    }

    /**
     * 获取已入库车牌踪迹详细信息
     */
    @PreAuthorize("@ss.hasPermi('management:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(historyService.selectHistoryById(id));
    }

    /**
     * 新增已入库车牌踪迹
     */
    @PreAuthorize("@ss.hasPermi('management:history:add')")
    @Log(title = "已入库车牌踪迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody History history)
    {
        return toAjax(historyService.insertHistory(history));
    }

    /**
     * 修改已入库车牌踪迹
     */
    @PreAuthorize("@ss.hasPermi('management:history:edit')")
    @Log(title = "已入库车牌踪迹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody History history)
    {
        return toAjax(historyService.updateHistory(history));
    }

    /**
     * 删除已入库车牌踪迹
     */
    @PreAuthorize("@ss.hasPermi('management:history:remove')")
    @Log(title = "已入库车牌踪迹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(historyService.deleteHistoryByIds(ids));
    }
}
