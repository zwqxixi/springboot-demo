package com.itstudent.springbootdemo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: IssueVoiceDto
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/9 10:14
 * @Description: TODO
 */
@Data
public class IssueVoiceDto implements Serializable {

    private static final long serialVersionUID = -2574953677866602287L;
    private String sign;
    private String plateNo;
    private String deviceNumber;
    private String simNo;
    private String sendType;
    private String textContent;

}
