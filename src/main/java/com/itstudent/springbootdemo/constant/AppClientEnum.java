package com.itstudent.springbootdemo.constant;
import lombok.Getter;
/**
 * @Project: rdf-biz-common
 * @Auther: ting.wu
 * @CreateDate: 2019-08-17 16:08
 * @Description: app configg related enum
 */
public enum AppClientEnum {

    ANDRIOD("安卓", 1),
    IOS("IOS", 2);


    AppClientEnum(String clientName, int clientCode) {
        this.clientCode = clientCode;
        this.clientName = clientName;
    }

    @Getter
    private String clientName;
    @Getter
    private int clientCode;

}
