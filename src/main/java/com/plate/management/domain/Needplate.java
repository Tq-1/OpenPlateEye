package com.plate.management.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.plate.common.annotation.Excel;
import com.plate.common.core.domain.BaseEntity;

/**
 * 入库车牌管理对象 needplate
 * 
 * @author yzy
 * @date 2024-08-03
 */
public class Needplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 需要的车牌的id */
    private Long pid;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String plate;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 车主 */
    @Excel(name = "车主")
    private String owner;

    /** 车型 */
    @Excel(name = "车型")
    private String type;

    /** 车架号 */
    @Excel(name = "车架号")
    private String frame;

    /* 插入状态*/
    private String status;



    /** 已入库车牌踪迹信息 */
    private List<History> historyList;


    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setPlate(String plate) 
    {
        this.plate = plate;
    }

    public String getPlate() 
    {
        return plate;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setOwner(String owner) 
    {
        this.owner = owner;
    }

    public String getOwner() 
    {
        return owner;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setFrame(String frame) 
    {
        this.frame = frame;
    }

    public String getFrame() 
    {
        return frame;
    }

    public List<History> getHistoryList()
    {
        return historyList;
    }

    public void setHistoryList(List<History> historyList)
    {
        this.historyList = historyList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid", getPid())
            .append("plate", getPlate())
            .append("phone", getPhone())
            .append("owner", getOwner())
            .append("type", getType())
            .append("frame", getFrame())
            .append("historyList", getHistoryList())
            .toString();
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
