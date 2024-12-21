package com.plate.management.service;

import java.util.List;
import com.plate.management.domain.Allplate;

/**
 * 所有车牌Service接口
 * 
 * @author yzy
 * @date 2024-08-03
 */
public interface IAllplateService 
{
    /**
     * 查询所有车牌
     * 
     * @param id 所有车牌主键
     * @return 所有车牌
     */
    public Allplate selectAllplateById(Long id);

    /**
     * 查询所有车牌列表
     * 
     * @param allplate 所有车牌
     * @return 所有车牌集合
     */
    public List<Allplate> selectAllplateList(Allplate allplate);

    /**
     * 新增所有车牌
     * 
     * @param allplate 所有车牌
     * @return 结果
     */
    public int insertAllplate(Allplate allplate);

    /**
     * 修改所有车牌
     * 
     * @param allplate 所有车牌
     * @return 结果
     */
    public int updateAllplate(Allplate allplate);

    /**
     * 批量删除所有车牌
     * 
     * @param ids 需要删除的所有车牌主键集合
     * @return 结果
     */
    public int deleteAllplateByIds(Long[] ids);

    /**
     * 删除所有车牌信息
     * 
     * @param id 所有车牌主键
     * @return 结果
     */
    public int deleteAllplateById(Long id);
}
