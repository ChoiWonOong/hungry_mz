package com.hungrymz.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"data.*","com.*.controller","com.*.service", "com.naver.storage"})
@MapperScan({"data.mapper"})
@EnableConfigurationProperties
public class HungryMzApplication {
	public static void main(String[] args) {
		SpringApplication.run(HungryMzApplication.class, args);
	}
}
