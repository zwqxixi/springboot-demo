package com.itstudent.springbootdemo.alipay;

import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName: AlipayConfig
 * @Auther: wenqin.zhao
 * @CreateDate: 2020/3/25 1:01
 * @Description: AlipayConfig
 */
public class AlipayConfig {
    // APPID
    @Value("${aliPay.appId}")
    public String app_id;

    // 生成公钥时对应的私钥（填自己的）
    @Value("${aliPay.privateKey}")
    public String private_key;

    // 公钥
    @Value("${aliPay.publicKey}")
    public String public_key;

    //异步回调接口:得放到服务器上，且使用域名解析 IP
    @Value("${aliPay.notifyUrl}")
    public String notify_url = "回调函数接口";

    //支付宝网关（注意沙箱alipaydev，正式则为 alipay）不需要修改
    public static String url = "https://openapi.alipaydev.com/gateway.do";

    //编码类型
    public static String charset = "UTF-8";

    //数据类型
    public static String format = "json";

    //签名类型
    public static String signtype = "RSA2";
}
