package com.itstudent.springbootdemo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: ImageCaptureDto
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/9 13:58
 * @Description: TODO
 */
@Data
public class ImageCaptureDto implements Serializable {

    private static final long serialVersionUID = -7375991280893679114L;
    private String sign;
    private String plateNo;
    private String deviceNumber;
    private String simNo;
    private String captureType;
    private Integer captureChannel;
}
