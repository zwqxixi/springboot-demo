package com.itstudent.springbootdemo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: RealTimeVideoDto
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/9 14:02
 * @Description: TODO
 */
@Data
public class RealTimeVideoDto implements Serializable {
    private static final long serialVersionUID = 3264608478626665843L;
    private String sign;
    private String plateNo;
    private String deviceNumber;
    private String simNo;
    private String liveStreamUrl;
    private String liveStreamType;
    private String liveStreamChannel;
}
