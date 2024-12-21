package com.plate.management.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.plate.common.exception.ServiceException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plate.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.plate.management.domain.History;
import com.plate.management.mapper.NeedplateMapper;
import com.plate.management.domain.Needplate;
import com.plate.management.service.INeedplateService;
import org.springframework.web.multipart.MultipartFile;

/**
 * 入库车牌管理Service业务层处理
 * 
 * @author yzy
 * @date 2024-08-03
 */
@Service
public class NeedplateServiceImpl implements INeedplateService 
{
    @Autowired
    private NeedplateMapper needplateMapper;

    // 处理 Excel 文件导入
//    private static final Logger logger = LoggerFactory.getLogger(NeedplateServiceImpl.class);
    private static final String PLATE_REGEX = "^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z](([DF]((?![IO])[a-zA-Z0-9](?![IO]))[0-9]{4})|([0-9]{5}[DF]))|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}|民航[A-HJ-NP\\d]{5}|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青��川宁琼使领]{1}[A-Z0-9]{7})|危险品|(WJ[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼·•]{1}[0-9]{4}[TDSHBXJ0-9]{1})|([VKHBSLJNGCE]{1}[A-DJ-PR-TVY]{1}[0-9]{5})$";
    private static final Pattern PLATE_PATTERN = Pattern.compile(PLATE_REGEX);

    @Override
    public List<String> importNeedplate(MultipartFile file) throws IOException {
        List<Needplate> needplateList = new ArrayList<>();
        List<String> failedPlates = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue; // 跳过表头行

            Needplate needplate = new Needplate();
            String plate = getCellValue(row.getCell(0));
            needplate.setPlate(plate);
            needplate.setOwner(getCellValue(row.getCell(1)));
            needplate.setPhone(getCellValue(row.getCell(2)));
            needplate.setType(getCellValue(row.getCell(3)));
            needplate.setFrame(getCellValue(row.getCell(4)));

            Matcher matcher = PLATE_PATTERN.matcher(plate);
            if (!matcher.matches()) {
                failedPlates.add(plate);
                continue;
            }

            Needplate existing = needplateMapper.selectNeedplateByPlate(needplate.getPlate());
            if (existing == null) {
                needplateMapper.insertNeedplate(needplate);
            } else {
                needplate.setPid(existing.getPid());
                needplateMapper.updateNeedplateByPlate(needplate);
            }

            needplateList.add(needplate);
        }

        workbook.close();
        return failedPlates;
    }

    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    cell.setCellType(CellType.STRING);
                    return cell.getStringCellValue();
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * 查询入库车牌管理
     * 
     * @param pid 入库车牌管理主键
     * @return 入库车牌管理
     */
    @Override
    public Needplate selectNeedplateByPid(Long pid)
    {
        return needplateMapper.selectNeedplateByPid(pid);
    }

    /**
     * 查询入库车牌管理列表
     * 
     * @param needplate 入库车牌管理
     * @return 入库车牌管理
     */
    @Override
    public List<Needplate> selectNeedplateList(Needplate needplate)
    {
        return needplateMapper.selectNeedplateList(needplate);
    }

    /**
     * 新增入库车牌管理
     * 
     * @param needplate 入库车牌管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNeedplate(Needplate needplate)
    {
        int rows = needplateMapper.insertNeedplate(needplate);
        insertHistory(needplate);
        return rows;
    }

    /**
     * 修改入库车牌管理
     * 
     * @param needplate 入库车牌管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNeedplate(Needplate needplate)
    {
        needplateMapper.deleteHistoryByPid(needplate.getPid());
        insertHistory(needplate);
        return needplateMapper.updateNeedplate(needplate);
    }

    /**
     * 批量删除入库车牌管理
     * 
     * @param pids 需要删除的入库车牌管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNeedplateByPids(Long[] pids)
    {
        needplateMapper.deleteHistoryByPids(pids);
        return needplateMapper.deleteNeedplateByPids(pids);
    }

    /**
     * 删除入库车牌管理信息
     * 
     * @param pid 入库车牌管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteNeedplateByPid(Long pid)
    {
        needplateMapper.deleteHistoryByPid(pid);
        return needplateMapper.deleteNeedplateByPid(pid);
    }

    /**
     * 新增已入库车牌踪迹信息
     * 
     * @param needplate 入库车牌管理对象
     */
    public void insertHistory(Needplate needplate)
    {
        List<History> historyList = needplate.getHistoryList();
        Long pid = needplate.getPid();
        if (StringUtils.isNotNull(historyList))
        {
            List<History> list = new ArrayList<History>();
            for (History history : historyList)
            {
                history.setPid(pid);
                list.add(history);
            }
            if (list.size() > 0)
            {
                needplateMapper.batchHistory(list);
            }
        }
    }
}
