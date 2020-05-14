package com.itstudent.springbootdemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.itstudent.springbootdemo.mapper")
@EnableCaching//开启缓存
@EnableAspectJAutoProxy//开启SpringAop
//@EnableApolloConfig//开启apollo配置中心
//@EnableElasticsearchRepositories//开启ElasticSearch支持
@EnableScheduling
public class SpringBootDemoApplication {
	public static void main(String[] args) {
		//由于其他地方使用netty的缘故
		System.setProperty("es.set.netty.runtime.available.processors","false");
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}
}
