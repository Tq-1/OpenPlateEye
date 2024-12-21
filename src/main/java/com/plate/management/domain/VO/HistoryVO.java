package com.plate.management.domain.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.plate.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

@Data
public class HistoryVO {
    private Long id;

    @Excel(name = "车牌号")
    private String plate;
    @Excel(name = "手机号")
    private String phone;
    @Excel(name = "车主")
    private String owner;
    @Excel(name = "车型")
    private String type;
    @Excel(name = "车架号")
    private String frame;

    @Excel(name = "识别时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date time;

    private String location;
    @Excel(name = "地址")
    private String address;

    @Excel(name = "设备号码")
    private String deviceId;
}
