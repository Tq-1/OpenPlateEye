package com.plate.management.service;

import java.util.List;
import com.plate.management.domain.History;
import com.plate.management.domain.VO.HistoryVO;

/**
 * 已入库车牌踪迹Service接口
 * 
 * @author yzy
 * @date 2024-08-03
 */
public interface IHistoryService 
{
    List<HistoryVO> selectHistoryByUserDevices(Long userId);
    /**
     * 查询已入库车牌踪迹
     * 
     * @param id 已入库车牌踪迹主键
     * @return 已入库车牌踪迹
     */
    public History selectHistoryById(Long id);

    /**
     * 查询已入库车牌踪迹列表
     * 
     * @param history 已入库车牌踪迹
     * @return 已入库车牌踪迹集合
     */
    public List<HistoryVO> selectHistoryList(HistoryVO history);

    /**
     * 新增已入库车牌踪迹
     * 
     * @param history 已入库车牌踪迹
     * @return 结果
     */
    public int insertHistory(History history);

    /**
     * 修改已入库车牌踪迹
     * 
     * @param history 已入库车牌踪迹
     * @return 结果
     */
    public int updateHistory(History history);

    /**
     * 批量删除已入库车牌踪迹
     * 
     * @param ids 需要删除的已入库车牌踪迹主键集合
     * @return 结果
     */
    public int deleteHistoryByIds(Long[] ids);

    /**
     * 删除已入库车牌踪迹信息
     * 
     * @param id 已入库车牌踪迹主键
     * @return 结果
     */
    public int deleteHistoryById(Long id);
}
