package com.plate.management.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.plate.common.annotation.Excel;
import com.plate.common.core.domain.BaseEntity;

/**
 * 所有车牌对象 allplate
 * 
 * @author yzy
 * @date 2024-08-03
 */
public class Allplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 识别记录的id */
    private Long id;

    /** 车牌号 */
    @Excel(name = "车牌号")
    private String plate;

    /** 识别时间 */
    @Excel(name = "识别时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;

    /** 经纬度 */
    @Excel(name = "经纬度")
    private String location;

    /** 设备号码 */
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
    public void setPlate(String plate) 
    {
        this.plate = plate;
    }

    public String getPlate() 
    {
        return plate;
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
            .append("plate", getPlate())
            .append("time", getTime())
            .append("location", getLocation())
            .append("deviceId", getDeviceId())
            .toString();
    }
}
