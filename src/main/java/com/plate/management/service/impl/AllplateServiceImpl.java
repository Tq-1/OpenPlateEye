package com.plate.management.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.plate.management.mapper.AllplateMapper;
import com.plate.management.domain.Allplate;
import com.plate.management.service.IAllplateService;

/**
 * 所有车牌Service业务层处理
 * 
 * @author yzy
 * @date 2024-08-03
 */
@Service
public class AllplateServiceImpl implements IAllplateService 
{
    @Autowired
    private AllplateMapper allplateMapper;

    /**
     * 查询所有车牌
     * 
     * @param id 所有车牌主键
     * @return 所有车牌
     */
    @Override
    public Allplate selectAllplateById(Long id)
    {
        return allplateMapper.selectAllplateById(id);
    }

    /**
     * 查询所有车牌列表
     * 
     * @param allplate 所有车牌
     * @return 所有车牌
     */
    @Override
    public List<Allplate> selectAllplateList(Allplate allplate)
    {
        return allplateMapper.selectAllplateList(allplate);
    }

    /**
     * 新增所有车牌
     * 
     * @param allplate 所有车牌
     * @return 结果
     */
    @Override
    public int insertAllplate(Allplate allplate)
    {
        return allplateMapper.insertAllplate(allplate);
    }

    /**
     * 修改所有车牌
     * 
     * @param allplate 所有车牌
     * @return 结果
     */
    @Override
    public int updateAllplate(Allplate allplate)
    {
        return allplateMapper.updateAllplate(allplate);
    }

    /**
     * 批量删除所有车牌
     * 
     * @param ids 需要删除的所有车牌主键
     * @return 结果
     */
    @Override
    public int deleteAllplateByIds(Long[] ids)
    {
        return allplateMapper.deleteAllplateByIds(ids);
    }

    /**
     * 删除所有车牌信息
     * 
     * @param id 所有车牌主键
     * @return 结果
     */
    @Override
    public int deleteAllplateById(Long id)
    {
        return allplateMapper.deleteAllplateById(id);
    }
}
