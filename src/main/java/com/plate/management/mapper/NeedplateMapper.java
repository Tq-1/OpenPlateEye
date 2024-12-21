package com.plate.management.mapper;

import java.util.List;
import com.plate.management.domain.Needplate;
import com.plate.management.domain.History;

/**
 * 入库车牌管理Mapper接口
 * 
 * @author yzy
 * @date 2024-08-03
 */
public interface NeedplateMapper 
{
    /**
     * 查询入库车牌管理
     * 
     * @param pid 入库车牌管理主键
     * @return 入库车牌管理
     */
    public Needplate selectNeedplateByPid(Long pid);

    /**
     * 查询入库车牌管理列表
     * 
     * @param needplate 入库车牌管理
     * @return 入库车牌管理集合
     */
    public List<Needplate> selectNeedplateList(Needplate needplate);

    /**
     * 新增入库车牌管理
     * 
     * @param needplate 入库车牌管理
     * @return 结果
     */
    public int insertNeedplate(Needplate needplate);

    /**
     * 修改入库车牌管理
     * 
     * @param needplate 入库车牌管理
     * @return 结果
     */
    public int updateNeedplate(Needplate needplate);

    public int updateNeedplateByPlate(Needplate needplate);

    /**
     * 删除入库车牌管理
     * 
     * @param pid 入库车牌管理主键
     * @return 结果
     */
    public int deleteNeedplateByPid(Long pid);

    /**
     * 批量删除入库车牌管理
     * 
     * @param pids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNeedplateByPids(Long[] pids);

    /**
     * 批量删除已入库车牌踪迹
     * 
     * @param pids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteHistoryByPids(Long[] pids);
    
    /**
     * 批量新增已入库车牌踪迹
     * 
     * @param historyList 已入库车牌踪迹列表
     * @return 结果
     */
    public int batchHistory(List<History> historyList);
    

    /**
     * 通过入库车牌管理主键删除已入库车牌踪迹信息
     * 
     * @param pid 入库车牌管理ID
     * @return 结果
     */
    public int deleteHistoryByPid(Long pid);

    /**
     * 通过车牌号查询入库车牌管理
     *
     * @param plate 车牌号
     * @return 入库车牌管理
     */
    public Needplate selectNeedplateByPlate(String plate);

}
