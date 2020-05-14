package com.itstudent.springbootdemo.dto;

import lombok.Data;
import java.io.Serializable;

/**
 * @ClassName: IssueVoiceResultDto
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/12/9 13:51
 * @Description: TODO
 */
@Data
public class IssueVoiceResultDto implements Serializable {

    private static final long serialVersionUID = 659508333212628244L;
    private String sign;
    private String plateNo;
    private String deviceNumber;
    private String simNo;
    private Long cmdId;
}
