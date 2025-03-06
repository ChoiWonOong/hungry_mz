package com.hungrymz.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"data.*","*.controller","com.hungrymz.main", "com.menu.controller", "com.menu.service", "com.naver.storage", "com.restaurant.controller", "com.restaurant.service"})
@MapperScan({"data.mapper"})
public class HungryMzApplication {
	public static void main(String[] args) {
		SpringApplication.run(HungryMzApplication.class, args);
	}
}
