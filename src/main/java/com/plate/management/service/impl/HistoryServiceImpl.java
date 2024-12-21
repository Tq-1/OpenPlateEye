package com.plate.management.service.impl;

import java.util.List;

import com.plate.management.domain.VO.HistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.plate.management.mapper.HistoryMapper;
import com.plate.management.domain.History;
import com.plate.management.service.IHistoryService;

/**
 * 已入库车牌踪迹Service业务层处理
 * 
 * @author yzy
 * @date 2024-08-03
 */
@Service
public class HistoryServiceImpl implements IHistoryService 
{
    @Autowired
    private HistoryMapper historyMapper;

    @Override
    public List<HistoryVO> selectHistoryByUserDevices(Long userId) {
        return historyMapper.selectHistoryByUserDevices(userId);
    }
    /**
     * 查询已入库车牌踪迹
     * 
     * @param id 已入库车牌踪迹主键
     * @return 已入库车牌踪迹
     */
    @Override
    public History selectHistoryById(Long id)
    {
        return historyMapper.selectHistoryById(id);
    }

    /**
     * 查询已入库车牌踪迹列表
     * 
     * @param history 已入库车牌踪迹
     * @return 已入库车牌踪迹
     */
    @Override
    public List<HistoryVO> selectHistoryList(HistoryVO history)
    {
        return historyMapper.selectHistoryList(history);
    }

    /**
     * 新增已入库车牌踪迹
     * 
     * @param history 已入库车牌踪迹
     * @return 结果
     */
    @Override
    public int insertHistory(History history)
    {
        return historyMapper.insertHistory(history);
    }

    /**
     * 修改已入库车牌踪迹
     * 
     * @param history 已入库车牌踪迹
     * @return 结果
     */
    @Override
    public int updateHistory(History history)
    {
        return historyMapper.updateHistory(history);
    }

    /**
     * 批量删除已入库车牌踪迹
     * 
     * @param ids 需要删除的已入库车牌踪迹主键
     * @return 结果
     */
    @Override
    public int deleteHistoryByIds(Long[] ids)
    {
        return historyMapper.deleteHistoryByIds(ids);
    }

    /**
     * 删除已入库车牌踪迹信息
     * 
     * @param id 已入库车牌踪迹主键
     * @return 结果
     */
    @Override
    public int deleteHistoryById(Long id)
    {
        return historyMapper.deleteHistoryById(id);
    }
}
