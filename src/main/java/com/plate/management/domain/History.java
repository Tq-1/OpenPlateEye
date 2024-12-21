package com.plate.management.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.plate.common.annotation.Excel;
import com.plate.common.core.domain.BaseEntity;

/**
 * 已入库车牌踪迹对象 history
 * 
 * @author yzy
 * @date 2024-08-03
 */
@Data
public class History extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 识别记录的id */
    private Long id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private Long pid;

    /** 识别时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Excel(name = "识别时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date time;

    /** 经纬度 */
    private String location;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 设备号码*/
    @Excel(name = "设备号码")
    private String deviceId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setTime(Date time) 
    {
        this.time = time;
    }

    public Date getTime() 
    {
        return time;
    }
    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getDeviceId()
    {
        return deviceId;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("time", getTime())
            .append("location", getLocation())
            .append("address", getAddress())
            .append("deviceId", getDeviceId())
            .toString();
    }
}
