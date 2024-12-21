package com.plate.management.service;

import java.io.IOException;
import java.util.List;
import com.plate.management.domain.Needplate;
import org.springframework.web.multipart.MultipartFile;

/**
 * 入库车牌管理Service接口
 * 
 * @author yzy
 * @date 2024-08-03
 */
public interface INeedplateService 
{
    // 处理 Excel 文件导入
    public List<String> importNeedplate(MultipartFile file) throws IOException;


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

    /**
     * 批量删除入库车牌管理
     * 
     * @param pids 需要删除的入库车牌管理主键集合
     * @return 结果
     */
    public int deleteNeedplateByPids(Long[] pids);

    /**
     * 删除入库车牌管理信息
     * 
     * @param pid 入库车牌管理主键
     * @return 结果
     */
    public int deleteNeedplateByPid(Long pid);
}
