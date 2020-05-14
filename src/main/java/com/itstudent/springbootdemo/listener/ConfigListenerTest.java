package com.itstudent.springbootdemo.listener;

import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.stereotype.Component;

/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/10/30 11:03
 * @Description: 监听配置文件是否修改
 */
@Component
public class ConfigListenerTest {
    // config change listener for namespace application
    /*@ApolloConfigChangeListener("application")
    private void anotherOnChange(ConfigChangeEvent changeEvent) {
        ConfigChange change = changeEvent.getChange("zhangsan_conf");
        System.out.println(String.format("Found change - key: %s, oldValue: %s," + "newValue: %s, changeType: %s",
                change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
    }*/
}
