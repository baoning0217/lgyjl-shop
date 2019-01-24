package com.vincce.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vincce.shop.mapper")
public class LgyjlShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LgyjlShopApplication.class, args);
	}

}

